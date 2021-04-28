package com.smoglog;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * the code corresponding to the accept activity when taking the id
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */
public class AcceptActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accept);

        int id = getIntent().getIntExtra("id", 0);


    }

}