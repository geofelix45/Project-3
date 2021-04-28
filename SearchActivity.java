package com.smoglog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smoglog.models.ClientVehicle;
import com.smoglog.models.User;
import com.smoglog.models.Utilities;

/**
 * this activity takes in a client's last name and vin number corresponding to their
 * vehicle to search the database and display the result if found
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */
public class SearchActivity extends AppCompatActivity {

    /**
     * global variables for the class
     * attributes are the last name of the client and the vin
     * number corresponding with their vehicle
     */
    private EditText lastnameF;
    private EditText vehicleVinF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        lastnameF = findViewById(R.id.search_lastname);
        vehicleVinF = findViewById(R.id.search_vin);

        (findViewById(R.id.searchBTN)).setOnClickListener(v -> {

            /**
             * last name and vin number are converted to strings
             */
            String last = lastnameF.getText().toString();
            String vehicleVin = vehicleVinF.getText().toString();

            /**
             * connection with database is made
             */
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("smoglog");
            reference.child("users").child(Utilities.loggedInUserId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    boolean found = false;
                    ClientVehicle clientVehicle = null;
                    User user = snapshot.getValue(User.class);
                    /**
                     * gets the vehicle list from the currently logged in user
                     */
                    for(int i = 0; i < user.getClientVehicleList().size(); i++) {
                        ClientVehicle vehicle = user.getClientVehicleList().get(i);
                        if(vehicle.getLastName().equals(last) &&
                                vehicle.getVehicleId().equals(vehicleVin)) {
                            found = true;
                            clientVehicle = vehicle;
                            break;
                        }
                    }
                    /**
                     * if statement entered if the client is found
                     * otherwise an alert is sent
                     */
                    if(found) {

                        Intent intent = new Intent(SearchActivity.this, DisplayClientVehicle.class);
                        intent.putExtra("vehicle", clientVehicle);
                        startActivity(intent);

                    } else {
                        alert("No any data found!");
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        });

    }

    /**
     * method used to send an alert message to the user of the app
     * @param message
     */
    private void alert(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

}