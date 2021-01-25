package com.example.travel_journal.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travel_journal.R;
import com.example.travel_journal.TripActivity;
import com.example.travel_journal.adapter.TripAdapter;
import com.example.travel_journal.async.Callback;
import com.example.travel_journal.database.model.Trip;
import com.example.travel_journal.database.service.TripService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final int REQUEST_CODE_ADD_TRIP = 201;
    RecyclerView recyclerView;
    List<Trip> trips = new ArrayList<>();
    private TripService tripService;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        tripService = new TripService(getActivity().getApplicationContext());
        tripService.getAll(getAllTripsFromDBCallback());
        TripAdapter adapter = new TripAdapter(trips, getActivity().getApplicationContext());

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        addAdapter(adapter, view);
        initFAB(view);

        return view;
    }

    private void initFAB(View view) {
        FloatingActionButton fab_add_trip = view.findViewById(R.id.home_add_trip);
        fab_add_trip.setOnClickListener(addTripEvent());
    }

    private void addAdapter(TripAdapter adapter, View view) {
        recyclerView = view.findViewById(R.id.home_trips);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private View.OnClickListener addTripEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TripActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ADD_TRIP);
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Trip trip = (Trip) data.getSerializableExtra(TripActivity.TRIP_KEY);

        if (resultCode == Activity.RESULT_OK && data != null) {
            if (requestCode == REQUEST_CODE_ADD_TRIP) {
                tripService.insert(trip, insertTripIntoDBCallback());
                notifyAdapter();
            }
        }

    }

    private void notifyAdapter() {
        TripAdapter adapter = (TripAdapter) recyclerView.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private Callback<List<Trip>> getAllTripsFromDBCallback() {
        return new Callback<List<Trip>>() {
            @Override
            public void runResultOnUIThread(List<Trip> result) {
                if (result != null) {
                    trips.clear();
                    trips.addAll(result);
                    for (Trip trip: trips) {
                        Log.e("trip", trip.toString());
                    }
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Trip> insertTripIntoDBCallback() {
        return new Callback<Trip>() {
            @Override
            public void runResultOnUIThread(Trip result) {
                if (result != null) {
                    trips.add(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Trip> updateTripToDBCallback() {
        return new Callback<Trip>() {
            @Override
            public void runResultOnUIThread(Trip result) {
                if (result != null) {
                    for (Trip trip : trips) {
                        if (trip.getId() == result.getId()) {
                            trip = result;
                            break;
                        }
                    }
                }
                notifyAdapter();
            }
        };
    }

    private Callback<Integer> deleteTripFromDBCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUIThread(Integer result) {
                if (result != -1) {
                    trips.remove(position);
                    notifyAdapter();
                }
            }
        };
    }
}