package com.example.wagba_android_application.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.wagba_android_application.model.Dish;
import com.example.wagba_android_application.model.Profile;
import com.example.wagba_android_application.model.Restaurant;

@Database(entities = {Restaurant.class, Dish.class, Profile.class}, version = 1, exportSchema = false)
public abstract class myRoomDB extends androidx.room.RoomDatabase {

    public abstract RestaurantDao restaurantDao();

    public abstract DishDao dishDao();

    public abstract ProfileDao profileDao();

    private static myRoomDB INSTANCE;

    public static myRoomDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (myRoomDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    myRoomDB.class, "wagba_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
