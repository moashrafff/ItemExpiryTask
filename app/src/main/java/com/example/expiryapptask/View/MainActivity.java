package com.example.expiryapptask.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.expiryapptask.R;
import com.example.expiryapptask.database.ItemDataBase;
import com.example.expiryapptask.BroadCast.ReminderBroadCast;
import com.example.expiryapptask.pojo.ItemModel;
import com.example.expiryapptask.adapters.ItemViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ItemViewModel viewModel;
    RecyclerView itemsRV;
    FloatingActionButton fab ,exFab ;
    ItemViewAdapter adapter;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        itemsRV = findViewById(R.id.item_rv);
        exFab = findViewById(R.id.fab_expired);

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
                        for (int i = 0; i < itemModels.size() ; i++){
                            long time = (itemModels.get(i).getItemExpirationDate()*(i+1) * 6);
                            insertTime(time);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        adapter = new ItemViewAdapter();
        itemsRV.setAdapter(adapter);
        itemsRV.setLayoutManager(new LinearLayoutManager(this));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment addFoodDialogFragment = new AddFoodDialogFragment();
                addFoodDialogFragment.show(getSupportFragmentManager(), "add_food");
                createNotificationChannel();
            }
        });


        exFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ExpiredItemsActivity.class));
            }
        });




    }

    private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
            CharSequence name = "Reminder Channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            String description = "Channel for Expiration Reminder";
            NotificationChannel channel = new NotificationChannel("Notify",name,importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void insertTime (long time){
        Intent intent = new Intent(MainActivity.this,ReminderBroadCast.class);
         pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        long timeAtButtonClick = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),timeAtButtonClick + time ,pendingIntent);


    }



    }