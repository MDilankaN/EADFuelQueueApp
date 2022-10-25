package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileUI extends AppCompatActivity {

    Button updateBtn;
    Button changePasswordBtn;
    Button deleteMyAccountBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_ui);

        updateBtn = findViewById(R.id.update_btn);
        changePasswordBtn = findViewById(R.id.change_password_btn);
        deleteMyAccountBtn = findViewById(R.id.delete_account_btn);

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(EditProfileUI.this, ChangePasswordUI.class);
                startActivity(intent);
            }
        });
    }

}
