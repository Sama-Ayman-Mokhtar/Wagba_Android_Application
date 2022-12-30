package com.example.wagba_android_application.model;

import android.graphics.drawable.Drawable;

public class Cart {
    private String item_name;
    private String price;
    private Drawable drawable_img;

    public Cart(String item_name, String price, Drawable drawable_img) {
        this.item_name = item_name;
        this.price = price;
        this.drawable_img = drawable_img;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Drawable getDrawableImage() {
        return drawable_img;
    }

    public void setDrawableImage(Drawable image) {
        this.drawable_img = image;
    }
}
