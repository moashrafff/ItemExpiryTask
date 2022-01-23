package com.example.expiryapptask.pojo;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Items_Table")
public class ItemModel {
    @PrimaryKey @NonNull
    private String itemId;
    private String itemName;
    private String itemCategory;
    private long itemExpirationDate;
    private int itemExpirationStatus;

    public ItemModel(String itemId, String itemName, String itemCategory, long itemExpirationDate, int itemExpirationStatus) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemExpirationDate = itemExpirationDate;
        this.itemExpirationStatus = itemExpirationStatus;
    }

    public int getItemExpirationStatus() {
        return itemExpirationStatus;
    }

    public void setItemExpirationStatus(int itemExpirationStatus) {
        this.itemExpirationStatus = itemExpirationStatus;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public long getItemExpirationDate() {
        return itemExpirationDate;
    }

    public void setItemExpirationDate(long itemExpirationDate) {
        this.itemExpirationDate = itemExpirationDate;
    }
}