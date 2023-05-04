package com.example.tech_service.Model;

public class TypeofRequest {
    private int price;
    private String category, typeofrequest_id;

    public int getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTypeofrequest_id() {
        return typeofrequest_id;
    }

    public void setTypeofrequest_id(String typeofrequest_id) {
        this.typeofrequest_id = typeofrequest_id;
    }
}
