package com.example.wagba_android_application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.wagba_android_application.R;
import com.example.wagba_android_application.model.Order;
import java.util.ArrayList;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.Viewholder>{

    ArrayList<Order> history_of_orders;

    public HistoryAdapter(ArrayList<Order> history_of_orders) {
        this.history_of_orders = history_of_orders;
    }

    @NonNull
    @Override
    public HistoryAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_item, parent, false);
        HistoryAdapter.Viewholder viewholder = new HistoryAdapter.Viewholder(view);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.Viewholder holder, int position) {
        Order order = history_of_orders.get(position);
        holder.id.setText(order.getId());
        holder.details.setText(order.getDetails());
        holder.status.setText(order.getStatus());
        holder.price.setText(order.getPrice());
        if((order.getStatus()).compareTo("Delivered") == 0){
            holder.delivered_checkmark.setVisibility(View.VISIBLE);
            holder.status.setVisibility(View.GONE);
        }else{
            holder.delivered_checkmark.setVisibility(View.GONE);
            holder.status.setVisibility(View.VISIBLE);
        }
    }

    public void setOrders(ArrayList<Order> orders){
        history_of_orders = orders;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (history_of_orders != null)
            return history_of_orders.size();
        else return 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        TextView id, details, status, price;
        ImageView delivered_checkmark;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.order_id);
            details = itemView.findViewById(R.id.order_details);
            status = itemView.findViewById(R.id.status_txtvw);
            price = itemView.findViewById(R.id.order_price);
            delivered_checkmark = itemView.findViewById(R.id.delivered);
        }
    }
}
