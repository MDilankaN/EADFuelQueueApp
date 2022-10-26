package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class AddQueueUI extends AppCompatActivity {

    TextView TimeTextView1 ;
    Button addQueue, btnOpenTime;

    EditText queueName, stationName;

    String stationNameVal, queueNameVal, openingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_queue_ui);

        btnOpenTime = findViewById(R.id.btn_openTime);

        TimeTextView1 = findViewById(R.id.textView_start_time);

        stationName = findViewById(R.id.station_name);
        queueName = findViewById(R.id.queue_name);

        addQueue = findViewById(R.id.add_queue_btn);

        addQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stationNameVal = stationName.getText().toString();
                queueNameVal = queueName.getText().toString();

                openingTime = TimeTextView1.getText().toString();

                if (!stationNameVal.equals("") && !queueNameVal.equals("")
                        && !TimeTextView1.equals("") ) {
                    addStation(stationNameVal, queueNameVal, openingTime);
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddQueueUI.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
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

    }

    public void addStation(String stationName, String queueName, String openingTime) {

        System.out.println(stationName);
        System.out.println(queueName);
        System.out.println(openingTime);

    }
}