package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ForgotPassword2UI extends AppCompatActivity {

    Button SubmitBtn;
    TextView LoginRedirectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_2_ui);

        SubmitBtn = findViewById(R.id.submit_btn);
        LoginRedirectBtn = findViewById(R.id.back_to_login_text2);

        SubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ForgotPassword2UI.this, LoginUI.class);
                startActivity(intent);
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

}