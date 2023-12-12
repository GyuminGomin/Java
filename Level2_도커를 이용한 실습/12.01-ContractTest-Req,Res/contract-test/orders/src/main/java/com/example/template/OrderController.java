package com.example.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    @Value("${api.url.product}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/order/validateProduct/{productId}")
    public ResponseEntity<String> productStockCheck(@PathVariable(value = "productId") Long productId) {
    
        String productUrl = apiUrl + "/product/" + productId;
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
    
        ResponseEntity<String> productEntity = restTemplate.exchange(productUrl, HttpMethod.GET, entity, String.class);
    
        return productEntity;
    }
    
}
