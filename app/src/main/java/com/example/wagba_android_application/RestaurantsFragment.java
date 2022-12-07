package com.example.wagba_android_application;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RestaurantsFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<RestaurantsModel> restaurantsModelArrayList = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_restaurants, container, false);
        recyclerView = view.findViewById(R.id.rv_restaurants);

        Resources res = getResources();
        String[] restaurant_names_lst = res.getStringArray(R.array.restaurant_names_lst);
        String[] restaurant_descriptions_lst = res.getStringArray(R.array.restaurant_descriptions_lst);
        String[] restaurant_images_lst = res.getStringArray(R.array.restaurant_images_lst);
        for (int i = 0 ; i < restaurant_images_lst.length; i++) {
            restaurantsModelArrayList.add(
                    new RestaurantsModel(restaurant_names_lst[i], restaurant_descriptions_lst[i], restaurant_images_lst[i] ));
        }
        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter(restaurantsModelArrayList);
        recyclerView.setAdapter(restaurantsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }
}