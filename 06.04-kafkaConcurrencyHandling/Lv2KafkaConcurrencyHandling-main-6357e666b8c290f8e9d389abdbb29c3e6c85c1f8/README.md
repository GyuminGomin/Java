### Kafka 메시지 동시성 처리

카프카 파티션이 스케일 아웃(Scale out)되면, 프로바이더가 보내는 이벤트 메시지는 라운드 로빈(Round Robin) 방식으로 늘어난 파티션에 균등 배분되어 적재된다. 이 때 파티션에 매핑된 컨슈머가 발행된 이벤트를 시간적 순서대로 처리하지 않으면, 비즈니스 오류가 발생하게 되는데 이에 대한 해결책을 본 랩으로 학습한다.

- Kafka 파티션 스케일아웃에 따른 동시성 이슈를 확인한다.
- 컨슈머 측에서 순서가 보장되기 위한 설정을 이해하고 실습한다.


### 주문, 배송서비스 실행

- 카프카 실행상태를 확인하고, 사용할 MySQL DB를 시작한다.
- 주문, 배송 마이크로서비스를 실행하고, 주문에 따른 배송 프로세스를 확인한다.
```
cd mysql
docker-compose up -d

cd order
mvn clean spring-boot:run

cd delivery
mvn clean spring-boot:run
```


## Test - 싱글 파티션, 싱글 컨슈머 환경

- 새로운 터미널에서 카프카 토픽을 실시간 모니터링한다.
```
cd kafka
docker-compose exec kafka /bin/bash
cd /bin
./kafka-console-consumer --bootstrap-server localhost:9092 --topic kafka.scaling --from-beginning
```

- 새로운 터미널을 오픈해 주문을 실행한다.
- PARK(id: 1000) 주문에 이어, KIM(id: 2000) 주문도 생성한다.
```
http :8081/orders customerId=1000 productId=100 productName=TV qty=3 address=SEOUL
http :8081/orders customerId=2000 productId=100 productName=RADIO qty=3 address=PUSAN
```

- KIM이 배송지 정보를 수정(PUSAN > SEOUL)한다.
- 주문수정이 일어나면 배송서비스에서는 10초의 딜레이(Long transaction)가 발생하도록 설정되어 있다.
```
http PATCH :8081/orders/2 address=SEOUL
```

- KIM이 주문을 삭제한다.
```
http DELETE :8081/orders/2
```

- 컨슈머(배송서비스)가 하나일 때는 싱글 스레드로 동작해, 설정된 딜레이 시간이 경과된 후 수정,삭제가 일어난다. 
- '배송수정(DELIVERY MODIFIED)'에 이어 '배송취소(DELIVERY CANCELLED)' 이벤트가 카프카에 수신되었다. 

- 새로운 터미널에서 데이터베이스를 조회해 보자.
```
cd mysql
docker-compose exec -it master-server bash
mysql --user=root --password=1234
use my-database;
select * from Delivery_table;
```
- 배송서비스 테이블에 배송취소에 대한 상태값(DELIVERY CANCELLED)이 정상적으로 조회된다.


## Test - 멀티 파티션, 멀티 컨슈머 환경


<u>12st Mall 사용량이 증가해 카프카와 배송서비스를 증설(스케일 아웃)할 필요가 생겼다고 가정해 보자. </u>


- 열린 카프카 터미널에서 파티션을 2개(default 1)로 Scale Out 한다.
```
./kafka-topics --bootstrap-server 127.0.0.1:9092 --alter --topic kafka.scaling -partitions 2
./kafka-topics --bootstrap-server 127.0.0.1:9092 --topic kafka.scaling --describe
```

- 새로운 터미널에서 두번째 배송 서비스를 실행(Scale Out) 한다.
```
cd delivery-2nd
mvn clean spring-boot:run
```
- 카프카 토픽의 파티션이 2개, 배송 서비스도 2개이므로, 각 배송서비스들은 각자 처리하는 파티션을 배정받는다.
- 이를 Kafka에서 리밸런싱(Re-balancing)이라고 부른다.


## 1. 처리순서가 지켜지지 않는 메시징
### 동일한 주문이 여러 파티션에 랜덤하게 적재되는 경우, 

- KIM(id: 2000)이 주문을 생성한다.
```
http POST :8081/orders customerId=2000 productId=100 productName=RADIO qty=3 address=PUSAN
```

- KIM이 배송지 정보를 수정(PUSAN > SEOUL)한다.
```
http PATCH :8081/orders/{order-id} address=SEOUL
```

- KIM이 주문을 삭제한다.
```
http DELETE :8081/orders/{order-id}
```

- '주문수정'과 '주문취소' 메시지는 라운드 로빈으로 각각 다른 파티션에 저장된다. 
- 배송 서비스는 각 매칭된 파티션으로부터 이벤트를 수신하여 동시성 처리가 일어난다.
- 이때, 
- 어느 한 배송 서비스가 '주문수정' 이벤트를 수신하여 '배송수정' 로직을 처리(10초, long Trx)하는 동안에,
- 다른 배송 서비스가 '주문취소' 이벤트를 수신하여 배송정보를 먼저 삭제 처리해 버린다.
- 이로 인해 '배송수정'을 처리하는 서비스는 삭제된 배송정보에 대해 배송수정을 처리하려다 오류를 일으키게 된다. 
- 카프카 컨슈머로 토픽을 조회해 보자.
```
./kafka-console-consumer --bootstrap-server localhost:9092 --topic kafka.scaling --from-beginning
```
- 'DeliveryCancelled' 이벤트가 'DeliveryModified' 이벤트보다 먼저 카프카에 퍼블리시 되었다. 
- 데이터베이스가 열려진 터미널에서 테이블을 조회해 보자.
```
select * from Delivery_table;
```
- 삭제되어야 할 배송정보가 수정된 상태('DELIVERY MODIFIED')로 조회되고 있으며, 이는 메시지 처리 순서상 문제가 발생했음을 알 수 있다. 


## 2. 처리순서가 보장되는 메시징
### 동일한 주문이 동일 파티션에 적재되는 경우, 

- 멀티 파티션, 멀티 컨슈머 환경에서 처리순서가 보장되려면, 도메인 이벤트 퍼블리시 때, 키(Key)와 함께 전송한다.
- 카프카 파티셔너는 동일한 키에 대해 동일 파티션에 이벤트를 적재해 준다.
- 이를 적용해 보자.
- 주문 서비스에서 Order id를 메시지 키로 하여 퍼블리시 되도록 Key를 추가한다.
- Order > Order.java 포트 메소드에서 아래대로 수정한다.
```
# 34,40,49 line modify
orderPlaced.publishAfterCommit(getId());
```

- 아래 코드를 복사하여 Order > AbstractEvent.java 62라인에 추가한다.
```
    public void publish(String messageKey) {
        /**
         * spring streams 방식
         */
        KafkaProcessor processor = OrderApplication.applicationContext.getBean(
            KafkaProcessor.class
        );
        MessageChannel outputChannel = processor.outboundTopic();
 
        outputChannel.send(
            MessageBuilder
                .withPayload(this)
                .setHeader(
                    MessageHeaders.CONTENT_TYPE,
                    MimeTypeUtils.APPLICATION_JSON
                )
                .setHeader("type", getEventType())
                .setHeader(KafkaHeaders.MESSAGE_KEY, messageKey.getBytes())
                .build()
        );
    }

    public void publishAfterCommit(Long messageKey) {
        TransactionSynchronizationManager.registerSynchronization(
            new TransactionSynchronizationAdapter() {
                @Override
                public void afterCompletion(int status) {
                    AbstractEvent.this.publish(String.valueOf(messageKey));
                }
            }
        );
    }
```
- 주문서비스를 재시작한다. 
- KIM(id: 2000)이 주문을 생성한다.
```
http :8081/orders customerId=2000 productId=100 productName=RADIO qty=3 address=PUSAN
```

- KIM이 배송지 정보를 수정(PUSAN > SEOUL)한다.
```
http PATCH :8081/orders/{order-id} address=SEOUL
```

- KIM이 주문을 삭제한다.
```
http DELETE :8081/orders/{order-id}
```

- 카프카 컨슈머로 토픽을 조회해 보자.
```
./kafka-console-consumer --bootstrap-server localhost:9092 --topic kafka.scaling --from-beginning
```
- '배송수정' 이벤트에 이어 '배송취소' 이벤트가 일어난 순서대로 퍼블리시 되었다. 
- 데이터베이스를 조회해 보자.
```
select * from Delivery_table;
```
- 최종 상태인 배송취소('DELIVERY CANCELLED') 상태가 조회된다.


- 주문수정과 주문취소가 (Key 설정으로) 동일한 파티션으로 퍼블리쉬 되었다. 
- 해당 파티션에 매칭된 배송 서비스가 순서대로 처리해 처리순서가 보장됨을 알 수 있다.




