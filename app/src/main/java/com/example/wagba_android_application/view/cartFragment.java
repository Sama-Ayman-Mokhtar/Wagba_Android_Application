package com.example.wagba_android_application.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.wagba_android_application.R;
import com.example.wagba_android_application.adapter.CartAdapter;
import com.example.wagba_android_application.model.Cart;
import com.example.wagba_android_application.model.Order;
import com.example.wagba_android_application.viewmodel.CartViewModel;
import com.example.wagba_android_application.viewmodel.OrderViewModel;

import java.util.ArrayList;


public class cartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private CartAdapter cartAdapter;
    private OrderViewModel orderViewModel;
    private StringBuilder details;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        TextView total_price_txt = view.findViewById(R.id.total_price);
        recyclerView = view.findViewById(R.id.rv_cart_items);
        cartAdapter = new CartAdapter(cartViewModel, getViewLifecycleOwner());
        recyclerView.setAdapter(cartAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));


        Button continue_btn = view.findViewById(R.id.continueShopping_btn);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).popBackStack();
            }
        });
        Button pay_btn = view.findViewById(R.id.pay_btn);
        orderViewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        pay_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order = new Order("Order #"+orderViewModel.getOrderNumber(),String.valueOf(details),"Placed",total_price_txt.getText().toString());
                orderViewModel.putInRealtimeDB(order);
                cartViewModel.setSelected_items(new ArrayList<>(
                ));
                Navigation.findNavController(view).navigate(R.id.action_cartFragment_to_historyFragment);
            }
        });
        cartViewModel.getSelected_items().observe(getViewLifecycleOwner(), lst_selected ->{
            cartAdapter.setCart(lst_selected);
            double total_price = 0.0;
            details = new StringBuilder();
            boolean flag = false;
            for (Cart cart_item: lst_selected ) {
                total_price += Double.valueOf(cart_item.getPrice());
                total_price_txt.setText(String.valueOf(total_price)+" EGP");
                if(!flag){
                    details.append(cart_item.getItem_name());
                    flag = true;
                }
                details.append(" - " + cart_item.getItem_name());
            }
        });
        return view ;
    }
}
