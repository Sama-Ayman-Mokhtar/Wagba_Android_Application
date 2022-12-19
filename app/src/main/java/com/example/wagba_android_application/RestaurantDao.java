package com.example.wagba_android_application;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RestaurantDao {

    @Query("SELECT * from restaurant_table")
    LiveData<List<Restaurant>> getRestaurants();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Restaurant restaurant);

    @Query("DELETE FROM restaurant_table")
    void deleteAll();
}
