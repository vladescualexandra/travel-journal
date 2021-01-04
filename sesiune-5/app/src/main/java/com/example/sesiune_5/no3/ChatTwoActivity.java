package com.example.sesiune_5.no3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.sesiune_5.R;

public class ChatTwoActivity extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_two);

        intent = getIntent();
        String message = intent.getStringExtra(ChatOneActivity.SEND_MESSAGE);

        if (message != null) {

        }

    }
}