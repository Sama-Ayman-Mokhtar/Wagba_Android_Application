package com.example.wagba_android_application.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wagba_android_application.viewmodel.DishViewModel;
import com.example.wagba_android_application.adapter.DishesAdapter;
import com.example.wagba_android_application.R;
import com.example.wagba_android_application.model.Dish;

import java.util.List;

public class dishesFragment extends Fragment {

    RecyclerView recyclerView;
    private DishViewModel mDishViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dishes, container, false);
        recyclerView = view.findViewById(R.id.rv_dishes);
        DishesAdapter dishesAdapter = new DishesAdapter();
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
