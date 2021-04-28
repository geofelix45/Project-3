package com.smoglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

/**
 * the main dashboard for the android interface
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */
public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        (findViewById(R.id.searchVehBTN)).setOnClickListener(a -> {startActivity(new Intent(Dashboard.this, SearchActivity.class));});
        (findViewById(R.id.enterNewVehicleClient)).setOnClickListener(a -> {startActivity(new Intent(Dashboard.this, NewClient.class));});

    }
}