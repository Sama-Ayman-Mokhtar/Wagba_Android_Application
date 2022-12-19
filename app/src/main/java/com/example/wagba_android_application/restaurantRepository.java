package com.example.wagba_android_application;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class restaurantRepository {
    private RestaurantDao restaurantDao;
    private LiveData<List<Restaurant>> AllRestaurants;


    restaurantRepository(Application application) {
        myRoomDatabase db = myRoomDatabase.getDatabase(application);
        restaurantDao = db.restaurantDao();
        AllRestaurants = restaurantDao.getRestaurants();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Restaurant>> getAllWords() {
        return AllRestaurants;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
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
