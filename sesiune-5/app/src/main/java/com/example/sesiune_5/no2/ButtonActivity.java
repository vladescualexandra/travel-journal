package com.example.sesiune_5.no2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.sesiune_5.R;

public class ButtonActivity extends AppCompatActivity {

    public static final String TEXT_ID= "TEXT ID";
    Button btn1;
    Button btn2;
    Button btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);

        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);

        btn1.setOnClickListener(openTextActivity(1));
        btn2.setOnClickListener(openTextActivity(2));
        btn3.setOnClickListener(openTextActivity(3));

    }

    private View.OnClickListener openTextActivity(int i) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),
                        ButtonActivity.class);
                intent.putExtra(TEXT_ID, i);
                startActivity(intent);

            }
        };
    }
}