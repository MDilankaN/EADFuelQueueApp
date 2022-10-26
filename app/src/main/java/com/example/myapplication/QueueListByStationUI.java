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

        GetListInQueue("11");

    }

    private void GetListInQueue(String id) {
        Call<List<QueueList>> call = jsonPlaceHolderAPI.QueueListOrder(id.trim());
        call.enqueue(new Callback<List<QueueList>>() {
            @Override
            public void onResponse(Call<List<QueueList>> call, Response<List<QueueList>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(QueueListByStationUI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                List<QueueList> queueList = response.body();

                for (QueueList queueListI : queueList) {
                    String content = "ID: " + queueListI.getId() + "\n" + "User ID: " + queueListI.getUserID() + "\n" + "Join Time: " + queueListI.getJoinTime() + "\n\n\n";
                    System.out.println(content);
                    ListItem.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<QueueList>> call, Throwable t) {
                Toast.makeText(QueueListByStationUI.this, "Error : onFailure", Toast.LENGTH_LONG).show();
            }
        });
    }


}