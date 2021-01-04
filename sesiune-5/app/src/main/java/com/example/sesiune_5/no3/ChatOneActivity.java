package com.example.sesiune_5.no3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sesiune_5.R;

public class ChatOneActivity extends AppCompatActivity {

    public static final String SEND_MESSAGE = "SEND_MESSAGE";
    private EditText inputField;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_one);

        inputField = findViewById(R.id.et_chat_one);
        btn = findViewById(R.id.btn_chat_one);
        btn.setOnClickListener(sendMessage());
    }

    private View.OnClickListener sendMessage() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = inputField.getText().toString();
                Intent intent = new Intent(getApplicationContext(),
                        ChatTwoActivity.class);
                intent.putExtra(SEND_MESSAGE, message);
                startActivity(intent);
            }
        };
    }
}