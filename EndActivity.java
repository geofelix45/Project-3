package com.smoglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

/**
 * the end activity for the firebase connection
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */
public class EndActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        (findViewById(R.id.logoutbtn)).setOnClickListener(v -> {

            FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signOut();
            startActivity(new Intent(EndActivity.this, LoginActivity.class));

        });

        findViewById(R.id.homebtn).setOnClickListener(v -> {

            startActivity(new Intent(EndActivity.this, Dashboard.class));

        });

    }
}