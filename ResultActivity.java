package com.smoglog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smoglog.models.ClientVehicle;
import com.smoglog.models.User;
import com.smoglog.models.Utilities;

/**
 * this activity is the interface for determining whether or not a client's vehicle passes or fails
 * their smog check
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */
public class ResultActivity extends AppCompatActivity {

    /**
     * global variables for the class
     * attributes are the EditText object and an integer id initialized as 0
     */
    private EditText resultF;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultF = findViewById(R.id.res_client_id);
        id = getIntent().getIntExtra("id", 1);
        resultF.setEnabled(false);
        resultF.setText(String.valueOf(id));

        (findViewById(R.id.passBTN)).setOnClickListener(v -> {update(true);});
        (findViewById(R.id.failBTN)).setOnClickListener(v -> {update(false);});

    }

    /**
     * takes in whether the smog test resulted in a pass or failure and handles it accordingly
     * @param result
     */
    private void update(boolean result) {

        /**
         * connects with the firebase
         */
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("smoglog");
        db.child("users").child(Utilities.loggedInUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                /**
                 * gets the client vehicle and changes its accept variable to pass or fail
                 */
                User user = snapshot.getValue(User.class);
                for(int i = 0; i < user.getClientVehicleList().size(); i++) {
                    ClientVehicle vehicle = user.getClientVehicleList().get(i);
                    if(vehicle.getClientId() == id) {
                        user.getClientVehicleList().get(i).setAccept(result);
                        break;
                    }
                }
                /**
                 * sends an alert when the result is updated or if there are errors
                 */
                db.child("users").child(Utilities.loggedInUserId)
                        .setValue(user)
                        .addOnSuccessListener(aVoid -> alert("Result Updated!"))
                        .addOnFailureListener(e -> {e.printStackTrace();alert("Unable to pass/fail client vehicle");});
                finish();

            }

            /**
             * entered if an error is encountered with the database
             * @param error
             */
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    /**
     * method for alerting user that the update was succesful or if an error was encountered when
     * passing or failing the client
     * @param message
     */
    private void alert(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

}