## 마이크로서비스 구현 및 동작원리 이해

이벤트스토밍 결과를 Cloud Native Application(CNA) 구축 시 많이 적용되는 Spring Boot(스프링 부트)와 Event-Driven 방식을 사용하여, 온-사이트에 적용가능한 코드 구현을 진행한다.
모델기반 Demo 예제를 통해서 본교육에 활용되는 마이크로서비스가 어떻게 구현되고 동작되는지 확인한다. 

- 스프링 부트를 처음 시작할 때 https://start.spring.io/ 에서 시작할 수 있다. 브라우저에서 접속해 보자.
- Lab들은 Maven Project,Java, 그리고 디펜던시(Dependencies)에 아래 3개가 추가 구성된다.
  - Rest Repositories : 레파지토리 패턴을 통해 CRUD Rest API를 생성해 줍니다.
  - Spring Data JPA : Java Persistence API의 약어, 자바 ORM 기술에 대한 표준 명세로 API 기반 영속성 관리 도구
  - H2 : Java 기반 오픈소스 인메모리 DB

 
### 대상 모델 
![image](https://user-images.githubusercontent.com/35618409/191653973-4f83ca3c-a01e-474c-b986-7ced9f1233bc.png)

#### 메뉴 CODE > Project IDE를 클릭하여 해당 모델의 준비된 GitPod 환경으로 진입한다.

-  시간이 지나 오픈됀 GitPod의 Explorer 영역에서 demo 폴더를 사용한다. 
-  아래는 이벤트스토밍 모델 기반 Sprinb-boot 템플릿 코드의 구현체를 설명하고 있다. 


### 1. 먼저, Aggregate를 생성합니다.

- Aggregate 는 Doamin State를 저장하는 리파지토리 기능을 포함하고 있습니다. 
- Product Class 를 생성 합니다.
- 상품 Entity 를 id, name, stock 맴버 변수를 가진 정의하고 get,set 메서드를 생성하여 줍니다.
- 클레스 상단에 @Entity 어노테이션을 달아서 Aggregate 선언을 하여 줍니다.
- @Entity 어노테이션은 JPA 방식을 사용합니다. 이는 Id 값이 필수입니다.
- id 로 선언한 변수에 @Id @GeneratedValue 를 선언하여 줍니다.

```java
package com.example.demo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {

    @Id @GeneratedValue
    Long id;
    String name;
    int stock;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    
}
```

### 2. Command를 생성합니다.

- ProductRepository interface를 CrudRepository<Product, Long> 를 extends 하여 생성합니다.
- CrudRepository<> 의 두개의 변수는 Entity Type과 Primary Key(Entity Id) Type 입니다.
- 아래와 같이 선언하면, Entity 의 Repository 패턴으로 Product 엔터티의 CRUD에 준하는 Rest API가 자동으로 생성됩니다.

```java 
package com.example.demo;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
```

### 3. REST API로 커맨드(Command)를 실행해 보자.

- 스프링부트 기반 마이크로서비스를 실행한다.
```
mvn spring-boot:run

```
- 메이븐 명령어로 spring-boot 라는 플러그인의 run 명령어를 실행 합니다.
- spring-boot 플러그인은 pom.xml 파일에 설정되어 있습니다.

- 실행시 기본 포트인 8080 으로 실행됩니다.
- http 명령으로 localhost:8080 을 호출하여 봅니다.

```bash 
http GET localhost:8080
http GET http://localhost:8080/products
http POST localhost:8080/products name=TV stock=10
http GET http://localhost:8080/products/1
http PATCH http://localhost:8080/products/1 stock=20
http DELETE http://localhost:8080/products/1
http GET http://localhost:8080/products/1
```

- 파일 두개만 만들었지만 Aggregate 와 CRUD가 가능한 Command 가 실행되는 것을을 확인할 수 있습니다.

### 4. Event를 생성합니다.

- 이벤트는 일어난 사실에 대한 결과이기 때문에 과거분사(PP, Past Participle) 형으로 작성 합니다.
- 상품 정보가 변경 되었을 때 변경 사실을 알리는 ProductChanged 이벤트를 만들어 봅니다.
- ProductChanged 클레스를 생성하고, 변수를 설정합니다.
- 이벤트는 다른 서비스에서 받아보는 정보입니다. 그렇기 때문에 자세하게 적어주어야 할 필요가 있습니다. json 으로 데이터를 보내기 때문에 eventType 이라는 변수를 만들고, 생성자에서 이벤트 이름을 적어 줍니다.
- 세부 정보도 다른 서비스에서 명확히 이해하기 쉽도록 그냥 name 이 아닌 productName 처럼 구체적으로 작성 합니다.

```java
package com.example.demo;
public class ProductChanged {
    String eventType;
    Long productId;
    String productName;
    int productStock;

    public ProductChanged(){
            this.eventType = this.getClass().getSimpleName();
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductStock() {
        return productStock;
    }

    public void setProductStock(int productStock) {
        this.productStock = productStock;
    }
}
```

### 5. 생성된 Event를 발송합니다.

- 이벤트는 Aggregate 내의 상태 변화에 의해서 발생하기 때문에, 이벤트를 보내는 로직은 Entity의 lifecycle 에 작성하게 됩니다.
- Product.java 에 데이터가 입력되었을때의 Lifecycle 인 @PostPersist 어노테이션에 이벤트를 생성하여 값을 셋팅 합니다.
- ObjectMapper 를 사용하여 json 으로 변환 합니다.
- 어그리게잇(Product.java) 코드 수정

- import 문 추가 
```
import javax.persistence.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
```

```java
@PostPersist
    public void eventPublish(){
        ProductChanged productChanged = new ProductChanged();
        productChanged.setProductId(this.getId());
        productChanged.setProductName(this.getName());
        productChanged.setProductStock(this.getStock());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = null;

        try {
            json = objectMapper.writeValueAsString(productChanged);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("JSON format exception", e);
        }
    System.out.println(json);
}
```

- 서비스를 재시작 후 Aggregate 에 커맨드를 실행하여 정상적으로 json이 생성되는지 콘솔을 통해 확인합니다.
- http POST localhost:8080/products name=TV stock=10
```
{"eventType":"ProductChanged","productId":1,"productName":"TV","productStock":10}
```
- 어그리게이트 내의 라이프사이클을 수정 적용해 본다.
```
@PostPersist > @PostUpdate
```

### 6. 서비스에 카프카 연결하기

- Spring Cloud Stream Application 모델
![image](https://user-images.githubusercontent.com/43136526/119310820-4354ea00-bcab-11eb-8309-7f8431ad1715.png)

- Spring Cloud Streams Application에서 Kafka 바인더를 사용하기 위한 Kafka 라이브러리를 pom.xml에서 확인한다.

```xml
<!-- kafka streams -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-stream-kafka</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

- stream 을 kafka 와 연결하기 위하여 application.yaml 파일에 아래 설정을 추가 합니다.
- kafka brokers로 localhost:9092 를 사용한다는 의미입니다. 카프카 설치시 기본 포트가 9092 입니다.

- destination 은 목적지라는 뜻인데, kafka 에서는 topic 이름이 됩니다.
- 즉, 해당 설정은 shop 이라는 토픽에 메세지를 주고 받겠다는 의미입니다.

- 환경정보 파일인 resouces/applications.properties를 application.yml로 변경 후, 아래 내용을 추가합니다.

```yaml
spring:
  cloud:
    stream:
      kafka:
        binder:
          brokers: localhost:9092
      bindings:
        input:
          group: product
          destination: shop
          contentType: application/json
        output:
          destination: shop
          contentType: application/json   
```

### 7. 이벤트를 kafka 에 퍼블리시 하기

- 7번에서 추가한  Product.java 리소스의 @PostPersist 라이프사이클을  스트림에 메세지를 발송하는 코드로 수정합니다.
- 어그리게잇(Product.java) 코드 수정
- import 문 추가 

```
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.MimeTypeUtils;
```

```java
@PostPersist
public void eventPublish(){
    ProductChanged productChanged = new ProductChanged();
    productChanged.setProductId(this.getId());
    productChanged.setProductName(this.getName());
    productChanged.setProductStock(this.getStock());
    ObjectMapper objectMapper = new ObjectMapper();
    String json = null;

    try {
json = objectMapper.writeValueAsString(productChanged);
} catch (JsonProcessingException e) {
throw new RuntimeException("JSON format exception", e);
}

    Processor processor = DemoApplication.applicationContext.getBean(Processor.class);
    MessageChannel outputChannel = processor.output();

    outputChannel.send(MessageBuilder
.withPayload(json)
.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
.build());
}
```

- 수정 후 서비스를 재시작한 다음 REST API로 상품 등록 시, 카프카에 이벤트 메시지가 도달하는지 확인 합니다.
- 메시지는 Kafka Consumer로써 shop 토픽(topic) 모니터링으로 확인 가능합니다.

### 8. 카프카 토픽 모니터링

- kafka 유틸리티가 포함된 위치에 접속하기 위하여 새 터미널에서 docker 를 통하여 shell 에 진입한다:
```
cd kafka
docker-compose exec -it kafka /bin/bash 
cd /bin
```

- kafka Consumer에서 이벤트 확인한다
``` 
./kafka-console-consumer --bootstrap-server localhost:9092 --topic shop  --from-beginning
```

- 다른 터미널에서 이벤트 발행해 본다.
```sh
http POST localhost:8080/products name=TV stock=10
```

### 9. 이벤트를 수신하는 Policy를 생성

- Event에 대응되는 Policy(폴리시)는 다른 마이크로서비스(팀)에서 수신 합니다.
즉, 상품 서비스에서 ProductChanged 이벤트가 발생하면 주문이나 배송 서비스에서 이를 수신 후 각 서비스에 맞는 Biz-Logic을 처리하지만, 편의상 Kafka로부터 메세지 수신만 확인합니다.
- PolicyHandler.java 를 생성하고 @StreamListener(Processor.INPUT) 를 추가하여 스트림을 수신합니다.

```java
package com.example.demo;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.cloud.stream.messaging.Processor;

@Service
public class PolicyHandler {
    @StreamListener(Processor.INPUT)
    public void onEventByString(@Payload ProductChanged productChanged) {
        System.out.println(productChanged.getEventType());
        System.out.println(productChanged.getProductName());
        System.out.println(productChanged.getProductStock());
    }
}
```

- String 이 아닌 객체 자체를 받아도 StreamListener 에서 객체 변환을 하여 줍니다.
- 위의 카프카에 데이터를 보내는 명령을 호출하여 메세지를 수신하는지 확인 합니다
