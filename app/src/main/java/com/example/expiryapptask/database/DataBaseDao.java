package com.example.expiryapptask.database;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.expiryapptask.pojo.ItemModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

@Dao
public interface DataBaseDao {
    @Insert
    Completable insertItem(ItemModel itemModel);

    @Query("Select * from Items_Table")
    Observable<List<ItemModel>> getItems();
}
