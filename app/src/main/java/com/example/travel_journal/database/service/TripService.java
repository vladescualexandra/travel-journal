package com.example.travel_journal.database.service;

import android.content.Context;

import com.example.travel_journal.async.AsyncTaskRunner;
import com.example.travel_journal.async.Callback;
import com.example.travel_journal.database.DatabaseManager;
import com.example.travel_journal.database.dao.TripDao;
import com.example.travel_journal.database.model.Trip;

import java.util.List;
import java.util.concurrent.Callable;

public class TripService {

    private final TripDao tripDao;
    private final AsyncTaskRunner taskRunner;

    public TripService(Context context) {
        tripDao = DatabaseManager.getInstance(context).getTripDao();
        taskRunner = new AsyncTaskRunner();
    }

    public void getAll(Callback<List<Trip>> callback) {
        Callable<List<Trip>> callable = new Callable<List<Trip>>() {
            @Override
            public List<Trip> call() throws Exception {
                return tripDao.getAll();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void getFav(Callback<List<Trip>> callback) {
        Callable<List<Trip>> callable = new Callable<List<Trip>>() {
            @Override
            public List<Trip> call() throws Exception {
                return tripDao.getFav();
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void insert(final Trip trip, Callback<Trip> callback) {
        Callable<Trip> callable = new Callable<Trip>() {
            @Override
            public Trip call() throws Exception {
                if (trip == null) {
                    return null;
                } else {
                    long id = tripDao.insert(trip);
                    if (id == -1) {
                        return null;
                    } else {
                        trip.setId(id);
                        return trip;

                    }
                }
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void update(final Trip trip, Callback<Trip> callback) {
        Callable<Trip> callable = new Callable<Trip>() {
            @Override
            public Trip call() throws Exception {
                if (trip == null) {
                    return null;
                } else {
                    int count = tripDao.update(trip);
                    if (count < 1) {
                        return null;
                    } else {
                        return trip;
                    }
                }
            }
        };
        taskRunner.executeAsync(callable, callback);
    }

    public void delete(final Trip trip, Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if (trip == null) {
                    return null;
                } else {
                    return tripDao.delete(trip);
                }
            }
        };
        taskRunner.executeAsync(callable, callback);
    }
}
