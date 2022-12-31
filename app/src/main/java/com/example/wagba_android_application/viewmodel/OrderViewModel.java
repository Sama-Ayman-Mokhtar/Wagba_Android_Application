package com.example.wagba_android_application.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.wagba_android_application.model.Dish;
import com.example.wagba_android_application.model.Order;
import com.example.wagba_android_application.repository.DishRepository;
import com.example.wagba_android_application.repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Order>> orders_lst;
    private OrderRepository orderRepository;

    public OrderViewModel(Application application) {
        super(application);
        orderRepository = new OrderRepository();
        orders_lst = orderRepository.getOrders();
    }

    public LiveData<ArrayList<Order>> getOrders() { return orders_lst; }

    public void putInRealtimeDB(Order order){
        orderRepository.putInRealtimeDB(order);
    }

    public String getOrderNumber() {
        return orderRepository.getOrderNumber();
    }

   /* public LiveData<List<Dish>> getAllDishesOfRestuarant( String restaurant_name){
        mAllDishesOfRestaurant = mRepository.getAllDishesOfRestaurant(restaurant_name);
        return mAllDishesOfRestaurant;
    }

    //to be removed
    public void insert(Dish dish) { mRepository.insert(dish); }*/
}
