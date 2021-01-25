package com.example.travel_journal.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.travel_journal.database.dao.TripDao;
import com.example.travel_journal.database.model.Trip;

@Database(entities={Trip.class}, exportSchema=false, version=1)
public abstract class DatabaseManager extends RoomDatabase {

    private static final String DATABASE_NAME = "travel_journal";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context,
                            DatabaseManager.class,
                            DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return  databaseManager;
    }

    public abstract TripDao getTripDao();
}
