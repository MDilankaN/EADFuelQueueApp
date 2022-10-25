package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StationCrudUI extends AppCompatActivity {

    private Button add_station, update_station, remove_station;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_crud_ui);

        add_station = findViewById(R.id.btn_addstation);
        update_station = findViewById(R.id.btn_updatestation);
        remove_station = findViewById(R.id.btn_removestation);

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

    public void openNewActivity(){
        Intent intent = new Intent(this, AddStationUI.class);
        startActivity(intent);
    }

    public void openNewActivity2(){
        Intent intent = new Intent(this, UpdateStationUI.class);
        startActivity(intent);
    }
}