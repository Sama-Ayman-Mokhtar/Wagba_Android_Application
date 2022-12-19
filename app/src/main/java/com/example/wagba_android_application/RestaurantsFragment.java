package com.example.wagba_android_application;

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

import java.util.List;

public class RestaurantsFragment extends Fragment {

    RecyclerView recyclerView;
    //ArrayList<Restaurant> restaurantsModelArrayList = new ArrayList<>();
    private RestaurantViewModel mResViewModel;

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

        /*Resources res = getResources();
        String[] restaurant_names_lst = res.getStringArray(R.array.restaurant_names_lst);
        String[] restaurant_descriptions_lst = res.getStringArray(R.array.restaurant_descriptions_lst);
        String[] restaurant_images_lst = res.getStringArray(R.array.restaurant_images_lst);
        for (int i = 0 ; i < restaurant_images_lst.length; i++) {
            restaurantsModelArrayList.add(
                    new RestaurantsModel(restaurant_names_lst[i], restaurant_descriptions_lst[i], restaurant_images_lst[i] ));
        }*/
        RestaurantsAdapter restaurantsAdapter = new RestaurantsAdapter();
        recyclerView.setAdapter(restaurantsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        mResViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication()).create(RestaurantViewModel.class);
        mResViewModel.getAllWords().observe(getActivity(), new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(@Nullable final List<Restaurant> words) {
                // Update the cached copy of the words in the adapter.
                restaurantsAdapter.setRestaurants(words);
            }
        });


        return view;
    }
}