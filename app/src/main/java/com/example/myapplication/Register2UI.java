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


public class Register2UI extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] fuelTypes = {"Petrol", "Diesel"};
    String[] vehicleTypes = {"Car", "Van", "Lorry", "Motorbike"};
    String[] languages = {"English", "Sinhala", "Tamil"};
    Button RegisterBtn;
    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    TextView LoginRedirectBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_2_ui);

        RegisterBtn = findViewById(R.id.register_btn);
        LoginRedirectBtn = findViewById(R.id.login_link);
        spinner1 =  findViewById(R.id.spinner1);
        spinner2 =  findViewById(R.id.spinner2);
        spinner3 =  findViewById(R.id.spinner3);

        spinner1.setOnItemSelectedListener(this);
        spinner2.setOnItemSelectedListener(this);
        spinner3.setOnItemSelectedListener(this);

        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Register2UI.this, HomeUI.class);
                startActivity(intent);
            }
        });

        LoginRedirectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(Register2UI.this, LoginUI.class);
                startActivity(intent);
            }
        });

        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,fuelTypes);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(aa1);

        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,vehicleTypes);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(aa2);

        ArrayAdapter aa3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,languages);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(aa3);
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
//        Toast.makeText(getApplicationContext(),fuelTypes[position] , Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),vehicleTypes[position] , Toast.LENGTH_LONG).show();
//        Toast.makeText(getApplicationContext(),languages[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }
}