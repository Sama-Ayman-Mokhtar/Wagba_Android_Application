package com.example.wagba_android_application.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.example.wagba_android_application.R;
import com.example.wagba_android_application.model.Cart;
import com.example.wagba_android_application.model.Dish;
import com.example.wagba_android_application.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class DishesAdapter extends RecyclerView.Adapter<DishesAdapter.Viewholder> {

    List<Dish> dishesModelsLst;
    Context context;
    CartViewModel vm;
    ArrayList<Cart> selected_items;
    LifecycleOwner vlco;
    String restaurnat_name;

    public DishesAdapter(CartViewModel vm,
                         LifecycleOwner vlco, String restaurnat_name) {
        this.vm = vm;
        this.vlco = vlco;
        this.selected_items = new ArrayList<>();
        this.restaurnat_name = restaurnat_name;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dish_item, parent, false);
        Viewholder viewholder = new Viewholder(view);
        vm.getSelected_items().observe(vlco, lst_selected ->{
            selected_items = lst_selected;
        });
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        Dish dishesModel = dishesModelsLst.get(position);
        holder.name.setText(dishesModel.getDishName());
        holder.description.setText(dishesModel.getDescription());
        holder.price.setText(dishesModel.getPrice());
        int id = holder.itemView.getResources().getIdentifier(dishesModel.getImage(), "drawable",  "com.example.wagba_android_application");
        Drawable drawable = holder.itemView.getResources().getDrawable(id);
        holder.image.setImageDrawable(drawable);
        boolean found = false;
        for (Cart cart_item: selected_items ) {
            if(cart_item.getItem_name().compareTo(holder.name.getText().toString()+" :: " + restaurnat_name)==0){
                found = true;
            }
        }
        if(found){
            holder.hidden_flag.setText("1");
            holder.cart_icon.setImageDrawable(holder.cart_take);

        }else{
            holder.hidden_flag.setText("0");
            holder.cart_icon.setImageDrawable(holder.cart_leave);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.hidden_flag.getText().toString().compareTo("0") == 0){
                    Cart cart = new Cart(holder.name.getText().toString()+" :: " + restaurnat_name,holder.price.getText().toString(),holder.image.getDrawable());
                    if(selected_items == null)
                        selected_items = new ArrayList<>();
                    selected_items.add(cart);
                    vm.setSelected_items(selected_items);
                    holder.cart_icon.setImageDrawable(holder.cart_take);
                    holder.hidden_flag.setText("1");
                }else{
                    Cart cart_item_to_rm = null;
                    for (Cart cart_item: selected_items ) {
                        if(cart_item.getItem_name().compareTo(holder.name.getText().toString()+" :: " + restaurnat_name)==0){
                            cart_item_to_rm = cart_item;
                        }
                    }
                    if(cart_item_to_rm!=null){
                        selected_items.remove(cart_item_to_rm);
                    }
                    holder.hidden_flag.setText("0");
                    vm.setSelected_items(selected_items);
                    holder.cart_icon.setImageDrawable(holder.cart_leave);
                }
            }
        });
    }

    public void setDishes(List<Dish> dishes){
        dishesModelsLst = dishes;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (dishesModelsLst != null)
            return dishesModelsLst.size();
        else return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView name, description, price;
        ImageView image;
        ImageView cart_icon;
        Drawable cart_take;
        Drawable cart_leave;
        TextView hidden_flag;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.order_id);
            description = itemView.findViewById(R.id.order_details);
            price = itemView.findViewById(R.id.order_price);
            image = itemView.findViewById(R.id.dishImage);
            cart_icon = itemView.findViewById(R.id.addCart);
            cart_take = itemView.getResources().getDrawable(R.drawable.ic_cart_take);
            cart_leave = itemView.getResources().getDrawable(R.drawable.ic_cart_leave);
            hidden_flag= itemView.findViewById(R.id.hidden_is_selected);
        }
    }
}
