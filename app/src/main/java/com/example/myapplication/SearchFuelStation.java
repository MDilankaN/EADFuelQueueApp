package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.api.JasonPlaceHolderAPI;

public class SearchFuelStation extends AppCompatActivity {

    Button addStaffBtn;
    EditText username, password, repassword, email, empno;
    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    LinearLayout stationDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_fuel_station_ui);

        stationDetails = findViewById(R.id.stationDetails);

        stationDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchFuelStation.this, StationPageUI.class);
                startActivity(intent);
            }
        });

    }

}
