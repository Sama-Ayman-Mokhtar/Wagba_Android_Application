package com.example.wagba_android_application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.Viewholder> {

    ArrayList<DishesModel> dishesModelsLst;

    public DishesAdapter(ArrayList<DishesModel> dishesModelsLst) {
        this.dishesModelsLst = dishesModelsLst;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dish_item, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        DishesModel dishesModel = dishesModelsLst.get(position);
        holder.name.setText(dishesModel.getName());
        holder.description.setText(dishesModel.getDescription());
        holder.price.setText(dishesModel.getPrice());
        int id = holder.itemView.getResources().getIdentifier(dishesModel.getImage(), "drawable",  "com.example.wagba_android_application");
        Drawable drawable = holder.itemView.getResources().getDrawable(id);
        holder.image.setImageDrawable(drawable);
    }

    @Override
    public int getItemCount() {
        return dishesModelsLst.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView name, description, price;
        ImageView image;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.order_id);
            description = itemView.findViewById(R.id.order_details);
            price = itemView.findViewById(R.id.order_price);
            image = itemView.findViewById(R.id.dishImage);
        }
    }
}
