package com.example.wagba_android_application;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DishViewModel extends AndroidViewModel {
    private DishRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Dish>> mAllDishes;
    private LiveData<List<Dish>> mAllDishesOfRestaurant;

    public DishViewModel(Application application) {
        super(application);
        mRepository = new DishRepository(application);
        mAllDishes = mRepository.getAllDishes();
    }

    LiveData<List<Dish>> getAllWords() { return mAllDishes; }
    LiveData<List<Dish>> getAllDishesOfRestuarant( String restaurant_name){
        mAllDishesOfRestaurant = mRepository.getAllDishesOfRestaurant(restaurant_name);
        return mAllDishesOfRestaurant;
    }

    //to be removed
    public void insert(Dish dish) { mRepository.insert(dish); }
}

