package com.example.travel_journal.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travel_journal.R;
import com.example.travel_journal.adapter.TripAdapter;
import com.example.travel_journal.model.Trip;
import com.example.travel_journal.model.Type;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.e("test", "hi fragment");
        Trip t1 = new Trip("Name1", "Dest1", Type.CITY_BREAK, 35, 2);
        Trip t2 = new Trip("Name2", "Dest2", Type.CITY_BREAK, 46, 4);
        Trip t3 = new Trip("Name3", "Dest3", Type.CITY_BREAK, 35, 1);

        List<Trip> trips = new ArrayList<>();
        trips.add(t1);
        trips.add(t2);
        trips.add(t3);
        TripAdapter adapter = new TripAdapter(trips);

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = view.findViewById(R.id.home_trips);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        recyclerView.setAdapter(adapter);
        return view;
    }
}