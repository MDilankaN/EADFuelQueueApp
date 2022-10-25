package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordUI extends AppCompatActivity {

    Button resetBtn;
    TextView redirectToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_ui);

        resetBtn = findViewById(R.id.reset_btn);
        redirectToLogin = findViewById(R.id.back_to_profile_text);

        redirectToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ChangePasswordUI.this, EditProfileUI.class);
                startActivity(intent);
            }
        });
    }

}
