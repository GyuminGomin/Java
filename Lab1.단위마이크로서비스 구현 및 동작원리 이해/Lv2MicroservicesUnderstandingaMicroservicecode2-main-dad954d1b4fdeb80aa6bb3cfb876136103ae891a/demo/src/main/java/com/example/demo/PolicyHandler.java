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