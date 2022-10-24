package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginUI extends AppCompatActivity {

    private Button privacy, contactus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ui);

        privacy = findViewById(R.id.button2);
        contactus = findViewById(R.id.button3);

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity2();
            }
        });

    }

    public void openNewActivity(){
        Intent intent = new Intent(this, PrivacyAndPolicy.class);
        startActivity(intent);
    }

    public void openNewActivity2(){
        Intent intent = new Intent(this, ContactUs.class);
        startActivity(intent);
    }
}