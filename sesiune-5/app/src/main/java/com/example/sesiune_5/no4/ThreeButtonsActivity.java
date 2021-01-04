package com.example.sesiune_5.no4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.sesiune_5.R;
import com.example.sesiune_5.no1.SecondActivity;

public class ThreeButtonsActivity extends AppCompatActivity {

    private static final String TAG = "ThreeButtonsActivity";
    protected static final String EXTRA_STRING_RES_LONG = "extra_string_text_long";
    protected static final String EXTRA_TEXT_LONG = "extra_text_long";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_buttons);

        findViewById(R.id.btnFirst).setOnClickListener(v -> {
            openSecondActivity(R.id.btnFirst);
        });
        findViewById(R.id.btnSecond).setOnClickListener(v -> {
            openSecondActivity(R.id.btnSecond);
        });
        findViewById(R.id.btnThird).setOnClickListener(v -> {
            openSecondActivity(R.id.btnThird);
        });

    }

    private void openSecondActivity(int buttonRes) {
        int stringRes;

        if (buttonRes == R.id.btnFirst) {
            stringRes = R.string.first_long_text;
        } else if (buttonRes == R.id.btnSecond) {
            stringRes = R.string.second_long_text;
        } else if (buttonRes == R.id.btnThird) {
            stringRes = R.string.third_long_text;
        } else {
            stringRes = 0;
            Log.d(TAG, "onCreate: called with buttonRes=[ " + buttonRes + "]");
        }
        String text;
        if (stringRes != 0) {
            text = getString(stringRes);
        } else {
            Log.d(TAG, "openSecondActivity() called with: buttonRes = [" + buttonRes + "].Invalid string resources");
            return;
        }
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_STRING_RES_LONG, stringRes);
        intent.putExtra(EXTRA_TEXT_LONG, text);
        startActivity(intent);
    }
}