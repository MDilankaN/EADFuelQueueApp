package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class AddStaffUI extends AppCompatActivity {

    Button addStaffBtn;

    EditText username, password, repassword, email, employeeNo;

    String usernameVal, passwordVal, repasswordVal, emailVal, employeeNoVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff_ui);

        addStaffBtn = findViewById(R.id.add_Staff_btn);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        employeeNo = findViewById(R.id.employee_no);
        password = findViewById(R.id.password);
        repassword = findViewById(R.id.confirmation_password);

        addStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameVal = username.getText().toString();
                emailVal = email.getText().toString();
                employeeNoVal = employeeNo.getText().toString();
                passwordVal = password.getText().toString();
                repasswordVal = repassword.getText().toString();

                if (!usernameVal.equals("") && !passwordVal.equals("") && !repasswordVal.equals("") && !emailVal.equals("") && !employeeNoVal.equals("")) {
                    if (!passwordVal.equals(repasswordVal)) {
                        Toast.makeText(AddStaffUI.this, "Passwords are not match", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    addStaff(usernameVal, emailVal, employeeNoVal, passwordVal);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();

                }
            }
        });
    }

    public void addStaff(String username, String email, String employeeNo, String password ) {

        System.out.println(username);
        System.out.println(email);
        System.out.println(employeeNo);
        System.out.println(password);

    }

}
