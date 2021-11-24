package com.example.sort_items;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private ArrayList<Item> itemList;

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView item;
        private TextView quantity;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            item = itemView.findViewById(R.id.text_item);
            quantity = itemView.findViewById(R.id.text_quantity);
        }
    }

    public Adapter(ArrayList<Item> itemList) {

        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Item currentItem = itemList.get(position);
        holder.item.setText(currentItem.getItem());
        holder.quantity.setText("Quantity: " +currentItem.getQuantity());
    }

    @Override
    public int getItemCount() {

        return itemList.size();
    }
}
