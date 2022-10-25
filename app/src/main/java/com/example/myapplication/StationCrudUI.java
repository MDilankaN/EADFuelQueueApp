package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StationCrudUI extends AppCompatActivity {

    Button addStationBtn;
    Button updateStationBtn;
    Button removeStationBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_crud_ui);

        addStationBtn = findViewById(R.id.addStaffBtn);
        updateStationBtn = findViewById(R.id.updateStaffBtn);
        removeStationBtn = findViewById(R.id.removeStaffBtn);

        addStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(StationCrudUI.this, UpdateStationUI.class);
                startActivity(intent);
            }
        });

        updateStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(StationCrudUI.this, UpdateStationUI.class);
                startActivity(intent);
            }
        });

        removeStationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
