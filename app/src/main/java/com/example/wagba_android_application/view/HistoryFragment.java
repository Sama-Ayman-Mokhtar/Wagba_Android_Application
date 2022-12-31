package com.example.wagba_android_application.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.wagba_android_application.R;
import com.example.wagba_android_application.adapter.HistoryAdapter;
import com.example.wagba_android_application.model.Order;
import com.example.wagba_android_application.viewmodel.OrderViewModel;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private OrderViewModel orderViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.rv_history);
        orderViewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        historyAdapter = new HistoryAdapter(orderViewModel.getOrders().getValue());
        orderViewModel.getOrders().observe(getActivity(), new Observer<ArrayList<Order>>() {
            @Override
            public void onChanged(ArrayList<Order> orders) {
                historyAdapter.setOrders(orders);
            }
        });
        recyclerView.setAdapter(historyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }
}
