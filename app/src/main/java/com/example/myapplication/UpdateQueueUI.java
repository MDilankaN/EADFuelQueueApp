package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.models.Queue;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateQueueUI extends AppCompatActivity {

    private TextView TimeTextView1 ;
    private Button updateQueue, btnOpenTime;
    Switch fuelStatus;
    String id, queueNameVal, queueListIdVal, stationNameVal, openingTime;
    boolean fuelStatusVal;

    EditText queueName, stationName, queueId;


    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_queue_ui);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            fuelStatusVal = extras.getBoolean("fuelStatus");
            queueListIdVal = extras.getString("queueListId");
            queueNameVal = extras.getString("queueName");
            stationNameVal = extras.getString("stationName");
            openingTime = extras.getString("time");
        }

        TimeTextView1 = findViewById(R.id.textView_start_time);
        queueId = findViewById(R.id.queue_id);
        fuelStatus = findViewById(R.id.switch3);
        stationName = findViewById(R.id.station_name);
        queueName = findViewById(R.id.queue_name);
        btnOpenTime = findViewById(R.id.btn_openTime);
        updateQueue = findViewById(R.id.update_queue_btn);

        stationName.setText(stationNameVal);
        queueId.setText(queueListIdVal);
        queueName.setText(queueNameVal);
        fuelStatus.setChecked(fuelStatusVal);
        TimeTextView1.setText(openingTime);

        updateQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openingTime = TimeTextView1.getText().toString();
                stationNameVal = stationName.getText().toString();
                queueNameVal = queueName.getText().toString();
                queueListIdVal = queueId.getText().toString();
                fuelStatusVal = fuelStatus.isChecked();

                if (!stationNameVal.equals("") && !queueNameVal.equals("") && !queueListIdVal.equals("")
                        && !openingTime.equals("") ) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                    jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                    updateStation(id, stationNameVal, queueNameVal, queueListIdVal, openingTime, fuelStatusVal);
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
                TimePickerDialog timePickerDialog = new TimePickerDialog(UpdateQueueUI.this, androidx.appcompat.R.style.Theme_AppCompat_Dialog, new TimePickerDialog.OnTimeSetListener() {
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

    public void updateStation(String id, String stationName, String queueName, String queueListId, String openingTime, boolean fuelStatus) {

        Queue queue = new Queue(id, queueName, stationName, queueListId, openingTime, fuelStatus);
        Call<Queue> call = jsonPlaceHolderAPI.updateQueue(id,queue);
        call.enqueue(new Callback<Queue>() {
            @Override
            public void onResponse(Call<Queue> call, Response<Queue> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(UpdateQueueUI.this, "Update unsuccessful", Toast.LENGTH_LONG).show();
                    System.out.println(response);
                    return;
                }

                Queue queue = (Queue) response.body();
                System.out.println("queue");
                System.out.println(queue.getQueueName());
                System.out.println(queue.getStationName());
                System.out.println(queue.getQueueListID());

                Toast.makeText(UpdateQueueUI.this, "Added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UpdateQueueUI.this, QueueCrudUI.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Queue> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(UpdateQueueUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }

        });



    }
}