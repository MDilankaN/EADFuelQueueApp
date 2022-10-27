package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.api.JasonPlaceHolderAPI;
import com.example.myapplication.models.ContactUs;
import com.example.myapplication.models.Station;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactUsUI extends AppCompatActivity {

    Button sendBtn;
    EditText name, email, subject, message;
    String nameVal, emailVal, subjectVal, messageVal ;


    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        sendBtn = findViewById(R.id.contactus_btn);
        name = findViewById(R.id.contactus_name);
        email = findViewById(R.id.contactus_mail);
        subject = findViewById(R.id.contactus_subject);
        message = findViewById(R.id.contactus_msg);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameVal = name.getText().toString();
                emailVal = email.getText().toString();
                subjectVal = subject.getText().toString();
                messageVal = message.getText().toString();

                if (!nameVal.equals("") && !emailVal.equals("") && !subjectVal.equals("")  && !messageVal.equals("")) {

                    //add data
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                    jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                    sendMsg(nameVal, emailVal, subjectVal, messageVal );
                    sendBtn.setText("Loading...");

                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }

    public void sendMsg(String name, String email, String subject, String message ) {

        ContactUs contactUs = new ContactUs(name, email, subject, message);
        Call<ContactUs> call = jsonPlaceHolderAPI.createContactUs(contactUs);

        call.enqueue(new Callback<ContactUs>() {
            @Override
            public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(ContactUsUI.this, "Error", Toast.LENGTH_LONG).show();
                    return;
                }

                Toast.makeText(ContactUsUI.this, "Data added successfully", Toast.LENGTH_LONG).show();
                ContactUs contactUsRes = response.body();
                System.out.println(contactUsRes.getEmail());
                System.out.println(contactUsRes.getName());
            }

            @Override
            public void onFailure(Call<ContactUs> call, Throwable t) {
                Toast.makeText(ContactUsUI.this, "Error : onFailure", Toast.LENGTH_LONG).show();
            }
        });
    }
}