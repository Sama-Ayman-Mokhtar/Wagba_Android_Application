package com.example.wagba_android_application.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.wagba_android_application.model.Restaurant;
import com.example.wagba_android_application.repository.restaurantRepository;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private restaurantRepository mRepository;

    private LiveData<List<Restaurant>> mAllRestaurants;

    public RestaurantViewModel(Application application) {
        super(application);
        mRepository = new restaurantRepository(application);
        mAllRestaurants = mRepository.getAllWords();
    }

    public LiveData<List<Restaurant>> getAllWords() { return mAllRestaurants; }

    //to be removed
    public void insert(Restaurant res) { mRepository.insert(res); }
}
