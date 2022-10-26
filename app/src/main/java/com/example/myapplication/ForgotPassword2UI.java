package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassword2UI extends AppCompatActivity {

    Button SubmitBtn;
    TextView LoginRedirectBtn;
    String username, passwordVal, confirmationPasswordVal;
    EditText password, confirmationPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
        }
        setContentView(R.layout.activity_forgot_password_2_ui);

        SubmitBtn = findViewById(R.id.submit_btn);
        LoginRedirectBtn = findViewById(R.id.back_to_login_text2);
        password = findViewById(R.id.password1);
        confirmationPassword = findViewById(R.id.password2);

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordVal = password.getText().toString();
                confirmationPasswordVal = confirmationPassword.getText().toString();
                resetPassword(username, passwordVal, confirmationPasswordVal);
            }
        });

        LoginRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ForgotPassword2UI.this, LoginUI.class);
                startActivity(intent);
            }
        });
    }

    public void resetPassword(String username, String password, String confirmationPassword) {

    }

}