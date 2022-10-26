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

public class StaffCrudUI  extends AppCompatActivity {

    Button addStaffBtn, updateStaffBtn, removeStaffBtn, searchBtn;
    EditText search;
    String searchVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_crud_ui);

        addStaffBtn = findViewById(R.id.addStaffBtn);
        updateStaffBtn = findViewById(R.id.updateStaffBtn);
        removeStaffBtn = findViewById(R.id.removeStaffBtn);

        searchBtn = findViewById(R.id.searchStationBtn);
        search = findViewById(R.id.staffField);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchVal = search.getText().toString();

                if (!searchVal.equals("")) {
                    searchStaff(searchVal);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

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
    public void searchStaff(String searchValue) {

        System.out.println(searchValue);

    }
}
