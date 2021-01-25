package com.example.travel_journal.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.travel_journal.database.model.Trip;

import java.util.List;

@Dao
public interface TripDao {

    // 0 - false
    // 1 - true
    @Query("SELECT * from trips where fav = 1")
    List<Trip> getFav();

    @Query("SELECT * from trips")
    List<Trip> getAll();

    @Insert
    long insert(Trip trip);

    @Update
    int update(Trip trip);

    @Delete
    int delete(Trip trip);
}
