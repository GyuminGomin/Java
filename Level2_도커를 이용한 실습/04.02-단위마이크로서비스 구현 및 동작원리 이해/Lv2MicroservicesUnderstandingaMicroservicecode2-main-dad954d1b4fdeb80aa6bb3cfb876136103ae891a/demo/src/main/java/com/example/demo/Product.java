package com.example.demo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;
// import javax.persistence.PostRemove;
// import javax.persistence.PostUpdate;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.util.MimeTypeUtils;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.support.MessageBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity // Entity 표시
// Product 클래스 생성
public class Product {
    
    @Id @GeneratedValue //key값 표시 , id값 자동 생성
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
    // @PostPersist
    // public void eventPublish(){
    //     ProductChanged productChanged = new ProductChanged();
    //     productChanged.setProductId(this.getId());
    //     productChanged.setProductName(this.getName());
    //     productChanged.setProductStock(this.getStock());
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     String json = null;

    //     try {
    //         json = objectMapper.writeValueAsString(productChanged);
    //     } catch (JsonProcessingException e) {
    //         throw new RuntimeException("JSON format exception", e);
    //     }
    //     System.out.println("PostPersist = "+ json);
    // }

    // @PostUpdate
    // public void eventPublishUpdate(){
    //     ProductChanged productChanged = new ProductChanged();
    //     productChanged.setProductId(this.getId());
    //     productChanged.setProductName(this.getName());
    //     productChanged.setProductStock(this.getStock());
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     String json = null;

    //     try {
    //         json = objectMapper.writeValueAsString(productChanged);
    //     } catch (JsonProcessingException e) {
    //         throw new RuntimeException("JSON format exception", e);
    //     }
    //     System.out.println("PostUpdate = "+ json);
    // }

    // @PostRemove
    // public void eventPublishRemove(){
    //     ProductChanged productChanged = new ProductChanged();
    //     productChanged.setProductId(this.getId());
    //     productChanged.setProductName(this.getName());
    //     productChanged.setProductStock(this.getStock());
    //     ObjectMapper objectMapper = new ObjectMapper();
    //     String json = null;

    //     try {
    //         json = objectMapper.writeValueAsString(productChanged);
    //     } catch (JsonProcessingException e) {
    //         throw new RuntimeException("JSON format exception", e);
    //     }
    //     System.out.println("PostRemove = "+ json);
    // }
    
}