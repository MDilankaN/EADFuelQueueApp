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

public class AddQueueUI extends AppCompatActivity {

    TextView TimeTextView1 ;
    Button addQueue, btnOpenTime;

    EditText queueName, stationName, queueId;

    boolean fuelStatus;

    String stationNameVal, queueNameVal, queueIdVal, openingTime;

    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_queue_ui);

        btnOpenTime = findViewById(R.id.btn_openTime);

        TimeTextView1 = findViewById(R.id.textView_start_time);

        stationName = findViewById(R.id.station_name);
        queueName = findViewById(R.id.queue_name);
        queueId = findViewById(R.id.queue_id);
        fuelStatus = true;

        addQueue = findViewById(R.id.add_queue_btn);

        addQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stationNameVal = stationName.getText().toString();
                queueNameVal = queueName.getText().toString();
                queueIdVal = queueId.getText().toString();

                openingTime = TimeTextView1.getText().toString();

                if (!stationNameVal.equals("") && !queueNameVal.equals("") && !queueIdVal.equals("")
                        && !openingTime.equals("") ) {

                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();

                    jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                    addQueue(queueNameVal, stationNameVal, queueIdVal,openingTime, fuelStatus);
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

    public void addQueue( String queueName, String stationName, String queueListId, String openingTime, boolean fuelStatus) {

        Queue queue = new Queue(queueName, stationName, queueListId, openingTime, fuelStatus);

        System.out.println(stationName);
        System.out.println(queueName);
        System.out.println(queueListId);
        System.out.println(openingTime);
        System.out.println(fuelStatus);

        Call<Queue> call = jsonPlaceHolderAPI.createQueue(queue);
        call.enqueue(new Callback<Queue>() {
            @Override
            public void onResponse(Call<Queue> call, Response<Queue> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddQueueUI.this, "Username or Password Incorrect", Toast.LENGTH_LONG).show();
                    return;
                }

                Queue queue = (Queue) response.body();
                System.out.println("queue");
                System.out.println(queue.getQueueName());
                System.out.println(queue.getStationName());
                System.out.println(queue.getQueueListID());

                Toast.makeText(AddQueueUI.this, "Added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AddQueueUI.this, QueueCrudUI.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Queue> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(AddQueueUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }
        });




    }
}