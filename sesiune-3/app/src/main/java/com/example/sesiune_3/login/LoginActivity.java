package com.example.sesiune_3.login;

import android.content.Intent;
import android.os.Bundle;
import android.os.PatternMatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sesiune_3.MainActivity;
import com.example.sesiune_3.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    TextInputLayout til_email;
    TextInputLayout til_password;
    TextInputEditText email;
    TextInputEditText password;
    CheckBox acceptTC;
    Button submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initComponents();

    }

    private void initComponents() {
        til_email = findViewById(R.id.login_til_email);
        til_password = findViewById(R.id.login_til_password);
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        acceptTC = findViewById(R.id.login_acceptTC);
        submit = findViewById(R.id.login_submit);

        submit.setOnClickListener(submitEvent());
        acceptTC.setOnCheckedChangeListener(acceptTC());
    }

    private CompoundButton.OnCheckedChangeListener acceptTC() {
        return new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (acceptTC.isChecked()) {
                    submit.setEnabled(true);
                } else {
                    submit.setEnabled(false);
                }
            }
        };
    }

    private View.OnClickListener submitEvent() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches()) {
                    til_email.setError("Invalid email.");
                } else if (password.getText().length() < 3) {
                    til_password.setError("Invalid password.");
                } else {
                    til_email.setError(null);
                    til_password.setError(null);

                    Toast.makeText(getApplicationContext(),
                            email.getText() + "/" + password.getText(),
                            Toast.LENGTH_LONG)
                            .show();

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        };
    }
}
