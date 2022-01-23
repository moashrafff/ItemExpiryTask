package com.example.expiryapptask.database;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.expiryapptask.pojo.ItemModel;

import java.util.List;

import io.reactivex.Observable;

public class Repository {

    ItemDataBase dataBase;
    DataBaseDao dao;

    public Repository(Application application) {
        dataBase = ItemDataBase.getInstance(application);
        dao = dataBase.postsDao();
    }

    public void insertItem(ItemModel item){
        ItemDataBase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                dao.insertItem(item);
            }
        });
    }

    public Observable<List<ItemModel>> getItems() {
        return dao.getItems();
    }

}
