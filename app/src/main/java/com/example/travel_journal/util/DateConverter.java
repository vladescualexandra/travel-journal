package com.example.travel_journal.util;

import com.example.travel_journal.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

    public static String format(int day, int month, int year) {
        return day + "/" + (month + 1) + "/" + year;
    }

    public static Date fromDTP(int day, int month, int year)  {
        try {
            String format = format(day, month, year);
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.parse(format);
        } catch (ParseException e) {
            return null;
        }
    }
}
