package com.example.template;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private Long productId;
    private String productName;
    private int quantity;
    private int price;
    private String customerId;
    private String customerName;
    private String customerAddr;
    private String state = "OrderPlaced";

    @PrePersist
    private void orderCheck(){
        RestTemplate restTemplate = Application.applicationContext.getBean(RestTemplate.class);
        Environment env = Application.applicationContext.getEnvironment();

        if( productId == null ){
            throw new RuntimeException();
        }

        int price = 0;
        String productName = null;

        if("true".equalsIgnoreCase(env.getProperty("checkStock"))){
            // 1. 주문에 대한 상품 조회 - API
            String productUrl = env.getProperty("api.url.product") + "/product/" + productId;

            ResponseEntity<String> productEntity = restTemplate.getForEntity(productUrl, String.class);
            JsonParser parser = new JsonParser();
            JsonObject jsonObject = parser.parse(productEntity.getBody()).getAsJsonObject();

            price = jsonObject.get("price").getAsInt();
            productName = jsonObject.get("name").getAsString();
            if( jsonObject.get("stock").getAsInt() < getQuantity()){
                throw new OrderException("No Available stock!");
            }


        }else{
            ProductRepository productRepository = Application.applicationContext.getBean(ProductRepository.class);
            Optional<Product> productOptional = productRepository.findById(productId);
            Product product = productOptional.get();

            price = product.getPrice();
            productName = product.getName();
            if( product.getStock() < getQuantity()){
                throw new OrderException("No Available stock!");
            }
        }
        this.setPrice(price);
        this.setProductName(productName);
    }

    /**
     * 주문이 들어옴
     */
    @PostPersist
    @ExceptionHandler(OrderException.class)
    private void publishOrderPlaced(){
        OrderPlaced orderPlaced = new OrderPlaced(this);
        orderPlaced.publish();
    }

    /**
     * 주문이 취소됨
     */
    @PostUpdate
    private void publishOrderCancelled(){
        if( "OrderCancelled".equals(this.getState())){
            // 이벤트를 발송하기 위하여 주문의 상세 정보를 조회

            OrderRepository orderRepository = Application.applicationContext.getBean(OrderRepository.class);
            Optional<Order> orderOptional = orderRepository.findById(this.getId());
            Order order = orderOptional.get();

            OrderCancelled orderCancelled = new OrderCancelled(order);
            orderCancelled.publish();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddr() {
        return customerAddr;
    }

    public void setCustomerAddr(String customerAddr) {
        this.customerAddr = customerAddr;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}