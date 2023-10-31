### Compensation and Correlation

어떠한 이벤트로 인하여 발생한 변경사항들에 대하여 고객이 원하거나 어떠한 기술적 이유로 인하여 해당 트랜잭션을 취소해야 하는 경우 이를 원복하거나 보상해주는 처리를 Compensation 이라고 한다. 그리고 해당 취소건에 대하여 여러개의 마이크로 서비스 내의 데이터간 상관 관계를 키값으로 연결하여 취소해야 하는데, 이러한 관계값에 대한 처리를 Correlation 이라고 한다. 



### 실습 시나리오

이전 랩에서 주문을 생성하는 OrderPlaced 라는 이벤트를 발행하였다.  
이번 랩에서는 주문서비스에서 주문을 취소하는 OrderCancelled 라는 이벤트를 발행 하고,  Inventory 에서는 해당 주문량에 대한 재고량을 다시 원복하는 Compensation을 수행한다. inventory 에 대해서는 해당 주문의 상품 id 를 상관관계 키 (Correlation Key)로 초기 주문되었던 개수만큼을 다시 추가하는 방법으로 복구가 이루어진다. 


### 작업순서  

#### 이벤트 스토밍 
- "cancel" Command 의 부착. 이때 해당 이벤트는 Order Aggregate 의 왼쪽에 인접하도록 부착한다.
- cancel command의 설정창을 열고(더블클릭) http method 로 "DELETE" 를 선택한다.
- "OrderCancelled" Event 를 부착한다. cancel command 에 따라서 OrderCancelled 라는 이벤트를 발행하도록 Aggregate 의 우측편에 인접하도록 부착한다.
- OrderCancelled Event 의 속성을 Aggregate 의 것에서 복사한다 (Sync Attributes 클릭)
- cancel command 와 OrderCancelled event 를 선으로 연결한다.
- inventory bounded context 내에 Policy 를 추가하고 이름을 "increase stock" 으로 설정한다.
- OrderCancelled Event 와 "increase stock" Policy 를 연결한다.

#### 코드의 생성

- order/../ Order.java
```
   @PreRemove
    public void onPreRemove() {
        OrderCancelled orderCancelled = new OrderCancelled(this);
        orderCancelled.publishAfterCommit();
    }
```

- order/../   OrderCancelled.java 와 inventory/../ OrderCancelled.java
```
package labshopcompensation.domain;

import java.util.*;
import labshopcompensation.domain.*;
import labshopcompensation.infra.AbstractEvent;
import lombok.*;

@Data
@ToString
public class OrderCancelled extends AbstractEvent {

    private Long id;
    private String productId;
    private Integer qty;
    private String customerId;
    private Double amount;
    private String status;
    private String address;

    public OrderCancelled(Order aggregate) {
        super(aggregate);
    }

    public OrderCancelled() {
        super();
    }
    // keep

}

```
- inventory/../ PolicyHandler.java 의 wheneverOrderCancelled_increaseStock method
```
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='OrderCancelled'"
    )
    public void wheneverOrderCancelled_IncreaseStock(
        @Payload OrderCancelled orderCancelled
    ) {
        OrderCancelled event = orderCancelled;
        System.out.println(
            "\n\n##### listener IncreaseStock : " + orderCancelled + "\n\n"
        );

        // Sample Logic //
        Inventory.increaseStock(event);
    }

```

- inventory/../ Inventory.java 의 increaseStock method
```
    public static void increaseStock(OrderCancelled orderCancelled) {

        /** fill out following code  */

    }
```

### 유틸리티 설치 (필요시)
- httpie 와 kafka
```
pip install httpie

cd kafka
docker-compose up
```

####  주문 취소와 이벤트 확인
- 생성된 코드를 반영한 후, 주문을 서비스를 재기동 한다.

- 초기 재고량 설정
```
http :8082/inventories id=1  stock=10
```

- 다음 명령으로 주문 생성한다:     
```
http localhost:8081/orders productId=1 productName=TV qty=3
```
- 주문에 의한 재고량 확인:
```
http :8082/inventories/1    # stock=7
```

- 주문을 취소한다.
```
http DELETE localhost:8081/orders/1
```
- 취소에 의한 재고량 확인:
```
http :8082/inventories/1    # stock=10
```

- Kafka consumer 를 이용하여 OrderCancelled 이벤트가 발행되는 것을 확인한다.

```
cd kafka
docker-compose exec -it kafka /bin/bash
cd /bin

./kafka-console-consumer --bootstrap-server localhost:9092 --topic labshopcompensation --from-beginning
```




#### inventory 서비스의 구현
- inventory의 Aggregate 인 Inventory.java  에 increaseStock method 를 다음과 같이 구현한다:
```
    public static void increaseStock(OrderCancelled orderCancelled) {

        repository().findById(Long.valueOf(orderCancelled.getProductId())).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() + orderCancelled.getQty()); 
            repository().save(inventory);


         });

    }


```

### 확장시나리오: 배송서비스에서 주문 삭제시 배송을 취소하는 작업  

