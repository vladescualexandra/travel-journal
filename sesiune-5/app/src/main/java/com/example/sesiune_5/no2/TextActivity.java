package com.example.sesiune_5.no2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.sesiune_5.R;

public class TextActivity extends AppCompatActivity {

    TextView text;
    Intent intent;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text);

        intent = getIntent();
        id = intent.getIntExtra(ButtonActivity.TEXT_ID, 1);

        text = findViewById(R.id.text);

        switch (id) {
            case 1:
                text.setText(R.string.text_one);
                break;
            case 2:
                text.setText(R.string.text_two);
                break;
            case 3:
                text.setText(R.string.text_three);
                break;
        }
    }
}