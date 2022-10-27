package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.models.Queue;
import com.example.myapplication.models.User;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StaffCrudUI  extends AppCompatActivity {

    Button addStaffBtn, updateStaffBtn, removeStaffBtn, searchBtn;
    EditText search;
    TextView result;
    String searchVal,id, username, email, empNo, password;
    LinearLayout result_box;
    private JasonPlaceHolderAPI jsonPlaceHolderAPI;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_crud_ui);

        addStaffBtn = findViewById(R.id.addStaffBtn);
        updateStaffBtn = findViewById(R.id.updateStaffBtn);
        removeStaffBtn = findViewById(R.id.removeStaffBtn);
        result_box = findViewById(R.id.searchResultBox);
        searchBtn = findViewById(R.id.searchStationBtn);
        search = findViewById(R.id.staffField);
        result = findViewById(R.id.result);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchVal = search.getText().toString();

                if (!searchVal.equals("")) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                    jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                    searchStaff(searchVal);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

        addStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(StaffCrudUI.this, AddStaffUI.class);
                startActivity(intent);
            }
        });

        updateStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(StaffCrudUI.this, UpdateStaffUI.class);
                startActivity(intent);
            }
        });

        removeStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public void searchStaff(String searchValue) {

        System.out.println(searchValue);
        Call<User> call = jsonPlaceHolderAPI.getUserByID(searchValue);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(StaffCrudUI.this, "Not Found", Toast.LENGTH_LONG).show();
                    return;
                }
                result_box.setVisibility(View.VISIBLE);
                updateStaffBtn.setVisibility(View.VISIBLE);
                removeStaffBtn.setVisibility(View.VISIBLE);

                User user = response.body();
                id = user.getId();
                username = user.getUserName();
                email = user.getEmail();
                empNo = user.getVehicleNo();
                password = user.getPassword();

                String content = "";
                result.setText(content);
                content += "User ID: " + user.getId() + "\n";
                content += "User name: " + user.getUserName() + "\n";
                content += "Email: " + user.getEmail() + "\n";
                content += "Emp no: " + user.getVehicleNo() + "\n";
                content += "Password: " + user.getPassword() + "\n";
//                if(queues.getFuelStatus() == true){
//                    content += "Fuel Status: Available";
//                }
//                if(queues.getFuelStatus() == false){
//                    content += "Fuel Status: Unavailable";
//                }
                result.append(content);
                Toast.makeText(StaffCrudUI.this, "Result Found", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(StaffCrudUI.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
