package com.example.wagba_android_application;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class RestaurantsAdapter extends RecyclerView.Adapter<RestaurantsAdapter.Viewholder> {

    ArrayList<RestaurantsModel> restaurantsModelsLst;

    public RestaurantsAdapter(ArrayList<RestaurantsModel> restaurantsModelsLst) {
        this.restaurantsModelsLst = restaurantsModelsLst;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.restaurant_item, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        RestaurantsModel restaurantsModel = restaurantsModelsLst.get(position);
        holder.name.setText(restaurantsModel.getName());
        holder.description.setText(restaurantsModel.getDescription());
        int id = holder.itemView.getResources().getIdentifier(restaurantsModel.getImage(), "drawable",  "com.example.wagba_android_application");
        Drawable drawable = holder.itemView.getResources().getDrawable(id);
        holder.image.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return restaurantsModelsLst.size();
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
