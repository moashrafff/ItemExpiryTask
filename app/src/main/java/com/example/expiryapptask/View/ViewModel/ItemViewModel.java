package com.example.expiryapptask.ui;

import android.app.Application;
import android.app.PendingIntent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.expiryapptask.database.DataBaseDao;
import com.example.expiryapptask.database.ItemDataBase;
import com.example.expiryapptask.database.Repository;
import com.example.expiryapptask.pojo.ItemModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ItemViewModel extends AndroidViewModel {

    Repository repository;

    MutableLiveData<List<ItemModel>> items = new MutableLiveData<>();

    public ItemViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
    }

    public void insertItem(ItemModel item){
        repository.insertItem(item);
    }

    public Observable<List<ItemModel>> getItems() {
        return repository.getItems();
    }



}
