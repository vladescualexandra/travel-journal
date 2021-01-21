package com.example.travel_journal;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.travel_journal.model.Trip;
import com.example.travel_journal.model.Type;
import com.example.travel_journal.util.DateConverter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Date;

import static com.example.travel_journal.R.*;

public class TripActivity extends AppCompatActivity {

    public static String TRIP_KEY = "trip_key";

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
        trip = new Trip();
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
        btn.setOnClickListener(saveTripEvent());
    }

    private View.OnClickListener saveTripEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trip.setName(name.getText().toString());
                trip.setDestination(destination.getText().toString());
                intent = new Intent();
                intent.putExtra(TRIP_KEY, trip);
                setResult(RESULT_OK, intent);
                finish();
            }
        };
    }

    private RadioGroup.OnCheckedChangeListener setTypeEvent() {
        return new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case id.trip_type_city_break:
                        trip.setType(Type.CITY_BREAK);
                        break;
                    case id.trip_type_sea_side:
                        trip.setType(Type.SEA_SIDE);
                        break;
                    default:
                        trip.setType(Type.MOUNTAINS);
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
                                Date start = DateConverter.fromDTP(dayOfMonth, month, year);
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
                                Date end = DateConverter.fromDTP(dayOfMonth, month, year);
                                trip.setEnd_date(end);
                            }
                        }, end_year, end_month, end_day);
                datePickerDialog.show();
            }
        });
    }

}