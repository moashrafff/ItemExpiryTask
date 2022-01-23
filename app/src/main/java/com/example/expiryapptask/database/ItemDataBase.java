package com.example.expiryapptask.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expiryapptask.pojo.ItemModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = ItemModel.class, version = 1)
public abstract class ItemDataBase extends RoomDatabase {

    private static ItemDataBase instance;
    public abstract DataBaseDao postsDao();

    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized ItemDataBase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ItemDataBase.class, "items_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}
