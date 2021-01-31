package com.example.travel_journal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.travel_journal.adapter.TripAdapter;
import com.example.travel_journal.async.Callback;
import com.example.travel_journal.database.model.Trip;
import com.example.travel_journal.database.service.TripService;
import com.example.travel_journal.util.DateConverter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.net.HttpURLConnection;
import java.util.Calendar;
import java.util.Date;

public class DisplayActivity extends AppCompatActivity {

    private TextView name;
    private TextView destination;
    private RatingBar rating;
    private TextView price;
    private CalendarView dates;
    private FloatingActionButton fab_delete;

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
        fab_delete = findViewById(R.id.display_delete);
        fab_delete.setOnClickListener(deleteTrip());
        intent = getIntent();
        trip = (Trip) intent.getSerializableExtra(TripAdapter.TripViewHolder.TRIP_KEY_DISPLAY);
        if (trip != null) {
            buildViews();
        }
    }

    private void getWeather() {

    }

    private View.OnClickListener deleteTrip() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(DisplayActivity.this);
                alertDialog.setTitle("Delete trip");
                alertDialog.setMessage("Are you sure you want to delete this entry?");
                alertDialog.setPositiveButton(getString(R.string.delete_yes), deleteEvent());
                alertDialog.setNegativeButton(getString(R.string.delete_no), null);
                alertDialog.show();
            }
        };
    }

    private DialogInterface.OnClickListener deleteEvent() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TripService tripService = new TripService(getApplicationContext());
                tripService.delete(trip, deleteTripFromDBCallback());
            }
        };
    }

    private Callback<Integer> deleteTripFromDBCallback() {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUIThread(Integer result) {
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        };
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