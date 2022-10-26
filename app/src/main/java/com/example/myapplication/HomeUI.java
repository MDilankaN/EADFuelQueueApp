package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeUI extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    SettingsFragment settingsFragment = new SettingsFragment();
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        Bundle bundel = new Bundle();
        if (extras != null) {
            username = extras.getString("username");
            bundel.putString("username", username);
        }
        setContentView(R.layout.activity_home_ui);


        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        homeFragment.setArguments(bundel);
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_menu_icon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, homeFragment).commit();
                        return true;
                    case R.id.settings_menu_id:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, settingsFragment).commit();
                        return true;
                }
                return false;
            }
        });

    }
}