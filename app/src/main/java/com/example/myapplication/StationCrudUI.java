package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.models.Queue;
import com.example.myapplication.models.Station;
import com.google.android.material.snackbar.Snackbar;

import java.util.Stack;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StationCrudUI extends AppCompatActivity {

    private Button add_station, update_station, remove_station, searchBtn;
    EditText search;
    String id,searchVal, stationName, telNo, address, timeOpen, timeClose;
    int noOfPumps;

    TextView result;
    private JasonPlaceHolderAPI jsonPlaceHolderAPI;
    LinearLayout result_box;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_crud_ui);

        add_station = findViewById(R.id.btn_addstation);
        update_station = findViewById(R.id.btn_updatestation);
        remove_station = findViewById(R.id.btn_removestation);

        searchBtn = findViewById(R.id.searchStationBtn);
        search = findViewById(R.id.fuelStationField);
        result = findViewById(R.id.result);
        result_box = findViewById(R.id.searchResultBox);

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

            }
        });

        remove_station.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(StationCrudUI.this)
                        .setTitle("Remove this station")
                        .setMessage("Do you want to remove?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                                jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                                removeStation();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
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

    public void removeStation(){
        Call<Void> call = jsonPlaceHolderAPI.deleteStation(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(StationCrudUI.this, "Failed", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(StationCrudUI.this, "Removed", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(StationCrudUI.this, QueueCrudUI.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(StationCrudUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void searchStation(String searchValue) {

        System.out.println(searchValue);
        Call<Station> call = jsonPlaceHolderAPI.getStationByName(searchValue);
        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(StationCrudUI.this, "Not Found", Toast.LENGTH_LONG).show();
                    return;
                }

                result_box.setVisibility(View.VISIBLE);
                update_station.setVisibility(View.VISIBLE);
                remove_station.setVisibility(View.VISIBLE);

                Station station = response.body();

                id = station.getId();
                stationName = station.getStationName();
                telNo = station.getTelephone();
                address = station.getAddress();
                timeOpen = station.getOpenTime();
                timeClose = station.getCloseTime();
                noOfPumps = station.getNoOfPumps();

                String content = "";
                result.setText(content);
                content += "Station Name: " + station.getStationName() + "\n";
                content += "Station TelNo: " + station.getOpenTime() + "\n";
                content += "Station Address: " + station.getAddress() + "\n";
                content += "Starting Time: " + station.getOpenTime() + "\n";
                content += "Close Time: " + station.getCloseTime() + "\n";
                content += "No of pumps: " + station.getNoOfPumps() + "\n";
//                if (queues.getFuelStatus() == true) {
//                    content += "Fuel Status: Available";
//                }
//                if (queues.getFuelStatus() == false) {
//                    content += "Fuel Status: Unavailable";
//                }

                result.append(content);

                Toast.makeText(StationCrudUI.this, "Result Found", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(StationCrudUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

}