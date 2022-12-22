package com.example.wagba_android_application.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dish_table")
public class Dish {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "dish_name")
    private String dishName;

    @NonNull
    @ColumnInfo(name = "restaurant_name")
    private String restaurantName;

    @NonNull
    @ColumnInfo(name = "description")
    private String description;

    @NonNull
    @ColumnInfo(name = "price")
    private String price;

    @NonNull
    @ColumnInfo(name = "image")
    private String image;

    public Dish(@NonNull String dishName, @NonNull String restaurantName, @NonNull String description, @NonNull String price, @NonNull String image) {
        this.dishName = dishName;
        this.restaurantName = restaurantName;
        this.description = description;
        this.price = price;
        this.image = image;
    }

    @NonNull
    public String getDishName() {
        return dishName;
    }

    public void setDishName(@NonNull String dishName) {
        this.dishName = dishName;
    }

    @NonNull
    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(@NonNull String restaurantName) {
        this.restaurantName = restaurantName;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public String getPrice() {
        return price;
    }

    public void setPrice(@NonNull String price) {
        this.price = price;
    }

    @NonNull
    public String getImage() {
        return image;
    }

    public void setImage(@NonNull String image) {
        this.image = image;
    }
}
