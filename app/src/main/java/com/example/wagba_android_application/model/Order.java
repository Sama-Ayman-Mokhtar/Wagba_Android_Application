package com.example.wagba_android_application.model;

public class Order {
    String id;
    String details;
    //placed -> confirmed -> processed -> delivered
    String status;
    String price;

    public Order(){
    }

    public Order(String id, String details, String status, String price) {
        this.id = id;
        this.details = details;
        this.status = status;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
