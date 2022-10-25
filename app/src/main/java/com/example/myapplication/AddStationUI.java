package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class AddStationUI extends AppCompatActivity {

    private TextView TimeTextView1, TimeTextView2;
    private  Button addStation, btnOpenTime, btnCloseTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_station_ui);

        btnOpenTime = findViewById(R.id.btn_openTime);
        btnCloseTime = findViewById(R.id.btn_closeTime);

        TimeTextView1 = findViewById(R.id.textView_open_time);
        TimeTextView2 = findViewById(R.id.textView_closing_time);

        addStation =  findViewById(R.id.add_station_btn);

        //https://youtu.be/suMdH7WSewg
        btnOpenTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int hours=calendar.get(Calendar.HOUR_OF_DAY);
                int mins=calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog= new TimePickerDialog(AddStationUI.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c=Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format= new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        TimeTextView1.setText(time);
                    }
                },hours ,mins,false);
                timePickerDialog.show();
            }
        });

        btnCloseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int hours=calendar.get(Calendar.HOUR_OF_DAY);
                int mins=calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog= new TimePickerDialog(AddStationUI.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Calendar c=Calendar.getInstance();
                        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                        c.set(Calendar.MINUTE,minute);
                        c.setTimeZone(TimeZone.getDefault());
                        SimpleDateFormat format= new SimpleDateFormat("k:mm a");
                        String time = format.format(c.getTime());
                        TimeTextView2.setText(time);
                    }
                },hours ,mins,false);
                timePickerDialog.show();
            }
        });


    }


}