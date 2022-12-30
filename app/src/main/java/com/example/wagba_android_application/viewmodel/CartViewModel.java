package com.example.wagba_android_application.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.wagba_android_application.model.Cart;

import java.util.ArrayList;

public class CartViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Cart>> selected_items = new MutableLiveData<>();
    public void setSelected_items(ArrayList<Cart> cart){
        for (int i = 0; i < cart.size(); i++) {
            Log.d("yay", cart.get(i).getItem_name());
        }
        selected_items.setValue(cart);
    }

    public MutableLiveData<ArrayList<Cart>> getSelected_items() {
        return selected_items;
    }
}
