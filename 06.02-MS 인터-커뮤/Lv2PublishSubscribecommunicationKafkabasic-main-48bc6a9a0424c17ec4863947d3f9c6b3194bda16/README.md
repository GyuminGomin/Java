## Kafka 기본 명령 이해하기

- VSCode 를 실행한다
- kafka 를 docker를 통하여 실행한다:
```
cd kafka
docker-compose up
```
> kafka 는 zookeeper 서버와 함께 2개의 프로세스로 기동된다.
> docker-compose file 은 하나 이상의 docker 서비스를 실행관리할 때 사용된다.

- kafka 유틸리티가 포함된 위치에 접cd속하기 위하여 docker 를 통하여 shell 에 진입한다:
```
cd kafka
docker-compose exec -it kafka /bin/bash
```
```
```
```
### 토픽생성
- replication 1개, partition 1개로 example이라는 토픽을 생성합니다.
```

### 토픽생성
- replication 1개, partition 1개로 example이라는 토픽을 생성합니다.
```bash
./kafka-topics --bootstrap-server http://localhost:9092 --topic example --create --partitions 1 --replication-factor 1
```
> --create : 생성옵션 
> --bootstrap-server : 토픽을 생성할 카프카 클러스터를 구성하는 브로커들의 IP와 port
> --replication-factor : Topic의 파티션을 복제할 복제 개수(2이면 1개의 복제본을 사용한다는 의미)
> --partitions : 파티션 개수
> --config는 추가적인 설정(e.g. retention.ms는 데이터 유지기간(ms) 86400000ms = 1일)


### 토픽 리스트 보기
- 새로운 터미널 창에서 Console Consumer를 실행한다.
```
cd kafka
docker-compose exec -it kafka /bin/bash
```
```bash
cd /bin
./kafka-topics --bootstrap-server http://localhost:9092 --list --exclude-internal   
```
> --list : 조회옵션
> --exclude-internal : Kafka 내부 관리를 위한 Topic을 조회 대상에서 제외


### 토픽 상세 조회
```
./kafka-topics --bootstrap-server http://localhost:9092 --topic example --describe 
```
> --describe 옵션
> 파티션 개수, 복제된 파티션이 위치한 브로커의 번호, 기타 토픽을 구성하는 설정 출력


### 토픽 옵션 수정
- 토픽의 파티션 개수를 2개로 늘린다.
```
./kafka-topics --bootstrap-server http://localhost:9092 --topic example --alter --partitions 2 
./kafka-topics --bootstrap-server http://localhost:9092 --topic example --describe 
```
> 토픽의 파티션 수는 증가만 가능하고, 감소는 불가능



### Kafka producer 연결 후 메세지 Publish
- 새로운 터미널 창에서 Console Producer를 실행한다.
```
cd kafka
docker-compose exec -it kafka /bin/bash
```
```bash
cd /bin
./kafka-console-producer --broker-list http://localhost:9092 --topic example
```

### Kafka consumer 연결 후 메세지 Subscribe
- 새로운 터미널 창에서 Console Consumer를 실행한다.
```
cd kafka
docker-compose exec -it kafka /bin/bash
```
```bash
cd /bin
./kafka-console-consumer --bootstrap-server http://localhost:9092 --topic example --from-beginning
```
> 브로커에 전송된 데이터를 폴링(polling)하여 데이터 확인
> --from-beginning : 가장 처음 offset 데이터부터 폴링(polling)
> 'hello world'' 라는 메세지 publish 해보기 



### Kafka Consumer Group & Offsets

- Consumer group 목록
```bash
./kafka-consumer-groups --bootstrap-server http://localhost:9092 --list
```


> --decribe 옵션으로 그룹에 대한 상세 내용 조회


### Consumer group 의 offset 확인
```bash
./kafka-consumer-groups --bootstrap-server http://localhost:9092 --group <group_id> --describe
```
> --decribe 옵션으로 그룹에 대한 상세 내용 조회


### Consumer group 의 offset 재설정
```bash
./kafka-consumer-groups --bootstrap-server http://localhost:9092 --group <group_id> --topic example --reset-offsets --to-earliest --execute
```
> By default, the offset retention lifetime is set to 7 days. If a consumer group has not committed any offsets for more than 7 days, the offset information will be deleted and the group will be considered inactive.

> There are many other resetting options, run kafka-consumer-groups for details:

```bash
 --shift-by <positive_or_negative_integer>
 --to-current
 --to-latest
 --to-offset <offset_integer>
 --to-datetime <datetime_string>
 --by-duration <duration_string>
```

