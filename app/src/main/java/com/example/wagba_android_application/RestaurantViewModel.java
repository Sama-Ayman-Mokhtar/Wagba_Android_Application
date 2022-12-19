package com.example.wagba_android_application;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private restaurantRepository mRepository;
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    private LiveData<List<Restaurant>> mAllRestaurants;

    public RestaurantViewModel(Application application) {
        super(application);
        mRepository = new restaurantRepository(application);
        mAllRestaurants = mRepository.getAllWords();
    }

    LiveData<List<Restaurant>> getAllWords() { return mAllRestaurants; }

    //to be removed
    public void insert(Restaurant res) { mRepository.insert(res); }
}
