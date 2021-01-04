package com.example.sesiune_5.no1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.sesiune_5.R;

public class FirstActivity extends AppCompatActivity {

    public static final String FIRST_ACTIVITY = "First Activity";
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btn = findViewById(R.id.first_activity_button);
        btn.setOnClickListener(openSecondActivity());

        Log.i(FIRST_ACTIVITY, "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(FIRST_ACTIVITY, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(FIRST_ACTIVITY, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(FIRST_ACTIVITY, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(FIRST_ACTIVITY, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(FIRST_ACTIVITY, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(FIRST_ACTIVITY, "onRestart");
    }

    private View.OnClickListener openSecondActivity() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
                startActivity(intent);
            }
        };
    }
}