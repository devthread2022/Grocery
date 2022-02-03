package com.jvt.devthread.grocery.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jvt.devthread.grocery.Models.OrderedProductModel;
import com.jvt.devthread.grocery.R;

import java.util.List;

public class OrderedProductAdapter extends RecyclerView.Adapter<OrderedProductAdapter.ViewHolder> {
    public Context context;
    private List<OrderedProductModel> orderedProductModelList;

    public OrderedProductAdapter(Context context, List<OrderedProductModel> orderedProductModelList) {
        this.context = context;
        this.orderedProductModelList = orderedProductModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderedProductModel orderedProductModel = orderedProductModelList.get(position);
        Glide.with(context).load(orderedProductModel.getPic()).into(holder.image);
        holder.name.setText(orderedProductModel.getProductName());
        holder.price.setText(String.valueOf(orderedProductModel.getPrice()));

    }

    @Override
    public int getItemCount() {
        return orderedProductModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView name, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.icon);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
