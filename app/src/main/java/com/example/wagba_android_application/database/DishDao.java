package com.example.wagba_android_application.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.wagba_android_application.model.Dish;

import java.util.List;

@Dao
public interface DishDao {

    @Query("SELECT * from dish_table")
    LiveData<List<Dish>> getDishes();

    @Query("SELECT * from dish_table  WHERE restaurant_name = :restaurant_name")
    LiveData<List<Dish>> getDishesInRestaurant(String restaurant_name);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Dish dish);

    @Query("DELETE FROM dish_table")
    void deleteAll();
}
