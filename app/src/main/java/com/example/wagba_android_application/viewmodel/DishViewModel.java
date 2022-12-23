package com.example.wagba_android_application.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba_android_application.model.Dish;
import com.example.wagba_android_application.repository.DishRepository;

import java.util.List;

public class DishViewModel extends AndroidViewModel {
    private DishRepository mRepository;

    private LiveData<List<Dish>> mAllDishes;
    private LiveData<List<Dish>> mAllDishesOfRestaurant;

    public DishViewModel(Application application) {
        super(application);
        mRepository = new DishRepository(application);
        mAllDishes = mRepository.getAllDishes();
    }

    LiveData<List<Dish>> getAllWords() { return mAllDishes; }
    public LiveData<List<Dish>> getAllDishesOfRestuarant( String restaurant_name){
        mAllDishesOfRestaurant = mRepository.getAllDishesOfRestaurant(restaurant_name);
        return mAllDishesOfRestaurant;
    }

    //to be removed
    public void insert(Dish dish) { mRepository.insert(dish); }
}

