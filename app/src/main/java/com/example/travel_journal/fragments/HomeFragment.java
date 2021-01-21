package com.example.travel_journal.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travel_journal.R;
import com.example.travel_journal.TripActivity;
import com.example.travel_journal.adapter.TripAdapter;
import com.example.travel_journal.model.Trip;
import com.example.travel_journal.model.Type;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    public static final int REQUEST_CODE_ADD_TRIP = 201;
    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Trip t1 = new Trip("Name1", "Dest1", Type.CITY_BREAK, 35, 2);
        Trip t2 = new Trip("Name2", "Dest2", Type.CITY_BREAK, 46, 4);
        Trip t3 = new Trip("Name3", "Dest3", Type.CITY_BREAK, 35, 1);

        List<Trip> trips = new ArrayList<>();
        trips.add(t1);
        trips.add(t2);
        trips.add(t3);
        TripAdapter adapter = new TripAdapter(trips);

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

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
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
        if (requestCode == REQUEST_CODE_ADD_TRIP && data != null) {

        }
    }
}