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
import com.jvt.devthread.grocery.Models.ProductListModel;
import com.jvt.devthread.grocery.R;

import java.util.List;

public class NewlyAddedProductListAdapter extends RecyclerView.Adapter<NewlyAddedProductListAdapter.ViewHolder> {
    public Context context;
    private List<ProductListModel> productListModelList;

    public NewlyAddedProductListAdapter(Context context, List<ProductListModel> productListModelList) {
        this.context = context;
        this.productListModelList = productListModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductListModel productListModel = productListModelList.get(position);
        Glide.with(context).load(productListModel.getPic()).into(holder.image);
        holder.name.setText(productListModel.getName());
        holder.price.setText(String.valueOf(productListModel.getPrice()));
        holder.unit.setText("KG");

    }

    @Override
    public int getItemCount() {
        return productListModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image,cart;
        TextView unit, price, name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.icon);
            cart = itemView.findViewById(R.id.cart_btn);
            unit = itemView.findViewById(R.id.unit);
            price = itemView.findViewById(R.id.price);
            name = itemView.findViewById(R.id.name);
        }
    }
}
