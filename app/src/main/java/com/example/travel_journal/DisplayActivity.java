package com.example.travel_journal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.travel_journal.adapter.TripAdapter;
import com.example.travel_journal.database.model.Trip;
import com.example.travel_journal.util.DateConverter;

import java.util.Calendar;
import java.util.Date;

public class DisplayActivity extends AppCompatActivity {

    private TextView name;
    private TextView destination;
    private RatingBar rating;
    private TextView price;
    private CalendarView dates;

    Intent intent;
    Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        initComponents();
    }

    private void initComponents() {
        name = findViewById(R.id.display_name);
        destination = findViewById(R.id.display_destination);
        rating = findViewById(R.id.display_rating);
        price = findViewById(R.id.display_price);
        dates = findViewById(R.id.display_calendar);
        intent = getIntent();
        trip = (Trip) intent.getSerializableExtra(TripAdapter.TripViewHolder.TRIP_KEY_DISPLAY);
        if (trip != null) {
            Log.e("DisplayActivity", trip.toString());
            buildViews();
        }
    }

    @SuppressLint("StringFormatMatches")
    private void buildViews() {
        name.setText(trip.getName());
        destination.setText(trip.getDestination());
        rating.setRating((float) trip.getRating());
        price.setText(getString(R.string.display_price_format, trip.getPrice()));
        dates.setMinDate(DateConverter.toMillis(trip.getStart_date()));
        dates.setMaxDate(DateConverter.toMillis(trip.getEnd_date()));
    }
}