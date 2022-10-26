package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class StationPageUI extends AppCompatActivity {


    TextView ListItem;

    String  id, stationName, address, telephone, openTime, closeTime, imageURL;
    int noOfPumps;

    TextView stationNameView, addressView, openingTimeView, telNoView;


    Button joinQueue, exitBeforeBtn, exitAfterBtn;
    private JasonPlaceHolderAPI jsonPlaceHolderAPI;
    QueueList CurrentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_page_ui);
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
        addressView = findViewById(R.id.stationAddress);
        openingTimeView = findViewById(R.id.stationOpeningTime);
        telNoView = findViewById(R.id.stationTelNo);

        joinQueue = findViewById(R.id.btn_join);
        exitBeforeBtn = findViewById(R.id.btn_exit_before);
        exitAfterBtn = findViewById(R.id.btn_exit_after);

        ListItem = findViewById(R.id.list_itemname);

        stationNameView.setText(stationName);
        addressView.setText(address);
        telNoView.setText(telephone);
        openingTimeView.setText("Opens at " + openTime);

        exitBeforeBtn.setVisibility(View.GONE);
        exitAfterBtn.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
        jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);

        GetListInQueue("11");

        joinQueue.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Date currentTime = Calendar.getInstance().getTime();
                joinList("11", "11", currentTime.toString(), "11", "1");
                GetListInQueue("11");
            }
        });

        exitBeforeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoveFromList(CurrentUser.getId());
            }
        });

        exitAfterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RemoveFromList(CurrentUser.getId());
            }
        });
    }

    private void GetListInQueue(String id) {
        Call<List<QueueList>> call = jsonPlaceHolderAPI.QueueListOrder(id.trim());
        call.enqueue(new Callback<List<QueueList>>() {
            @Override
            public void onResponse(Call<List<QueueList>> call, Response<List<QueueList>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(StationPageUI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                List<QueueList> queueList = response.body();

                for (QueueList queueListI : queueList) {
                    String content = "ID: " + queueListI.getId() + "\n" + "User ID: " + queueListI.getUserID() + "\n" + "Joined Time: " + queueListI.getJoinTime() + "\n\n\n";
                    System.out.println(content);
                    ListItem.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<QueueList>> call, Throwable t) {
                Toast.makeText(StationPageUI.this, "Error : onFailure", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void searchStation(String searchValue) {

        System.out.println(searchValue);

    }

    public void openNewActivity() {
        Intent intent = new Intent(this, AddStationUI.class);
        startActivity(intent);
    }

    public void openNewActivity2() {
        Intent intent = new Intent(this, UpdateStationUI.class);
        startActivity(intent);
    }


    public void joinList(String UserID, String QueueID, String JoinTime, String LeftTime, String Position) {
        QueueList list = new QueueList(UserID, QueueID, JoinTime, LeftTime, Position);
        System.out.println(list.getId());
        System.out.println("--------------------------------");
        Call<QueueList> call = jsonPlaceHolderAPI.joinQueueList(list);

        call.enqueue(new Callback<QueueList>() {
            @Override
            public void onResponse(Call<QueueList> call, Response<QueueList> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(StationPageUI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(StationPageUI.this, "Successfully registered", Toast.LENGTH_LONG).show();
                QueueList joinres = response.body();
                CurrentUser = joinres;
                System.out.println("joinres");
                System.out.println(joinres.getId());
                exitBeforeBtn.setVisibility(View.VISIBLE);
                exitAfterBtn.setVisibility(View.VISIBLE);
                joinQueue.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<QueueList> call, Throwable t) {
                Toast.makeText(StationPageUI.this, "Error : onFailure", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void RemoveFromList(String ID) {
        Call<Void> call = jsonPlaceHolderAPI.RemoveFromList(ID);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(StationPageUI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                exitBeforeBtn.setVisibility(View.GONE);
                exitAfterBtn.setVisibility(View.GONE);
                joinQueue.setVisibility(View.VISIBLE);
                Toast.makeText(StationPageUI.this, "Exited From the List", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(StationPageUI.this, "Error : Failed", Toast.LENGTH_LONG).show();
            }
        });
    }

}