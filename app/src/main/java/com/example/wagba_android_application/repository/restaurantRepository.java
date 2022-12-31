package com.example.wagba_android_application.repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.wagba_android_application.database.RestaurantDao;
import com.example.wagba_android_application.database.myRoomDB;
import com.example.wagba_android_application.model.Restaurant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class restaurantRepository {
    private RestaurantDao restaurantDao;
    private LiveData<List<Restaurant>> AllRestaurants;


    public restaurantRepository(Application application) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("restaurants");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Restaurant res = snapshot.getValue(Restaurant.class);
                    insert(res);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        myRoomDB db = myRoomDB.getDatabase(application);
        restaurantDao = db.restaurantDao();
        AllRestaurants = restaurantDao.getRestaurants();
    }

    public LiveData<List<Restaurant>> getAllWords() {
        return AllRestaurants;
    }

    public void insert (Restaurant restaurant) {
        new insertAsyncTask(restaurantDao).execute(restaurant);
    }

    private static class insertAsyncTask extends AsyncTask<Restaurant, Void, Void> {

        private RestaurantDao mAsyncTaskDao;

        insertAsyncTask(RestaurantDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Restaurant... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
