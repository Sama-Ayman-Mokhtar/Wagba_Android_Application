package com.example.wagba_android_application.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_android_application.model.Cart;
import com.example.wagba_android_application.viewmodel.CartViewModel;
import com.example.wagba_android_application.viewmodel.DishViewModel;
import com.example.wagba_android_application.adapter.DishesAdapter;
import com.example.wagba_android_application.R;
import com.example.wagba_android_application.model.Dish;


import java.util.ArrayList;
import java.util.List;

public class dishesFragment extends Fragment {

    RecyclerView recyclerView;
    private DishViewModel mDishViewModel;
    DishesAdapter dishesAdapter;
    TextView cart_count;
    Button btn_checkout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dishes, container, false);
        cart_count = view.findViewById(R.id.item_count);
        btn_checkout = view.findViewById(R.id.checkout_btn);
        CartViewModel vm = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        vm.getSelected_items().observe(getViewLifecycleOwner(), lst_selected ->{
            cart_count.setText(lst_selected.size()+" items");
        });
        btn_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_dishesFragment_to_cartFragment);
            }
        });
        recyclerView = view.findViewById(R.id.rv_dishes);
        dishesAdapter = new DishesAdapter(vm, getViewLifecycleOwner(), requireArguments().getString("restaurant_name"));
        recyclerView.setAdapter(dishesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        mDishViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(DishViewModel.class);
        mDishViewModel.getAllDishesOfRestuarant(requireArguments().getString("restaurant_name")).observe(getActivity(), new Observer<List<Dish>>() {
            @Override
            public void onChanged(@Nullable final List<Dish> words) {
                // Update the cached copy of the words in the adapter.
                dishesAdapter.setDishes(words);
            }
        });
        return view;
    }

}
