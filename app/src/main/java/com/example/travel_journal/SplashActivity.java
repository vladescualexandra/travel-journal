package com.example.travel_journal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    private final int DISPLAY_DURATION = 2000;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences preferences = getSharedPreferences(RegisterActivity.USER_PREFS, MODE_PRIVATE);
        String username = preferences.getString(RegisterActivity.USERNAME_KEY, null);
        String email = preferences.getString(RegisterActivity.EMAIL_KEY, null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (username == null || email == null) {
                    intent = new Intent(getApplicationContext(), RegisterActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        }, DISPLAY_DURATION);
    }
}