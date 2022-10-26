package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class ChangePasswordUI extends AppCompatActivity {

    Button resetBtn;
    TextView redirectToLogin;
    EditText password, repassword;

    String passwordVal, repasswordVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_ui);

        resetBtn = findViewById(R.id.reset_btn);
        redirectToLogin = findViewById(R.id.back_to_profile_text);

        password = findViewById(R.id.password1);
        repassword = findViewById(R.id.password2);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordVal = password.getText().toString();
                repasswordVal = repassword.getText().toString();

                if (!passwordVal.equals("") && !repasswordVal.equals("")) {
                    if (!passwordVal.equals(repasswordVal)) {
                        Toast.makeText(ChangePasswordUI.this, "Passwords are not match", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    chanagePassword(passwordVal);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();

                }
            }
        });



        redirectToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(ChangePasswordUI.this, EditProfileUI.class);
                startActivity(intent);
            }
        });
    }

    public void chanagePassword(String password) {

        System.out.println(password);

    }

}
