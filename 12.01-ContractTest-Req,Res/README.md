## Contract Test 

주문서비스와 상품서비스간 계약(Contract)테스트를 통해 마이크로서비스와 같은 분산 환경에서 API 일관성이 유지되는 실습을 수행한다.  

### 테스트 서비스 기동

- 서비스 정상 작동 확인
- 상품서비스(8085)와 주문서비스(8081)를 각각 콘솔을 열어 기동한다.
```sh	
cd contract-test

cd orders
mvn clean
mvn spring-boot:run

cd products
mvn clean
mvn spring-boot:run
```

- 주문을 한다.
```	
http http://localhost:8081/orders productId=2 quantity=3 customerId=1@uengine.org
```

- 상품팀의 일방적 API 수정으로 계약(Contract) 위반 사항 만들기
	- 주문서비스에서 주문을 할때, 상품서비스의 api 를 호출한다.
		- Order.java 파일(45행)의 restTemplate.getForEntity 확인
		- http://상품서비스/product/productId

- 상품서비스에서 해당 api 를 item 으로 변경한다.
> - 상품서비스의 ProductController.java 확인
> - 15행에서 @GetMapping("/product/{productId}") 을
> - @GetMapping("/item/{productId}") 으로 변경

- 상품서비스를 재시작 하고 주문해 본다.

```
http http://localhost:8081/orders productId=2 quantity=3 customerId=1@uengine.org
```
- 404 에러 발생!!

### CDC(Consumer Driven Contract) 계약 체결
- Consumer가 참조하는 코드를 Provider 일방적인 수정방지를 위한 Contract 적용
- Consumer인 주문 서비스(Consumer) 개발자가 주도적으로 계약서(Contract)를 작성한다.
- order 서비스 최상위 루트 폴더에 productGet.groovy 파일이 작성되었다.
- 작성된 계약서는 Groovy 기반으로 다음과 같다.
``` groovy
Contract.make {
    request {
        method 'GET'
        url ('/product/1')
        headers {
            contentType(applicationJson())
        }
    }
    response {
        status 200
        body(
                id: 1,
                name: "TV",
                price: 10000,
                stock: 10,
                imageUrl: "testUrl"
        )
        bodyMatchers {
            jsonPath('$.id', byRegex(nonEmpty()).asLong())
            jsonPath('$.name', byRegex(nonEmpty()).asString())
            jsonPath('$.price', byRegex(nonEmpty()).asLong())
            jsonPath('$.stock', byRegex(nonEmpty()).asLong())
            jsonPath('$.imageUrl', byRegex(nonEmpty()).asString())
        }
        headers {
            contentType(applicationJson())        }
    }
}
```
- productGet.groovy 파일을 복사하여서, product 서비스의 test/resources/contracts/rest 폴더에 복사를 한다.
> - 실제로는 Git 환경에서 PR(Pull Request)을 요청하고 이를 상품팀이 수락한다.
> - (contracts/rest 폴더는 없기때문에 새로 만들어야 한다.)
> - (contracts/rest 폴더를 만드는 이유는 productGet.groovy 파일에 package contracts.rest 라고 선언했기 때문이다.)

- 계약에 의해서 product 서비스에서 Test, or Package 실행단계에서 에러가 발생한다.
- product 서비스의 package 명령을 호출한다.
```sh
cd products
mvn clean package
```
- test fail 에러 발생!!
![image](https://user-images.githubusercontent.com/35618409/231977809-da0f6a45-0e9f-4de2-b325-c812ae65a032.png)

> Consumer와 체결한 계약(Contract)을 위반하여 상품팀에서는 빌드단계에서부터 실패하게 된다. 
> 계약서에 명시된 응답 속성이 하나라도 리턴되지 않아도 빌드는 실패한다. 
> ProductService.java > 74라인 주석 후 재빌드해 보자

- 계약 위반을 해결하기 위하여 product 서비스는 기존의 /product 라는 api 를 유지 해야한다.
-  product 서비스의 ProductController.java 에서 

```
   @GetMapping("/v2/item/{productId}")
    Product productStockCheck_v2(@PathVariable(value = "productId") Long productId) {
        return productStockCheck(productId);
    }

    @GetMapping("/product/{productId}")
    Product productStockCheck(@PathVariable(value = "productId") Long productId) {

        return  this.productService.getProductById(productId);
    }


```
위의 같이 기존 API를 준수하면서 신규 API 추가할 수 있도록 변경한다.
	- product 서비스의 package 명령을 호출해 본다.
```sh
cd products
mvn clean package
```
- 테스트 성공 및 jar 파일 생성 완료!!

- Product 서비스는 Contract(계약) 기반의 테스트 코드를 자동생성하여 테스트를 수행한다.
- 이를 위해 contract-verifier 디펜던시가 pom.xml에 추가되었다.
- contract-test > products > pom.xml을 열어본다.
```
<!-- Auto generating test Client based on CDC. -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-starter-contract-verifier</artifactId>
	<version>${spring-cloud-contract.version}</version>
	<scope>test</scope>
</dependency>
```
- 자동 생성된 Test Code는 target > generated-test-sources > contracts 에서 확인된다.

### 주문서비스에서 테스트

- 주문서비스는 상품서비스의 실행여부와 상관없이 언제든지 테스트를 수행하고 배포할 수 있다.
- 주문서비스가 상품서비스의 api 를 테스트 하기 위해서는 상품서비스에서 stub 파일을 제공해 주어야 한다.
	- 상품 서비스에서 mvn install 을 하여 stub 파일을 Local(.m2 folder)이나 Remote(mvn deploy) 리파지토리에 저장한다.
```sh
cd products
mvn install
```

- 주문서비스에서는 상품서비스에서 만들어진 stub 파일(Mock Server)을 바라보며 테스트를 진행한다.

- 이를 위해 wiremock, stub-runner 디펜던시가 pom.xml에 추가되었다.
- contract-test > orders > pom.xml을 열어본다.
```
<!-- Client Sdie Contract dependencies  -->
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-contract-wiremock</artifactId>
	<version>${spring-cloud-contract.version}</version>
	<scope>test</scope>
</dependency>
<dependency>
	<groupId>org.springframework.cloud</groupId>
	<artifactId>spring-cloud-contract-stub-runner</artifactId>
	<version>${spring-cloud-contract.version}</version>
	<scope>test</scope>
</dependency>	
``` 

- order 서비스의 test/java/com.example.template/ProductContractTest.java 파일을 확인한다.
  - 생성된 Stub을 로컬에서 Mock서버로 8090포트로 실행한다. (@AutoConfigureStubRunner)
  - 또한, 테스트 환경은 'test' 프로파일을 사용한다.
```
...
...
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
@AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, 
                         ids = "com.example:products:+:stubs:8090")
@ActiveProfiles("test")                      
public class ProductContractTest {
...
```
  
- @Test Code는
	- OrderController에 등록된 /order/validateProduct/1 URL을 호출하고,
	- OrderController에서는 'test' profile에 설정된 Local Mock 서버(http://localhost:8090/product/1)로 재고수량 체크를 위한 Get 요청을 보낸다.
	- (contract-test > orders > src > test > resources > application.yaml)
```
    @Value("${api.url.product}")
    private String apiUrl;
```
	- 이처럼 주문서비스의 Biz 로직을 활용해 배포 시, 상품서비스 Stub을 통해 참조하는 Product의 API 테스트를 수행한다.
	- (Test 시엔 Stub URL을, 클라우드 환경에서는 Cloud URL을 profile로 관리)
```sh
cd orders
mvn clean test
```	
- Test 로그를 보면, Mock Server에 대해 Request와 Response가 성공적으로 실행되었고,
![image](https://user-images.githubusercontent.com/35618409/232052587-c8136327-8fdb-4e78-84e1-6c6833b2a852.png)

- 전체 Contract 테스트도 성공적으로 종료되었다.
![image](https://user-images.githubusercontent.com/35618409/232053136-6e875302-a95f-48c0-a444-2c0b2ea8da0d.png)

- Consumer Side(주문 서비스) 파이프라인에서 테스트 성공이 확인되어야 다음 작업(도커라이징, 컨테이너라이징)이 진행된다.



