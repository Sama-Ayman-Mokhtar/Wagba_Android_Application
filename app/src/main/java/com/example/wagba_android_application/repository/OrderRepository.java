package com.example.wagba_android_application.repository;

import androidx.lifecycle.MutableLiveData;
import com.example.wagba_android_application.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class OrderRepository {

    private ArrayList<Order> orders;
    private MutableLiveData<ArrayList<Order>> orders_lst = new MutableLiveData<>();

    public MutableLiveData<ArrayList<Order>> getOrders(){
        loadOrders();
        orders_lst.setValue(orders);
        return orders_lst;
    }

    private void loadOrders() {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("orders");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                orders = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Order order = snapshot.getValue(Order.class);
                    orders.add(order);
                }
                orders_lst.setValue(orders);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });
    }


    public void putInRealtimeDB(Order order) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("orders/"+orders.size());
        reference.setValue(order);
    }

    public String getOrderNumber() {
        return String.valueOf(orders.size());
    }
}
