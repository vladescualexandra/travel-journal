package com.example.travel_journal.util;

import android.annotation.SuppressLint;

import com.example.travel_journal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    public static String format(int day, int month, int year) {
        return day + "/" + (month + 1) + "/" + year;
    }

    public static long toMillis(String date) {
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        if (date != null) {
            try {
                Date parsedDate = sdf.parse(date);
                return parsedDate.getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

}
