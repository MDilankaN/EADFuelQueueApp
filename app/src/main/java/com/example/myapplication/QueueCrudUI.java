package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class QueueCrudUI extends AppCompatActivity {

    private Button add_queue, update_queue, remove_queue, searchBtn;;
    EditText search;
    String searchVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue_crud_ui);

        add_queue = findViewById(R.id.btn_addqueue);
        update_queue = findViewById(R.id.btn_updatequeue);
        remove_queue = findViewById(R.id.btn_removequeue);

        searchBtn = findViewById(R.id.searchStationBtn);
        search = findViewById(R.id.fuelQueueField);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchVal = search.getText().toString();

                if (!searchVal.equals("")) {
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
                openNewActivity2();
            }
        });

        remove_queue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    public void searchQueue(String searchValue) {

        System.out.println(searchValue);

    }

    public void openNewActivity(){
        Intent intent = new Intent(this, AddQueueUI.class);
        startActivity(intent);
    }

    public void openNewActivity2(){
        Intent intent = new Intent(this, UpdateQueueUI.class);
        startActivity(intent);
    }
}