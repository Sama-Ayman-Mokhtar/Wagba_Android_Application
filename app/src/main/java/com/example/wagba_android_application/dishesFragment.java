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

public class dishesFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<DishesModel> dishesModelArrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_dishes, container, false);
        recyclerView = view.findViewById(R.id.rv_dishes);

        //hard coded to be passed in as parameter
        Resources res = getResources();
        String[] dishes_names_lst = res.getStringArray(R.array.spectra_name_lst);
        String[] dishes_descriptions_lst = res.getStringArray(R.array.spectra_description_lst);
        String[] dishes_prices_lst = res.getStringArray(R.array.spectra_price_lst);
        String[] dishes_images_lst = res.getStringArray(R.array.spectra_images_lst);
        for (int i = 0 ; i < dishes_names_lst.length; i++) {
            dishesModelArrayList.add(
                    new DishesModel(dishes_names_lst[i], dishes_descriptions_lst[i], dishes_images_lst[i], dishes_prices_lst[i] ));
        }
        DishesAdapter dishesAdapter = new DishesAdapter(dishesModelArrayList);
        recyclerView.setAdapter(dishesAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        return view;
    }
}