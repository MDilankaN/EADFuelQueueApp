package com.example.myapplication;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.api.JasonPlaceHolderAPI;

public class SearchFuelStation extends AppCompatActivity {

    Button addStaffBtn;
    EditText username, password, repassword, email, empno;
    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_fuel_station_ui);

    }

}
