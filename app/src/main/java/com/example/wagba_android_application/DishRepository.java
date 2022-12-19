package com.example.wagba_android_application;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DishRepository {
    private DishDao dishDao;
    private LiveData<List<Dish>> allDishes;
    private LiveData<List<Dish>> allDishesOfRestaurant;


    DishRepository(Application application) {
        myRoomDatabase db = myRoomDatabase.getDatabase(application);
        dishDao = db.dishDao();
        allDishes = dishDao.getDishes();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    LiveData<List<Dish>> getAllDishes() {
        return allDishes;
    }

    LiveData<List<Dish>> getAllDishesOfRestaurant(String restaurant_name) {
        allDishesOfRestaurant = dishDao.getDishesInRestaurant(restaurant_name);
        return allDishesOfRestaurant;
    }

    // You must call this on a non-UI thread or your app will crash.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
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
