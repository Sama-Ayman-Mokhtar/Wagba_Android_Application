package com.example.wagba_android_application;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.Viewholder> {

    List<Restaurant> restaurantsModelsLst;
    Context context;

    public RestaurantsAdapter() {
       // this.restaurantsModelsLst = restaurantsModelsLst;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.restaurant_item, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Restaurant restaurant = restaurantsModelsLst.get(position);
        holder.name.setText(restaurant.getName());
        holder.description.setText(restaurant.getDescription());
        int id = holder.itemView.getResources().getIdentifier(restaurant.getImage(), "drawable",  "com.example.wagba_android_application");
        Drawable drawable = holder.itemView.getResources().getDrawable(id);
        holder.image.setImageDrawable(drawable);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String restaurnat_name = holder.name.getText().toString();
                Log.d("sama ", restaurnat_name);
                bundle.putString("restaurant_name",restaurnat_name);
                Navigation.findNavController(view).navigate(R.id.action_restaurantsFragment_to_dishesFragment,bundle);
            }
        });
    }

    void setRestaurants(List<Restaurant> restaurants){
        restaurantsModelsLst = restaurants;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (restaurantsModelsLst != null)
            return restaurantsModelsLst.size();
        else return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView name, description;
        ImageView image;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.retaurant_name);
            description = itemView.findViewById(R.id.restaurant_description);
            image = itemView.findViewById(R.id.restaurant_image);
        }
    }

}
