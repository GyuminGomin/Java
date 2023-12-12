## Messaging Contract Test

- 상품서비스와 주문서비스간 메시징 기반 EDA 통신을 Testing하는 방법을 실습한다.
- Producer와 Consumer만 있는 REST Contract Test와는 달리, Message Broker 요소가 Contract Test에 추가된다.
- 즉, CDC Messaging Contract에는 Producer 측의 Biz 로직이 Message Broker에 이벤트를 제대로 Pubish하는지 확인하는 테스트가 추가된다.

### Dependencies for Messaging Contract

- contract-test > products > pom.xml을 열어본다.
```
<!-- Support testing your microservice without connecting to a messaging system. -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-stream-test-support</artifactId>
	<scope>test</scope>
</dependency>

<!-- Auto generating test Client based on CDC. -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-contract-verifier</artifactId>
	<version>${spring-cloud-contract.version}</version>
	<scope>test</scope>
</dependency>
```

### CDC Messaging Contract

#### Contract 파일을 살펴보자.
- products > src > test > resources > contract > messaging > productChaged.groovy

```
package contracts.messaging
import org.springframework.cloud.contract.spec.Contract

Contract.make {
    // The Identifier which can be used to identify it later.
    label 'ProductChanged'
    input {
        // Contract will be triggered by the following method.
        triggeredBy('productChanged()')
    }
    outputMessage {
        sentTo 'eventTopic'
        // Consumer Expected Payload spec. that a JSON message must have, 
        // If the Producer-side test is OK, then send the following msg to event-out channel.
        body(
                eventType: "ProductChanged",
                productId: 1,
                productName: "TV",
                productPrice: 10000,
                productStock: 10,
                imageUrl: "tv.jpg"
        )
        bodyMatchers {
            jsonPath('$.eventType', byRegex("ProductChanged"))
            jsonPath('$.productId', byRegex(nonEmpty()).asLong())
            jsonPath('$.productName', byRegex(nonEmpty()).asString())
            jsonPath('$.productPrice', byRegex(nonEmpty()).asLong())
            jsonPath('$.productStock', byRegex(nonEmpty()).asLong())
            jsonPath('$.imageUrl', byRegex(nonEmpty()).asString())
        }
        headers {
            messagingContentType(applicationJson())
        }
    }
}

``` 

#### Test Base 코드를 살펴보자.
- products > src > test > java/com/example/template > MessagingBase.java
> 상품서비스 비즈니스 로직 ProductController를 사용해 테스트 메시지를 생성한다.
> 메시지는 Kafka Mock Server에 MessageVerifier 인터페이스를 통해 퍼블리쉬한다.

> 이후, 생성될 테스트 코드에서 이를 수신해, 비즈니스 로직이 제대로 수행되었는지 검증한다.  


### Contract Test 실행

- 성공적인 계약테스트를 확인해 본다.
```
cd products
mvn clean test
```
- 아래처럼 Producer에서 Message 테스트가 성공적으로 실행되는게 확인된다.
![image](https://user-images.githubusercontent.com/35618409/232287767-36048901-7b2c-4996-8c9b-92432860939c.png)


### 계약 위반 테스트

- 상품팀에서 ProductChanged 도메인 이벤트명을 임의로 "ProductModified"로 바꾼다.
```
ProductService.java > 103라인 주석해제 
mvn clean test
```

- 계약 위반에 따라 빌드오류로 배포가 진행되지 않는다.
![image](https://user-images.githubusercontent.com/35618409/232288290-20db278d-413e-4df8-bef4-0dfbd25dc062.png)

- 다시 빌드 후 Stub을 생성 해 Maven 라이브러리에 배포한다.
```
ProductService.java > 103라인 주석처리 
mvn clean install
```

### Consumer (주문서비스) 테스트

- 주문서비스의 테스트를 위한 디펜던시와 테스트 코드(ProductChangedContactTest)를 확인한다.
  - orders > pom.xml
```
<!-- test -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-stream-test-support</artifactId>
	<scope>test</scope>
</dependency>

<!-- tag::stubrunner[] -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-contract-stub-runner</artifactId>
	<scope>test</scope>
</dependency>
```

- 주문 컨슈머의 최종 메시지 테스트를 수행한다.
```
cd orders
mvn clean test
```

- 메시지 테스트가 성공적으로 수행됨이 확인된다.
![image](https://user-images.githubusercontent.com/35618409/232289407-a6119401-b132-4661-9c60-621d640c2ea3.png)

