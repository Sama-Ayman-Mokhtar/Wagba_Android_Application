package com.example.wagba_android_application.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.wagba_android_application.database.DishDao;
import com.example.wagba_android_application.database.myRoomDatabase;
import com.example.wagba_android_application.model.Dish;
import com.example.wagba_android_application.model.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DishRepository {
    private DishDao dishDao;
    private LiveData<List<Dish>> allDishes;
    private LiveData<List<Dish>> allDishesOfRestaurant;


    public DishRepository(Application application) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("dishes/");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot ss: snapshot.getChildren()) {
                        Dish dish = ss.getValue(Dish.class);
                        insert(dish);
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        myRoomDatabase db = myRoomDatabase.getDatabase(application);
        dishDao = db.dishDao();
        allDishes = dishDao.getDishes();
    }

    public LiveData<List<Dish>> getAllDishes() {
        return allDishes;
    }

    public LiveData<List<Dish>> getAllDishesOfRestaurant(String restaurant_name) {
        allDishesOfRestaurant = dishDao.getDishesInRestaurant(restaurant_name);
        return allDishesOfRestaurant;
    }

    public void insert (Dish dish) {
        new insertAsyncTask(dishDao).execute(dish);
    }

    private static class insertAsyncTask extends AsyncTask<Dish, Void, Void> {

        private DishDao mAsyncTaskDao;

        insertAsyncTask(DishDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Dish... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
