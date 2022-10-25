package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgotPassword1UI extends AppCompatActivity {

    Button NextBtn;
    TextView LoginRedirectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_1_ui);

        NextBtn = findViewById(R.id.next_btn);
        LoginRedirectBtn = findViewById(R.id.back_to_login_text);

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ForgotPassword1UI.this, ForgotPassword2UI.class);
                startActivity(intent);
            }
        });

        LoginRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ForgotPassword1UI.this, LoginUI.class);
                startActivity(intent);
            }
        });
    }

}