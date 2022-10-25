package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ForgotPassword1UI extends AppCompatActivity {

    Button NextBtn;
    TextView LoginRedirectBtn;
    EditText username;

    String usernameVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_1_ui);

        NextBtn = findViewById(R.id.next_btn);
        LoginRedirectBtn = findViewById(R.id.back_to_login_text);

        username = findViewById(R.id.username);

        NextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameVal = username.getText().toString();

                if (!usernameVal.equals("")) {
                    Intent intent = new Intent(ForgotPassword1UI.this, ForgotPassword2UI.class);
                    intent.putExtra("username", usernameVal);
                    startActivity(intent);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();
                }
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