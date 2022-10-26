package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.database.DBHandler;
import com.example.myapplication.models.User;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditProfileUI extends AppCompatActivity {

    Button updateBtn;
    Button changePasswordBtn;
    Button deleteMyAccountBtn;

    EditText username, email, vehicleNo,vehicleType, fuelType;

    String usernameVal, emailVal, vehicleNoVal, vehicleTypeVal, fuelTypeval;
    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    String usernameParam;
    DBHandler dbHandler;
    User userData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_ui);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            usernameParam = extras.getString("username");
            dbHandler = new DBHandler(EditProfileUI.this);
            userData = dbHandler.getUserData(usernameParam);
            System.out.println("userData.getUserName()");
            System.out.println(userData.getUserName());
        }

        updateBtn = findViewById(R.id.update_btn);
        changePasswordBtn = findViewById(R.id.change_password_btn);
        deleteMyAccountBtn = findViewById(R.id.delete_account_btn);

        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        vehicleNo = findViewById(R.id.vehicle_no);
        vehicleType = findViewById(R.id.vehicle_type);
        fuelType = findViewById(R.id.fuel_type);

        username.setText(userData.getUserName());
        email.setText(userData.getEmail());
        vehicleNo.setText(userData.getVehicleNo());
        vehicleType.setText(userData.getVehicleType());
        fuelType.setText(userData.getFuelType());


        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);

                usernameVal = username.getText().toString();
                emailVal = email.getText().toString();
                vehicleNoVal = vehicleNo.getText().toString();
                vehicleTypeVal = vehicleType.getText().toString();
                fuelTypeval = fuelType.getText().toString();

                String userid ="";

                if (!usernameVal.equals("") && !emailVal.equals("") && !vehicleNoVal.equals("")  && !vehicleTypeVal.equals("") &&!fuelTypeval.matches("")) {
                    updateProfile(userData.getId(), usernameVal, emailVal, vehicleNoVal, vehicleTypeVal, fuelTypeval);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();

                }
            }
        });

        changePasswordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(EditProfileUI.this, ChangePasswordUI.class);
                startActivity(intent);
            }
        });
    }

    public void updateProfile( String userid ,String username, String email, String vehicleNoVal, String vehicletypeX, String fuel ) {
        String password = userData.getPassword();
        String lang = userData.getLanguage();
        String type = userData.getType();
        User user = new User(userid, username,email,password,vehicleNoVal,vehicletypeX,fuel,lang,type);
        Call<User> call = jsonPlaceHolderAPI.updateUser(userid, user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(EditProfileUI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                System.out.println("Update_____________________________");
                System.out.println(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(EditProfileUI.this, "Error : onFailure", Toast.LENGTH_LONG).show();
            }
        });

    }
}
