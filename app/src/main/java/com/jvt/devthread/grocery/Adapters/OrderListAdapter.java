package com.jvt.devthread.grocery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jvt.devthread.grocery.Models.OrderListModel;
import com.jvt.devthread.grocery.R;

import org.w3c.dom.Text;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {
    public Context context;
    private List<OrderListModel> orderListModelList;

    public OrderListAdapter(Context context, List<OrderListModel> orderListModelList) {
        this.context = context;
        this.orderListModelList = orderListModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderListModel orderListModel = orderListModelList.get(position);
        holder.orderId.setText(orderListModel.getOrderId());
        holder.date.setText("Date: "+orderListModel.getDate());
        holder.amount.setText("Paid Amount: "+String.valueOf(orderListModel.getAmount()));

    }

    @Override
    public int getItemCount() {
        return orderListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, amount, date, viewDetails;
        RecyclerView productRecycler;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            orderId = itemView.findViewById(R.id.ord_id);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);
            viewDetails = itemView.findViewById(R.id.view);
            productRecycler = itemView.findViewById(R.id.itemRecycler);
        }
    }
}
