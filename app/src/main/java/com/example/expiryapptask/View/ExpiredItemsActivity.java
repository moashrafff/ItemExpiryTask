package com.example.expiryapptask.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.expiryapptask.adapters.ExpiredItemViewAdapter;
import com.example.expiryapptask.R;
import com.example.expiryapptask.database.ItemDataBase;
import com.example.expiryapptask.pojo.ItemModel;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ExpiredItemsActivity extends AppCompatActivity {
    RecyclerView itemsRV;
    ExpiredItemViewAdapter adapter;
    ItemViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expired_items_layout);
        itemsRV = findViewById(R.id.expired_items);

        adapter = new ExpiredItemViewAdapter();

        viewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        viewModel.getItems()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ItemModel>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<ItemModel> itemModels) {
                        adapter.setItems(itemModels);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        itemsRV.setLayoutManager(new LinearLayoutManager(this));
        itemsRV.setAdapter(adapter);





    }
}