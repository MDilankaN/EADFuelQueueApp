package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginUI extends AppCompatActivity {

    Button LoginBtn;
    TextView RegisterRedirectBtn;
    TextView ForgotPasswordBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui);

        LoginBtn = findViewById(R.id.login_btn);
        RegisterRedirectBtn = findViewById(R.id.regiter_link);
        ForgotPasswordBtn = findViewById(R.id.forgot_pwd);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(LoginUI.this, HomeUI.class);
                startActivity(intent);
            }
        });

        RegisterRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(LoginUI.this, RegisterUI.class);
                startActivity(intent);
            }
        });

        ForgotPasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(LoginUI.this, ForgotPassword1UI.class);
                startActivity(intent);
            }
        });
    }


}