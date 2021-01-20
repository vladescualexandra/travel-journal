package com.example.travel_journal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    public static String USER_PREFS = "user_preferences";
    public static String USERNAME_KEY = "username";
    public static String EMAIL_KEY = "email";

    private TextInputEditText et_username;
    private TextInputEditText et_email;
    private Button btn_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponents();
    }

    private void initComponents() {
        et_username = findViewById(R.id.register_username);
        et_email = findViewById(R.id.register_email);
        btn_save = findViewById(R.id.register_save);

        btn_save.setOnClickListener(saveUserEvent());
    }

    private View.OnClickListener saveUserEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString().trim();
                String email = et_email.getText().toString().trim();

                if (validate(username, email)) {
                    saveUser(username, email);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        };
    }

    private boolean validate(String username, String email) {
        if (username.length() < 3) {
            et_username.setError(getString(R.string.register_invalid_username));
            return false;
        } else {
            et_username.setError(null);
        }
        ;
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError(getString(R.string.register_invalid_email));
            return false;
        } else {
            et_email.setError(null);
        }
        return true;
    }

    private void saveUser(String username, String email) {
        SharedPreferences prefs = getSharedPreferences(USER_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(USERNAME_KEY, username);
        editor.putString(EMAIL_KEY, email);
        editor.apply();
    }
}