

### Kafka 스케일링 

카프카 컨슈머 그룹의 인스턴스를 늘여 처리량을 늘린다. 이때 카프카 파티션 추가(Scaling)하여 컨슈머와 카프카 파티션 사이의 매칭관계에 대해 학습한다.

#### Kafka Partition vs. Consumers
 
- Kafka Topic 생성시, default partition은 1개로 생성된다. 
- kafka에서 하나의 Partition은 반드시 하나의 Consumer가 매칭되어 메시지를 소비한다. 
- Partiton 수보다 동일한 Group id를 가진 Consumer 수가 많다면 일부 Consumer들은 partition에 binding되지 못해 message를 Polling 하지 못하는 현상이 일어난다. 
- 아래의 Instruction을 따라 일부 Consumer가 메시지를 poll 해오지 못하는 현상을 확인한다.

- Order 서비스 시작
```bash
cd order
mvn spring-boot:run
```
- inventory 서비스 시작 (port=8082)
```bash
cd inventory
mvn spring-boot:run
```
- inventory 2 서비스 시작 (port=8083)
```bash
cd inventory
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8083
```
> inventory1 서비스와는 달리 inventory2 마이크로서비스의 Console 창을 통해 파티션 할당이 일어나지 않았음을 확인할 수 있다.
> partitions assigned: []

- Consumer 그룹정보를 확인한다.
```
cd kafka
docker-compose exec -it kafka /bin/bash
cd /bin

./kafka-topics --bootstrap-server 127.0.0.1:9092 --topic labshoppubsub --describe

./kafka-consumer-groups --bootstrap-server localhost:9092 --describe --group inventory
```
> Inventory Group 의  Consumer (마이크로서비스 레플리카)가 2개임에도 파티션이 1개이므로, 매칭된 Consumer가  1개로 확인된다.

- 실험을 위하여 8082, 8083 의 inventory 에 각각 10개의 재고를 등록한다:
```
http :8082/inventories id=1 stock=10
http :8083/inventories id=1 stock=10
```
- 주문 4건을 등록한다:
```
http :8081/orders productId=1 qty=1
http :8081/orders productId=1 qty=1
http :8081/orders productId=1 qty=1
http :8081/orders productId=1 qty=1
```
- 해당 주문에 대한 재고 감소가 8082 혹은 8083 한 곳에서만 발생한 것을 확인한다:
```
http :8082/inventories/1
http :8083/inventories/1
```

#### Kafka Partition Scale out 

- Kafka Partition을 확장한다. 

```sh 
./kafka-topics --bootstrap-server 127.0.0.1:9092 --alter --topic labshoppubsub -partitions 2
```

- Inventory2 마이크로서비스를 재시작하거나 2~3분 정도 기다리면 Partition Rebalancing이 일어나면서 Inventory2 서비스도 partition assigned로 바뀌며 message를 Polling할 수 있는 상태로 변경된다.

- 토픽정보와 Consumer Group 정보를 재확인한다.

> Partition 0,1 각각에 Consumer가 매핑된 것을 확인할 수 있다.



- Order 서비스에 POST로 메시지를 발행하면 Inventory 1, Inventory 2 서비스가 차례로 메시지를 수신하기 때문에 8082, 8083이 나누어서 재고량이 1씩 감소된다.

```
http :8081/orders productId=1 qty=1
http :8082/inventories
http :8083/inventories

http :8081/orders productId=1 qty=1
http :8082/inventories
http :8083/inventories

http :8081/orders productId=1 qty=1
http :8082/inventories
http :8083/inventories

```
> 물론, 실제 inventory 이 production 될때는 같은 데이터베이스를 사용하도록 production 될 것이기 때문에 재고량의 차이가 생기지는 않는다.
