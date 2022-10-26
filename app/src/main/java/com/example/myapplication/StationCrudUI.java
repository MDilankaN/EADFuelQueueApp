package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class StationCrudUI extends AppCompatActivity {

    private Button add_station, update_station, remove_station, searchBtn;
    EditText search;
    String searchVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_crud_ui);

        add_station = findViewById(R.id.btn_addstation);
        update_station = findViewById(R.id.btn_updatestation);
        remove_station = findViewById(R.id.btn_removestation);

        searchBtn = findViewById(R.id.searchStationBtn);
        search = findViewById(R.id.fuelStationField);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchVal = search.getText().toString();

                if (!searchVal.equals("")) {
                    searchStation(searchVal);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        add_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });

        update_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity2();
            }
        });

        remove_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void searchStation(String searchValue) {

        System.out.println(searchValue);

    }

    public void openNewActivity(){
        Intent intent = new Intent(this, AddStationUI.class);
        startActivity(intent);
    }

    public void openNewActivity2(){
        Intent intent = new Intent(this, UpdateStationUI.class);
        startActivity(intent);
    }
}