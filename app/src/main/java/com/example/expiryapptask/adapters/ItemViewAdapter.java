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
import com.example.expiryapptask.ui.AddFoodDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class ItemViewAdapter extends RecyclerView.Adapter<ItemViewAdapter.ItemViewHolder> implements AddFoodDialogFragment.onFragmentListener {

    private List<ItemModel> itemModels = new ArrayList<>();

    public void setItems(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_view,null,false);
        ItemViewHolder viewHolder =new ItemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        ItemModel itemModel = itemModels.get(position);

        if (itemModel.getItemExpirationStatus() == 0) {
            switch (itemModel.getItemCategory()) {
                case "Food":
                    holder.itemImage.setImageResource(R.drawable.ic_chips);
                    holder.itemCategory.setText("Food");
                case "Drink":
                    holder.itemImage.setImageResource(R.drawable.ic_drinks);
                    holder.itemCategory.setText("Drinks");
            }
            holder.itemName.setText(itemModel.getItemName());
            holder.itemExpirationDate.setText(String.valueOf(itemModel.getItemExpirationDate()));

        }


    }

    @Override
    public int getItemCount() {
        return itemModels.size();
    }

    @Override
    public void onFragmentInteraction(ItemModel itemModel) {
        itemModels.add(itemModel);
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage ;
        TextView itemName , itemCategory, itemExpirationDate;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_vector);
            itemName = itemView.findViewById(R.id.item_name_tv);
            itemCategory = itemView.findViewById(R.id.item_category);
            itemExpirationDate = itemView.findViewById(R.id.item_exp_date);


        }
    }

}