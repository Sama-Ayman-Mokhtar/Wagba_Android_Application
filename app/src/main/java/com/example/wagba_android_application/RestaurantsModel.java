package com.example.wagba_android_application;

public class RestaurantsModel {
    private String name;
    private String Description;
    private String image;

    public RestaurantsModel(String name, String description, String image) {
        this.name = name;
        Description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
