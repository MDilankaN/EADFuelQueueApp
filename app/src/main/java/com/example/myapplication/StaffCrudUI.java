package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StaffCrudUI  extends AppCompatActivity {

    Button addStaffBtn;
    Button updateStaffBtn;
    Button removeStaffBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_crud_ui);

        addStaffBtn = findViewById(R.id.addStaffBtn);
        updateStaffBtn = findViewById(R.id.updateStaffBtn);
        removeStaffBtn = findViewById(R.id.removeStaffBtn);

        addStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(StaffCrudUI.this, AddStaffUI.class);
                startActivity(intent);
            }
        });

        updateStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(StaffCrudUI.this, UpdateStaffUI.class);
                startActivity(intent);
            }
        });

        removeStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
