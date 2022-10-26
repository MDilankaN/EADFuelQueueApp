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
import com.example.myapplication.models.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QueueCrudUI extends AppCompatActivity {

    private Button add_queue, update_queue, remove_queue, searchBtn;;
    EditText search;
    String searchVal, id, queueName, queueListId, stationName, time;
    boolean fuelStatus;
    TextView result;
    LinearLayout result_box;
    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_crud_ui);

        result = findViewById(R.id.result);

        add_queue = findViewById(R.id.btn_addqueue);
        update_queue = findViewById(R.id.btn_updatequeue);
        remove_queue = findViewById(R.id.btn_removequeue);
        result_box = findViewById(R.id.searchResultBox);

        result_box.setVisibility(View.GONE);
        update_queue.setVisibility(View.GONE);
        remove_queue.setVisibility(View.GONE);

        searchBtn = findViewById(R.id.searchStationBtn);
        search = findViewById(R.id.fuelQueueField);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchVal = search.getText().toString();

                if (!searchVal.equals("")) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                    jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                    searchQueue(searchVal);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        add_queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewActivity();
            }
        });

        update_queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(id);
                Intent intent = new Intent(QueueCrudUI.this, UpdateQueueUI.class);
                intent.putExtra("id", id);
                intent.putExtra("stationName", stationName);
                intent.putExtra("queueName", queueName);
                intent.putExtra("queueListId", queueListId);
                intent.putExtra("fuelStatus", fuelStatus);
                intent.putExtra("time", time);
                startActivity(intent);
            }
        });

        remove_queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(QueueCrudUI.this)
                    .setTitle("Remove this queue")
                    .setMessage("Do you want to remove?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                            jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                            removeQueue();
                        }
                    })
                    .setNegativeButton("No", null)
                    .show();
            }
        });
    }

    public void removeQueue(){

        Call<Void> call = jsonPlaceHolderAPI.deleteQueue(id);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(QueueCrudUI.this, "Failed", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(QueueCrudUI.this, "Removed", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(QueueCrudUI.this, QueueCrudUI.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(QueueCrudUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }
        });
    }


    public void searchQueue(String searchValue) {

        System.out.println(searchValue);
        Call<Queue> call = jsonPlaceHolderAPI.getQueueByName(searchValue);
        call.enqueue(new Callback<Queue>() {
            @Override
            public void onResponse(Call<Queue> call, Response<Queue> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(QueueCrudUI.this, "Not Found", Toast.LENGTH_LONG).show();
                    return;
                }

                result_box.setVisibility(View.VISIBLE);
                update_queue.setVisibility(View.VISIBLE);
                remove_queue.setVisibility(View.VISIBLE);

                Queue queues = response.body();

                id = queues.getId();
                queueName = queues.getQueueName();
                queueListId = queues.getQueueListID();
                stationName = queues.getStationName();
                fuelStatus = queues.getFuelStatus();
                time = queues.getStartingTime();

                String content = "";
                result.setText(content);
                content += "Queue List ID: " + queues.getQueueListID() + "\n";
                content += "Queue Name: " + queues.getQueueName() + "\n";
                content += "Station Name: " + queues.getStationName() + "\n";
                content += "Starting Time: " + queues.getStartingTime() + "\n";
                if(queues.getFuelStatus() == true){
                    content += "Fuel Status: Available";
                }
                if(queues.getFuelStatus() == false){
                    content += "Fuel Status: Unavailable";
                }

                result.append(content);

                Toast.makeText(QueueCrudUI.this, "Result Found", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<Queue> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(QueueCrudUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }

        });

    }

    public void openNewActivity(){
        Intent intent = new Intent(this, AddQueueUI.class);
        startActivity(intent);
    }

}



//        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
//        jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
//
//        Call<List<Queue>> call = jsonPlaceHolderAPI.getQueues();
//
//        call.enqueue(new Callback<List<Queue>>() {
//            @Override
//            public void onResponse(Call<List<Queue>> call, Response<List<Queue>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(QueueCrudUI.this, "Not Found", Toast.LENGTH_LONG).show();
//                    return;
//                }
//
//                List<Queue> queues = response.body();
//                System.out.println("List of queues");
//
//                for (Queue queue : queues) {
//                    String content = "";
//                    content += "ID: " + queue.getQueueListID() + "\n";
//                    content += "Queue Name: " + queue.getQueueName() + "\n";
//                    content += "Station Name: " + queue.getStationName() + "\n";
//                    content += "Starting Time: " + queue.getStartingTime() + "\n";
//                    if(queue.getFuelStatus() == true){
//                        content += "Fuel Status: Available\n\n";
//                    }
//                    if(queue.getFuelStatus() == false){
//                        content += "Fuel: Unavailable\n\n";
//                    }
//
//                    result.append(content);
//                }
//
//                Toast.makeText(QueueCrudUI.this, "Result Found", Toast.LENGTH_LONG).show();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Queue>> call, Throwable t) {
//                System.out.println(t);
//                Toast.makeText(QueueCrudUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
//            }
//
//        });
