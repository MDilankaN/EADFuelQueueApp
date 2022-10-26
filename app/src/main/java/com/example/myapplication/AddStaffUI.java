package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddStaffUI extends AppCompatActivity {

    Button addStaffBtn;
    EditText username, password, repassword, email, empno;
    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff_ui);

        addStaffBtn = findViewById(R.id.add_Staff_btn);

        username = findViewById(R.id.username_staff);
        email = findViewById(R.id.email_staff);
        empno = findViewById(R.id.employeeno_Staff);
        password = findViewById(R.id.password_staff);
        repassword = findViewById(R.id.confirmation_password_staff);

        addStaffBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String un = username.getText().toString();
                String pwd = username.getText().toString();
                String rpwd = username.getText().toString();
                String email = username.getText().toString();
                String empNo = username.getText().toString();

                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                if (!un.matches("") && !email.matches("") && !empNo.matches("") && !pwd.matches("") && !rpwd.matches("")) {
                    if (!pwd.matches(rpwd)) {
                        Toast.makeText(AddStaffUI.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                        return;
                    }
                    addStafftoSystem(un, email, pwd, empNo, "", "", "EN", "Staff");
                } else {
                    Toast.makeText(AddStaffUI.this, "Fields are empty", Toast.LENGTH_LONG).show();
                }

            }
        });
    }


    public void addStafftoSystem(String username, String email, String password, String vehicleNo, String vehicleType, String fuelType, String language, String type) {
        User user = new User(username, email, password, vehicleNo, vehicleType, fuelType, language, type);

        System.out.println(user.getId());
        System.out.println("--------------------------------");
        Call<User> call = jsonPlaceHolderAPI.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(AddStaffUI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                User userRes = response.body();
                System.out.println("userRes");
                System.out.println(userRes);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(AddStaffUI.this, "Error : onFailure", Toast.LENGTH_LONG).show();
            }
        });

    }


}
