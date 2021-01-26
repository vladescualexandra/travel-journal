package com.example.travel_journal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel_journal.adapter.TripAdapter;
import com.example.travel_journal.database.model.Trip;
import com.example.travel_journal.util.DateConverter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import static com.example.travel_journal.R.*;

public class TripActivity extends AppCompatActivity {

    public static String TRIP_KEY_INSERT = "trip_key_insert";
    public static String TRIP_KEY_UPDATE = "trip_key_update";

    private TextView title;
    private TextInputEditText name;
    private TextInputEditText destination;
    private RadioGroup type;
    private SeekBar price;
    private TextView startDate;
    private TextView endDate;
    private ImageButton editStartDate;
    private ImageButton editEndDate;
    private RatingBar rating;
    private Button btn;

    Intent intent;
    Trip trip;

    private int start_year, start_month, start_day;
    private int end_year, end_month, end_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_trip);
        initComponents();
    }

    private void initComponents() {
        title = findViewById(id.trip_title);
        name = findViewById(id.trip_name);
        destination = findViewById(id.trip_destination);
        type = findViewById(id.trip_type);
        price = findViewById(id.trip_price);
        startDate = findViewById(id.trip_start_date);
        endDate = findViewById(id.trip_end_date);
        editStartDate = findViewById(id.trip_edit_start_date);
        editEndDate = findViewById(id.trip_edit_end_date);
        rating = findViewById(id.trip_rating);
        btn = findViewById(id.trip_save);

        editStartDateEvent();
        editEndDateEvent();
        type.setOnCheckedChangeListener(setTypeEvent());
        price.setOnSeekBarChangeListener(setPriceEvent());
        rating.setOnRatingBarChangeListener(setRatingEvent());

        intent = getIntent();
        trip = (Trip) intent.getSerializableExtra(TripAdapter.TripViewHolder.TRIP_KEY_UPDATE);
        if (trip != null) {
            Log.e("TripActivity - update", trip.toString());
            name.setText(trip.getName());
            destination.setText(trip.getDestination());
            if (trip.getType().equals(Trip.SEA_SIDE)) {
                type.check(id.trip_type_sea_side);
            } else if (trip.getType().equals(Trip.MOUNTAINS)) {
                type.check(id.trip_type_mountains);
            } else {
                type.check(id.trip_type_city_break);
            }

            price.setProgress(trip.getPrice());
            if (trip.getStart_date() != null) {
                startDate.setText(trip.getStart_date());
            }
            if (trip.getEnd_date() != null) {
                endDate.setText(trip.getEnd_date());
            }
            rating.setRating(trip.getRating());
        } else {
            trip = new Trip();
        }
        btn.setOnClickListener(saveTripEvent());
    }


    private View.OnClickListener saveTripEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buildTrip();

                if (trip.getId() > 0) {
                    intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra(TRIP_KEY_UPDATE, trip);
                    Log.e("TripActivity", "update");
                    startActivity(intent);
                } else {
                    Log.e("TripActivity", "insert");
                    intent.putExtra(TRIP_KEY_INSERT, trip);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        };
    }

    private void buildTrip() {
        trip.setName(name.getText().toString());
        trip.setDestination(destination.getText().toString());
        if (trip.getType() == null) {
            trip.setType(Trip.CITY_BREAK);
        }
        trip.setFav(false);

        if (startDate.getText().toString() == null) {
            trip.setStart_date("01/01/2021");
        }
        if (endDate.getText().toString() == null) {
            trip.setEnd_date("01/01/2021");
        }
    }

    private RadioGroup.OnCheckedChangeListener setTypeEvent() {
        return new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case id.trip_type_mountains:
                        trip.setType(Trip.MOUNTAINS);
                        break;
                    case id.trip_type_sea_side:
                        trip.setType(Trip.SEA_SIDE);
                        break;
                    default:
                        trip.setType(Trip.CITY_BREAK);
                        break;
                }
            }
        };
    }

    private RatingBar.OnRatingBarChangeListener setRatingEvent() {
        return new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                trip.setRating((int) rating);
            }
        };
    }

    private SeekBar.OnSeekBarChangeListener setPriceEvent() {
        return new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                trip.setPrice(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
    }

    private void editStartDateEvent() {
        editStartDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                start_year = c.get(Calendar.YEAR);
                start_month = c.get(Calendar.MONTH);
                start_day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(TripActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                startDate.setText(getString(R.string.trip_date_format, dayOfMonth, month + 1, year));
                                String start = DateConverter.format(dayOfMonth, month, year);
                                trip.setStart_date(start);
                            }
                        }, start_year, start_month, start_day);
                datePickerDialog.show();
            }
        });
    }

    private void editEndDateEvent() {
        editEndDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                end_year = c.get(Calendar.YEAR);
                end_month = c.get(Calendar.MONTH);
                end_day = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(TripActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                endDate.setText(getString(R.string.trip_date_format, dayOfMonth, month + 1, year));
                                String end = DateConverter.format(dayOfMonth, month, year);
                                trip.setEnd_date(end);
                            }
                        }, end_year, end_month, end_day);
                datePickerDialog.show();
            }
        });
    }

}