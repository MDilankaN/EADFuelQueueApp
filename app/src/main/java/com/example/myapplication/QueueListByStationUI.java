package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.models.Queue;
import com.example.myapplication.models.QueueList;
import com.example.myapplication.models.Station;
import com.example.myapplication.models.User;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.annotations.SerializedName;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QueueListByStationUI extends AppCompatActivity {


    ListView ListView;

    String  id, stationName, address, telephone, openTime, closeTime, imageURL;
    int noOfPumps;
    List<String> queueNameList  = new ArrayList<>();
    String content = "";
    TextView stationNameView;

    private JasonPlaceHolderAPI jsonPlaceHolderAPI;
    QueueList CurrentUser;

    String queue_id, queue_name, queue_station_name, queue_queue_list_id, queue_starting_time, queue_fuel_status, queue_data;
    String[] split_words;

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
        ListView = findViewById(R.id.listView);
        stationNameView.setText(stationName);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
        GetListInQueue();

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                String value = (String)adapterView.getItemAtPosition(position);

                queue_data = value;
                split_words = queue_data.split("\nQueue ID:");

                queue_queue_list_id = split_words[1];

                Intent intent = new Intent(QueueListByStationUI.this, StationPageUI.class);
                intent.putExtra("id", id);
                intent.putExtra("stationName", stationName);
                intent.putExtra("address", address);
                intent.putExtra("telephone", telephone);
                intent.putExtra("openTime", openTime);
                intent.putExtra("closeTime", closeTime);
                intent.putExtra("imageURL", imageURL);
                intent.putExtra("noOfPumps", noOfPumps);
                intent.putExtra("queue_list_id", queue_queue_list_id);
                startActivity(intent);

            }
        });

    }

    private void GetListInQueue() {
        Call<List<Queue>> call = jsonPlaceHolderAPI.getQueues();
        call.enqueue(new Callback<List<Queue>>() {
            @Override
            public void onResponse(Call<List<Queue>> call, Response<List<Queue>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(QueueListByStationUI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                for (Queue p : response.body()) {
                    if(stationName.equals(p.getStationName().toString())){
//                        String content = "";
//                        content = "Station Name: " + p.getStationName() + "\n" + "Queue Name: " + p.getQueueName() + "\n";
//                        if(p.getFuelStatus() == true){
//                            content += "Fuel Status: Available";
//                        }
//                        if(p.getFuelStatus() == false){
//                            content += "Fuel Status: Unavailable";
//                        }
                        queueNameList.add(p.getQueueName() + "\nQueue ID:" + p.getQueueListID());
                    }
                }
                if(queueNameList.size() == 0){
                    Toast.makeText(QueueListByStationUI.this, "No queues opened at the moment", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent( QueueListByStationUI.this, SearchFuelStation.class);
                    startActivity(intent);
                }
                ArrayAdapter adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.activity_queuelist_textview, queueNameList);
                ListView.setAdapter(adapter);


            }
            @Override
            public void onFailure(Call<List<Queue>> call, Throwable t) {
                Toast.makeText(QueueListByStationUI.this, "Error : onFailure", Toast.LENGTH_LONG).show();
            }
        });
    }


}