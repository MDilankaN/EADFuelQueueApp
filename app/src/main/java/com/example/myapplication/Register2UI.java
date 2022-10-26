package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Register2UI extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] fuelTypes = {"Petrol", "Diesel"};
    String[] vehicleTypes = {"Car", "Van", "Lorry", "Motorbike"};
    String[] languages = {"English", "Sinhala", "Tamil"};
    Button RegisterBtn;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    TextView LoginRedirectBtn;
    String username, password, email, vehicleNo, vehicleType, fuelType, language;
    Boolean isLoading = false;

    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
            password = extras.getString("password");
            email = extras.getString("email");
            vehicleNo = extras.getString("vehicleno");
        }

        System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkk");
        System.out.println(username);
        setContentView(R.layout.activity_register_2_ui);

        RegisterBtn = findViewById(R.id.register_btn);
        LoginRedirectBtn = findViewById(R.id.login_link);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);

        System.out.println(RegisterBtn.getText().toString());

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fuelType.equals("") && !vehicleType.equals("") && !language.equals("")) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                    jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                    registerUser(username, email, password, vehicleNo, fuelType, vehicleType, language, "user");
                    RegisterBtn.setText("Loading...");
                }
            }
        });

        LoginRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register2UI.this, LoginUI.class);
                startActivity(intent);
            }
        });

        ArrayAdapter aa1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, fuelTypes);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(aa1);

        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, vehicleTypes);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(aa2);

        ArrayAdapter aa3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, languages);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(aa3);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

        fuelType = spinner1.getSelectedItem().toString();
        vehicleType = spinner2.getSelectedItem().toString();
        language = spinner3.getSelectedItem().toString();

        System.out.println(fuelType);
        System.out.println(vehicleType);
        System.out.println(language);


//        Toast.makeText(getApplicationContext(),fuelTypes[position] , Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),vehicleTypes[position] , Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),languages[position] , Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void registerUser(String username, String email, String password, String vehicleNo, String vehicleType, String fuelType, String language, String type) {
        User user = new User(username, email, password, vehicleNo, vehicleType, fuelType, language, type);

        System.out.println(user.getId());
        System.out.println("--------------------------------");
        Call<User> call = jsonPlaceHolderAPI.createUser(user);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(Register2UI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(Register2UI.this, "Successfully registered", Toast.LENGTH_LONG).show();
                User userRes = response.body();
                System.out.println("userRes");
                System.out.println(userRes.getEmail());
                System.out.println(userRes.getUserName());
                System.out.println(userRes.getFuelType());
                System.out.println(userRes.getLanguage());
                System.out.println(userRes.getVehicleNo());
                System.out.println(userRes.getVehicleType());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(Register2UI.this, "Error : onFailure", Toast.LENGTH_LONG).show();
            }
        });

    }
}