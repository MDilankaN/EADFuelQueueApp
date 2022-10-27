package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
import com.example.myapplication.models.QueueList;
import com.example.myapplication.models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QueueListByStationUI extends AppCompatActivity {


    TextView ListItem;

    String  id, stationName, address, telephone, openTime, closeTime, imageURL;
    int noOfPumps;

    TextView stationNameView;

    private JasonPlaceHolderAPI jsonPlaceHolderAPI;
    QueueList CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_list_by_station_ui);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            id = extras.getString("id");
            stationName = extras.getString("stationName");
            address = extras.getString("address");
            telephone = extras.getString("telephone");
            openTime = extras.getString("openTime");
            closeTime = extras.getString("closeTime");
            imageURL = extras.getString("imageURL");
            noOfPumps = extras.getInt("noOfPumps");
        }

        stationNameView = findViewById(R.id.manageText);
        ListItem = findViewById(R.id.list_itemname);
        stationNameView.setText(stationName);

        ListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(QueueListByStationUI.this, "Error", Toast.LENGTH_LONG).show();

//                System.out.println(id);
//                Intent intent = new Intent(QueueListByStationUI.this, StationPageUI.class);
//                intent.putExtra("id", id);
//                intent.putExtra("stationName", stationName);
//                intent.putExtra("address", address);
//                intent.putExtra("telephone", telephone);
//                intent.putExtra("openTime", openTime);
//                intent.putExtra("closeTime", closeTime);
//                intent.putExtra("imageURL", imageURL);
//                intent.putExtra("noOfPumps", noOfPumps);
//                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);

        GetQueuesinStaion(stationName);

    }

    private void GetQueuesinStaion(String name) {
        Call<List<Queue>> call = jsonPlaceHolderAPI.getQueuesByStation(name);
        call.enqueue(new Callback<List<Queue>>() {
            @Override
            public void onResponse(Call<List<Queue>> call, Response<List<Queue>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(QueueListByStationUI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                List<Queue> queueList = response.body();
                for (Queue queueListI : queueList) {
                    String content = "ID: " + queueListI.getId() + "\n" + "Queue Name: " + queueListI.getQueueName() + "\n" + "Fuel Status: " + queueListI.getFuelStatus() + "\n\n\n";
                    System.out.println(content);
                    ListItem.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Queue>> call, Throwable t) {
                Toast.makeText(QueueListByStationUI.this, "Error : onFailure", Toast.LENGTH_LONG).show();
            }
        });
    }



}