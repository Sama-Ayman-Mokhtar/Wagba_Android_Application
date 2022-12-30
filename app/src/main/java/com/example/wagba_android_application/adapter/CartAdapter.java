package com.example.wagba_android_application.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
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

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder>{

    CartViewModel vm;
    ArrayList<Cart> selected_items;
    LifecycleOwner vlco;

    public CartAdapter(CartViewModel vm, LifecycleOwner vlco) {
        this.vm = vm;
        this.vlco = vlco;
        this.selected_items = new ArrayList<>();
    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cart_item, parent, false);
        CartAdapter.Viewholder viewholder = new CartAdapter.Viewholder(view);
        return viewholder;
    }
    public void setCart(ArrayList<Cart> cartLst){
        selected_items = cartLst;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {
        Cart cart = selected_items.get(position);
        holder.name.setText(cart.getItem_name());
        holder.price.setText(cart.getPrice()+ " EGP");
        holder.image.setImageDrawable(cart.getDrawableImage());
    }

    @Override
    public int getItemCount() {
        if (selected_items != null)
            return selected_items.size();
        else return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView name, price;
        ImageView image;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.cart_item_name);
            price = itemView.findViewById(R.id.cart_item_price);
            image = itemView.findViewById(R.id.cart_item_image);
        }
    }
}
