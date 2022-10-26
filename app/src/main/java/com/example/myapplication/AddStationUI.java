package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.models.Station;
import com.example.myapplication.models.User;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddStationUI extends AppCompatActivity {

    private TextView TimeTextView1, TimeTextView2;
    private Button addStation, btnOpenTime, btnCloseTime;
    String ImageURL = "";

    EditText stationName, stationTel, stationAddress1, stationAddress2, noOfPumps;

    String stationNameVal, stationTelVal, stationAddress1Val, stationAddress2Val, openingTime, closingTime, imgVal;
    Integer no_of_pumps;

    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station_ui);

        btnOpenTime = findViewById(R.id.btn_openTime);
        btnCloseTime = findViewById(R.id.btn_closeTime);

        TimeTextView1 = findViewById(R.id.textView_open_time);
        TimeTextView2 = findViewById(R.id.textView_closing_time);

        stationName = findViewById(R.id.station_name);
        stationTel = findViewById(R.id.station_tel);
        stationAddress1 = findViewById(R.id.station_address1);
        stationAddress2 = findViewById(R.id.station_address2);
        noOfPumps = findViewById(R.id.station_noOfPumps);

        addStation = findViewById(R.id.add_station_btn);

        addStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stationNameVal = stationName.getText().toString();
                stationTelVal = stationTel.getText().toString();
                stationAddress1Val = stationAddress1.getText().toString();
                stationAddress2Val = stationAddress2.getText().toString();

                openingTime = TimeTextView1.getText().toString();
                closingTime = TimeTextView2.getText().toString();

                no_of_pumps = Integer.valueOf(noOfPumps.getText().toString());
                imgVal = ImageURL.toString();



                if (!stationNameVal.equals("") && !stationTelVal.equals("") && !stationAddress1Val.equals("") && !stationAddress2Val.equals("")
                        && !TimeTextView1.equals("") && !TimeTextView2.equals("")) {
                    addStation(stationNameVal, stationAddress1Val, stationAddress2Val, stationTelVal, openingTime, closingTime, imgVal, no_of_pumps);

                    //add data
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                    jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                    addStation(stationName.toString(), stationAddress1.toString(), stationAddress2.toString(), stationTel.toString(), TimeTextView1.toString(), TimeTextView2.toString(), ImageURL, 5);
                    addStation.setText("Loading...");
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();
                    return;
                }


            }
        });

        //https://youtu.be/suMdH7WSewg
        btnOpenTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddStationUI.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time1 = format.format(c.getTime());
                        TimeTextView1.setText(time1);
                    }
                }, hours, mins, false);
                timePickerDialog.show();
            }
        });

        btnCloseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hours = calendar.get(Calendar.HOUR_OF_DAY);
                int mins = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddStationUI.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c = Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        c.set(Calendar.MINUTE, minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format = new SimpleDateFormat("k:mm a");
                        String time2 = format.format(c.getTime());
                        TimeTextView2.setText(time2);
                    }
                }, hours, mins, false);
                timePickerDialog.show();
            }
        });


    }

    public void addStation(String stationName, String stationAddress1, String stationAddress2, String stationTelNo, String openingTime, String closingTime, String imageURL, int noOfPumps){

        String Address = stationAddress1 +" " + stationAddress2;
        Station station = new Station(stationName, Address, stationTelNo, openingTime, closingTime, imageURL, noOfPumps);

        Call<Station> call = jsonPlaceHolderAPI.createStation(station);

        System.out.println(stationName);
        System.out.println(stationTelNo);
        System.out.println(stationAddress1);
        System.out.println(stationAddress2);
        System.out.println(openingTime);
        System.out.println(closingTime);

        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddStationUI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(AddStationUI.this, "Successfully registered", Toast.LENGTH_LONG).show();
                Station stationRes = response.body();
                System.out.println("stationRes");
                System.out.println(stationRes.getStationName());
                System.out.println(stationRes.getAddress());
                System.out.println(stationRes.getOpenTime());
                System.out.println(stationRes.getCloseTime());
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Toast.makeText(AddStationUI.this, "Error : onFailure", Toast.LENGTH_LONG).show();
            }
        });

    }
}