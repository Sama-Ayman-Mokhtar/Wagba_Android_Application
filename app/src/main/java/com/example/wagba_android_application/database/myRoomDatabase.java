package com.example.wagba_android_application.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.wagba_android_application.model.Dish;
import com.example.wagba_android_application.model.Restaurant;

@Database(entities = {Restaurant.class, Dish.class}, version = 1, exportSchema = false)
public abstract class myRoomDatabase extends androidx.room.RoomDatabase {

    public abstract RestaurantDao restaurantDao();

    public abstract DishDao dishDao();

    private static myRoomDatabase INSTANCE;

    public static myRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (myRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    myRoomDatabase.class, "wagba_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
