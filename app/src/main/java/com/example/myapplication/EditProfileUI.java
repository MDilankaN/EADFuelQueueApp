package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class EditProfileUI extends AppCompatActivity {

    Button updateBtn;
    Button changePasswordBtn;
    Button deleteMyAccountBtn;

    EditText username, email, vehicleNo, password, repassword;

    String usernameVal, emailVal, vehicleNoVal, passwordVal, repasswordVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_ui);

        updateBtn = findViewById(R.id.update_btn);
        changePasswordBtn = findViewById(R.id.change_password_btn);
        deleteMyAccountBtn = findViewById(R.id.delete_account_btn);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        vehicleNo = findViewById(R.id.vehicle_no);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.confirmation_password);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameVal = username.getText().toString();
                emailVal = email.getText().toString();
                vehicleNoVal = vehicleNo.getText().toString();
                passwordVal = password.getText().toString();
                repasswordVal = repassword.getText().toString();

                if (!usernameVal.equals("") && !emailVal.equals("") && !vehicleNoVal.equals("")  && !passwordVal.equals("") && !repasswordVal.equals("")) {
                    if (!passwordVal.equals(repasswordVal)) {
                        Toast.makeText(EditProfileUI.this, "Passwords are not match", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    updateProfile(usernameVal, emailVal, vehicleNoVal, passwordVal);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();

                }
            }
        });

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(EditProfileUI.this, ChangePasswordUI.class);
                startActivity(intent);
            }
        });
    }

    public void updateProfile(String username, String email, String employeeNo, String password ) {

        System.out.println(username);
        System.out.println(email);
        System.out.println(employeeNo);
        System.out.println(password);

    }
}
