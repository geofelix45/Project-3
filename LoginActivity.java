package com.smoglog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.smoglog.models.Utilities;

/**
 * the activity used for the log in interface
 * connects with firebase and finds if a user is registered for a log in
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */
public class LoginActivity extends AppCompatActivity {

    // Attributes..
    private EditText login_mail, login_pass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /**
         * connects and interacts with the data stored on firebase
         */
        mAuth = FirebaseAuth.getInstance();

        login_mail = findViewById(R.id.login_email);
        login_pass = findViewById(R.id.login_password);

        (findViewById(R.id.loginBTN)).setOnClickListener(v -> {

            String mail = login_mail.getText().toString();
            String pass = login_pass.getText().toString();
            /**
             * if statement determines whether or not an email and password is entered
             */
            if(mail.isEmpty() || pass.isEmpty()) {
                alert("No any field should be empty!");
            } else {

                /**
                 * determines whether or not the email and password entered are valid per
                 * the data stored on the firebase
                 */
                mAuth.signInWithEmailAndPassword(mail, pass)

                        .addOnSuccessListener(authResult -> {
                                Utilities.loggedInUserId = mAuth.getCurrentUser().getUid();
                                startActivity(
                                    new Intent(LoginActivity.this, Dashboard.class)
                                );
                        })
                        .addOnFailureListener(e -> {alert("Email or Password is wrong!");});

            }

        });

    }

    /**
     * alert to give if an error is encountered when entering the email and password
     * @param message
     */
    private void alert(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }
}