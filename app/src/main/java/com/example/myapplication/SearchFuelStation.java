package com.example.myapplication;
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
import com.example.myapplication.models.Station;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchFuelStation extends AppCompatActivity {

    Button searchBtn;
    EditText search;
    String searchVal;
    LinearLayout result_box;
    TextView result;
    String  id, stationName, address, telephone, openTime, closeTime, imageURL;
    int noOfPumps;

    private JasonPlaceHolderAPI jsonPlaceHolderAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_fuel_station_ui);

        searchBtn = findViewById(R.id.searchStationBtn);
        search = findViewById(R.id.fuelStationField);
        result_box = findViewById(R.id.searchResultBox);
        result = findViewById(R.id.stationDetails);

        result_box.setVisibility(View.GONE);


        result_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(id);
                Intent intent = new Intent(SearchFuelStation.this, QueueListByStationUI.class);
                intent.putExtra("id", id);
                intent.putExtra("stationName", stationName);
                intent.putExtra("address", address);
                intent.putExtra("telephone", telephone);
                intent.putExtra("openTime", openTime);
                intent.putExtra("closeTime", closeTime);
                intent.putExtra("imageURL", imageURL);
                intent.putExtra("noOfPumps", noOfPumps);
                startActivity(intent);
            }
        });

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchVal = search.getText().toString();

                if (!searchVal.equals("")) {
                    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://ead-backend-fuel-queue.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build();
                    jsonPlaceHolderAPI = retrofit.create(JasonPlaceHolderAPI.class);
                    searchQueue(searchVal);
                } else {
                    Snackbar.make(v, "Fields are Empty", Snackbar.LENGTH_SHORT).show();
                }

            }
        });

    }

    public void searchQueue(String searchValue) {

        System.out.println(searchValue);
        Call<List<Station>> call = jsonPlaceHolderAPI.getStationByName(searchValue.trim());
        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(SearchFuelStation.this, "Not Found", Toast.LENGTH_LONG).show();
                    System.out.println(response);

                    return;
                }

                result_box.setVisibility(View.VISIBLE);

                List<Station> station = response.body();

                for (Station st : station){
                    id = st.getId();
                    stationName = st.getStationName();
                    address = st.getAddress();
                    telephone = st.getTelephone();
                    openTime = st.getOpenTime();
                    closeTime = st.getCloseTime();
                    imageURL = st.getImageURL();
                    noOfPumps = st.getNoOfPumps();

                    String content = "";
                    result.setText(content);
                    content += "Station Name: " + st.getStationName() + "\n";
                    content += "Address: " + st.getAddress() + "\n";
                    content += "Opens between (" + st.getOpenTime() + " - " + st.getCloseTime() + ")";

                    result.append(content);
                }

                Toast.makeText(SearchFuelStation.this, "Result Found", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                System.out.println(t);
                Toast.makeText(SearchFuelStation.this, "Error: Failed", Toast.LENGTH_LONG).show();
            }

        });

    }

}