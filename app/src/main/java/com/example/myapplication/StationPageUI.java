package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class StationPageUI extends AppCompatActivity {

    Button joinQueue, exitBeforeBtn, exitAfterBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_page_ui);

        joinQueue = findViewById(R.id.btn_join);
        exitBeforeBtn = findViewById(R.id.btn_exit_before);
        exitAfterBtn = findViewById(R.id.btn_exit_after);

        joinQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        exitBeforeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        exitAfterBtn.setOnClickListener(new View.OnClickListener() {
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