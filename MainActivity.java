package com.smoglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * the main activity for the smoglog app which interacts with the login and registration
 * activities to determine if the user has access or can be granted access to enter the app
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        (findViewById(R.id.main_login)).setOnClickListener(v -> startActivity(
                new Intent(MainActivity.this, LoginActivity.class)));

        (findViewById(R.id.main_register)).setOnClickListener(v -> startActivity(
                new Intent(MainActivity.this, Registeration.class)));

    }
}