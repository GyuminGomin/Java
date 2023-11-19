
### CQRS(Command and Query Responsibility Segregation) 모델링 
 
주문서비스와 배송서비스의 설계 모델을 참조하여 읽기 전용의 쿼리 모델(Query Model)을 설계하고 실행해 본다.

#### SCENARIO
- 고객센터팀이 신설되어 '마이페이지' 서비스를 런칭하는 플랜을 세웠다.
- '마이페이지'는 쪼개어 관리되는 주문정보와 배송정보를 병합하여 사용자들에게 제공되어야 한다.


#### CQRS 모델링 How to
CQRS 모델링은 이벤트스토밍 교구를 통해 아래처럼 수행단계를 가진다.

- customercenter BC(Bounded Context) 를 추가
- Read Model 녹색 스티커 추가('MyPage')
- Read Model 속성 Define
> Long orderId 
> String productId
> String deliveryStatus
> String orderStatus

<img width="982" alt="image" src="https://user-images.githubusercontent.com/487999/191055790-5d6a529f-e2f7-49ab-8ee0-74d371f06090.png">

- Read Model CRUD 상세설계

<img width="434" alt="image" src="https://user-images.githubusercontent.com/487999/191056403-fbdec62b-42ea-4261-8e4e-b631c6c6779a.png">


#### Code Download 
- 소스 코드를 내 로컬에 다운로드하여 사용하는 IDE 도구에 로딩한다.


#### Complete Service codes
- 배송 마이크로서비스의 도메인 코드를 완성한다.
- Delivery.java > addToDeliveryList Port method를 아래 코드로 붙여 넣는다.
```
Delivery delivery = new Delivery();
delivery.setAddress(orderPlaced.getAddress());
delivery.setQuantity(orderPlaced.getQty());
delivery.setCustomerId(orderPlaced.getCustomerId());
repository().save(delivery);
```

- 고객 마이크로서비스의 MyPage.java의 Id 자동생성을 설정한다.
```
 @GeneratedValue(strategy=GenerationType.AUTO)  // 주석해제
```

#### 마이크로서비스 실행
- 주문, 배송, 고객센터 마이크로서비스를 각각 실행한다.
```
mvn spring-boot:run
```


#### 실행
- customer-center 에 오류가 발생한다면 다음 MypageViewHandler.java 부분의 구현체를 확인: (findById --> findByOrderId)
```
    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_UPDATE_1(@Payload DeliveryStarted deliveryStarted) {
        try {
            if (!deliveryStarted.validate()) return;
                // view 객체 조회
            Optional<MyPage> myPageOptional = myPageRepository.findById(deliveryStarted.getOrderId());

            if( myPageOptional.isPresent()) {
                 MyPage myPage = myPageOptional.get();
            // view 객체에 이벤트의 eventDirectValue 를 set 함
                myPage.setDeliveryStatus("Started");    
                // view 레파지 토리에 save
                 myPageRepository.save(myPage);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
    }

```
- 주문 1건을 등록한 후, MyPage 의 내용을 확인한다
```
http :8081/orders productId=1 qty=1
http :8084/myPages
```
- 배송서비스(8083)를 다운다운시킨시킨 다음, MyPage 의 내용을 확인하여도 마이페이지의 응답 데이터는 정상 출력된다. 
- 이는 주문 및 배송 서비스로부터 CQRS 패턴에 따라 Mypage 서비스가 데이터를 복제해 제공하기 때문이다.


