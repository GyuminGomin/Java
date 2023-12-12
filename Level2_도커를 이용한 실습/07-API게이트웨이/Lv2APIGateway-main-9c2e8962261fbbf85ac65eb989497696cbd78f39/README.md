
### API Gateway를 사용하여 마이크로 서비스들의 엔드포인트 단일화

마이크로서비스들의 단일 진입점인 API Gateway를 실행하고, Gateway를 통해 백엔드 다운스트림으로 라우팅해 본다.
(실습 환경에 Kaka runtime이 실행되고 있어야 한다.)

- monolith 마이크로 서비스를 실행한다.
```
cd monolith
mvn spring-boot:run
```

- gateway 마이크로 서비스를 실행한다.
```
cd gateway
mvn spring-boot:run
```

- 기동된 monolith 서비스를 호출하여 주문 1건을 요청한다.
```
  http localhost:8081/orders productId=1 qty=3
  http localhost:8081/orders
```
    
- 게이트웨이를 통하여 같은 url 을 port 를 변경하여 실행한다.
```
 http localhost:8088/orders productId=1 qty=1
 http localhost:8081/orders  # can find the order item here
 http localhost:8088/orders  # can find the order item here also
```
  
- inventory 마이크로 서비스를  실행한다.
- 게이트웨이서비스의 application.yaml 의 spring.cloud.gateway.routes 에 아래 설정을 추가하여 inventory 서비스로의 라우팅을 추가한다. (indent 에 주의해주세요)
```yaml
      - id: inventory
        uri: http://localhost:8082
        predicates:
          - Path=/inventories/** 
```

- 게이트웨이 서비스를 재기동 한다.
- 8082 포트로  서비스를 호출하여 보고, 게이트웨이를 통하여 서비스를 호출한다.  
```
http localhost:8082/inventories
http localhost:8088/inventories
```

