package com.example.expiryapptask.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expiryapptask.R;
import com.example.expiryapptask.pojo.ItemModel;

import java.util.ArrayList;
import java.util.List;

public class ExpiredItemViewAdapter extends RecyclerView.Adapter<ExpiredItemViewAdapter.ExpiredItemViewHolder> {

    private List<ItemModel> expiredItemModels = new ArrayList<>();

    @NonNull
    @Override
    public ExpiredItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.expired_item_card_view,null,false);
        ExpiredItemViewHolder viewHolder = new ExpiredItemViewHolder(v);
        return viewHolder;
    }

    public void setItems(List<ItemModel> expiredItemModels) {
        this.expiredItemModels = expiredItemModels;
    }

    @Override
    public void onBindViewHolder(@NonNull ExpiredItemViewHolder holder, int position) {

        ItemModel itemModel = expiredItemModels.get(position);

        if (itemModel.getItemExpirationStatus() == 1 ) {
            switch (itemModel.getItemCategory()) {
                case "Food":
                    holder.itemImage.setImageResource(R.drawable.ic_chips);
                    holder.itemCategory.setText("Food");
                case "Drink":
                    holder.itemImage.setImageResource(R.drawable.ic_drinks);
                    holder.itemCategory.setText("Drinks");
            }
            holder.itemName.setText(itemModel.getItemName());
            holder.itemExpirationDate.setText(itemModel.getItemExpirationDate()+"");
        }
    }

    @Override
    public int getItemCount() {
        return expiredItemModels.size();
    }

    class ExpiredItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage ;
        TextView itemName , itemCategory, itemExpirationDate;

        public ExpiredItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.expired_item_vector);
            itemName = itemView.findViewById(R.id.expired_item_name_tv);
            itemCategory = itemView.findViewById(R.id.expired_item_category);
            itemExpirationDate = itemView.findViewById(R.id.expired_item_exp_date);
        }
    }

}