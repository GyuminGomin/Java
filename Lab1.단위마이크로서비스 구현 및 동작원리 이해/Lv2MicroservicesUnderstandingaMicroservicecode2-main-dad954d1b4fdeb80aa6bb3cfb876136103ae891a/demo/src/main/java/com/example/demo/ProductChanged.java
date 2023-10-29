// event 등록

package com.example.demo;

public class ProductChanged {

    String eventType;
    Long productId;
    String productName;
    int productStock;

    public String getEventType() {
        return eventType;
    }
     public ProductChanged() {
        this.eventType = this.getClass().getSimpleName();
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