package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ContactUs extends AppCompatActivity {

    Button sendBtn;
    EditText name, email, subject, message;
    String nameVal, emailVal, subjectVal, messageVal ;

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
                    sendMsg(nameVal, emailVal, subjectVal, messageVal);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();

                }
            }
        });

    }

    public void sendMsg(String username, String email, String subject, String msg ) {

        System.out.println(username);
        System.out.println(email);
        System.out.println(subject);
        System.out.println(msg);

    }
}