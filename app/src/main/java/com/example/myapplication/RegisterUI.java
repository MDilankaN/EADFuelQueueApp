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

public class RegisterUI extends AppCompatActivity {

    Button RegisterBtn;
    TextView LoginRedirectBtn;

    EditText username, password, repassword, email, vehicleNo;

    String usernameVal, passwordVal, repasswordVal, emailVal, vehicleNoVal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ui);

        RegisterBtn = findViewById(R.id.register_btn);
        LoginRedirectBtn = findViewById(R.id.login_link);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        vehicleNo = findViewById(R.id.vehicle_no);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.confirmation_password);


        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usernameVal = username.getText().toString();
                emailVal = email.getText().toString();
                vehicleNoVal = vehicleNo.getText().toString();
                passwordVal = password.getText().toString();
                repasswordVal = repassword.getText().toString();

                if (!usernameVal.equals("") && !passwordVal.equals("") && !repasswordVal.equals("") && !emailVal.equals("") && !vehicleNoVal.equals("")) {
                    if (!passwordVal.equals(repasswordVal)) {
                        Toast.makeText(RegisterUI.this, "Passwords are not match", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(RegisterUI.this, Register2UI.class);
                    intent.putExtra("username", usernameVal);
                    intent.putExtra("email", emailVal);
                    intent.putExtra("vehicleno", vehicleNoVal);
                    intent.putExtra("password", passwordVal);
                    startActivity(intent);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();

                }

            }
        });

        LoginRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUI.this, LoginUI.class);
                startActivity(intent);
            }
        });
    }

}