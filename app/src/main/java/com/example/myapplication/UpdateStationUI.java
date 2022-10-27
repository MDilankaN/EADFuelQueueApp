package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.models.Station;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateStationUI extends AppCompatActivity {
    private TextView TimeTextView1, TimeTextView2;
    private Button updateStation, btnOpenTime, btnCloseTime;

    EditText stationName, stationTel, stationAddress1;

    String id, stationNameVal, stationTelVal, stationAddress1Val,openingTime, closingTime;
    int noOfPumps;
    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_station_ui);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            stationNameVal = extras.getString("stationName");
            stationAddress1Val = extras.getString("address");
            stationTelVal = extras.getString("telNo");
            openingTime = extras.getString("timeOpen");
            closingTime = extras.getString("timeClose");
            noOfPumps = extras.getInt("noOfPumps");
        }

        btnOpenTime = findViewById(R.id.btn_openTime);
        btnCloseTime = findViewById(R.id.btn_closeTime);
        TimeTextView1 = findViewById(R.id.textView_open_time);
        TimeTextView2 = findViewById(R.id.textView_closing_time);
        stationName = findViewById(R.id.update_station_name);
        stationTel = findViewById(R.id.update_station_tel);
        stationAddress1 = findViewById(R.id.update_station_address1);
//        stationAddress2 = findViewById(R.id.update_station_address2);
        updateStation = findViewById(R.id.update_station_btn);


        stationName.setText(stationNameVal);
        stationTel.setText(stationTelVal);
        stationAddress1.setText(stationAddress1Val);
//        stationAddress2.setText("");
        TimeTextView1.setText(openingTime);
        TimeTextView2.setText(closingTime);



        updateStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stationNameVal = stationName.getText().toString();
                stationTelVal = stationTel.getText().toString();
                stationAddress1Val = stationAddress1.getText().toString();
//                stationAddress2Val = stationAddress2.getText().toString();

                openingTime = TimeTextView1.getText().toString();
                closingTime = TimeTextView2.getText().toString();

                if (!stationNameVal.equals("") && !stationTelVal.equals("") && !stationAddress1Val.equals("")
                        && !openingTime.equals("") && !closingTime.equals("")) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                    jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                    updateStation(id, stationNameVal, stationTelVal, stationAddress1Val, openingTime, closingTime, noOfPumps);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateStationUI.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateStationUI.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
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

    public void updateStation(String id, String stationName, String stationTelNo, String stationAddress, String openingTime, String closingTime, int noOfPumps) {

        System.out.println(id);
        System.out.println(stationName);
        System.out.println(stationTelNo);
        System.out.println(stationAddress1);
//        System.out.println(stationAddress2);
        System.out.println(openingTime);
        System.out.println(this.closingTime);
        System.out.println(String.valueOf(noOfPumps));

//        String Address = stationAddress1 + " " + stationAddress2;
        Station station = new Station(id, stationName, stationAddress, stationTelNo, openingTime, this.closingTime, "", noOfPumps);
        Call<Station> call = jsonPlaceHolderAPI.updateStation(id,station);
        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateStationUI.this, "Update unsuccessful", Toast.LENGTH_LONG).show();
                    System.out.println(response);
                    return;
                }
                Station station = (Station) response.body();
                System.out.println("station");
                System.out.println(station.getStationName());
                System.out.println(station.getTelephone());
                System.out.println(station.getAddress());

                Toast.makeText(UpdateStationUI.this, "Updated", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateStationUI.this, StationCrudUI.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(UpdateStationUI.this, "Added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateStationUI.this, StationCrudUI.class);
                startActivity(intent);
//                Toast.makeText(UpdateQueueUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}