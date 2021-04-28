package com.smoglog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smoglog.models.User;

/**
 * the activity used for registering a new user
 * connects with the database to ensure the user is new and not
 * a reoccurring one as well as adds the new user to said database
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */
public class Registeration extends AppCompatActivity {

    /**
     * global variables for the class
     * attributes are the registration info needed to add a new user to the firebase
     */
    private EditText reg_mail, reg_tech, reg_last, reg_first, reg_shop, reg_pass, reg_confirm;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        /**
         * interacts with firebase
         */
        mAuth = FirebaseAuth.getInstance();

        /**
         * initialize the attributes
         */
        reg_mail = findViewById(R.id.reg_email);
        reg_tech = findViewById(R.id.reg_tech);
        reg_last = findViewById(R.id.reg_last);
        reg_first = findViewById(R.id.reg_first);
        reg_shop = findViewById(R.id.reg_shop);
        reg_pass = findViewById(R.id.reg_pass);
        reg_confirm = findViewById(R.id.reg_confirm_pass);

        (findViewById(R.id.registerBTN)).setOnClickListener(v -> {

            /**
             * converts the attributes to a string value
             */
            String mail = reg_mail.getText().toString();
            String pass = reg_pass.getText().toString();
            String confirm = reg_confirm.getText().toString();
            String tech = reg_tech.getText().toString();
            String first = reg_first.getText().toString();
            String last = reg_last.getText().toString();
            String shop = reg_shop.getText().toString();
            /**
             * if statement entered if the passwords given during registration match one another
             */
            if(pass.equals(confirm)) {

                mAuth.createUserWithEmailAndPassword(mail, pass).addOnSuccessListener(task -> {

                    mAuth.signInWithEmailAndPassword(mail, pass).addOnSuccessListener(sign -> {

                        /**
                         * connect with database and register new user
                         */
                        String id = mAuth.getCurrentUser().getUid();
                        DatabaseReference ref = FirebaseDatabase.getInstance()
                                .getReference("smoglog");
                        ref.child("users").child(id)
                                .setValue(new User(mail, tech, last, first, shop))
                                .addOnSuccessListener(task1 ->
                                        startActivity(new Intent(Registeration.this,
                                                Dashboard.class)))
                                /**
                                 * listener for errors with connection
                                 */
                        .addOnFailureListener(e -> {
                            e.printStackTrace();
                            alert("Unable to connect with database.");
                        });

                    });

                }).addOnFailureListener(e -> {
                    e.printStackTrace();
                    alert("Email is already registered.");
                });

            } else {
                alert("Both Passwords should be same.");
            }

        });

    }

    /**
     * alert method used if any complications are encountered during the registration process
     * @param message
     */
    private void alert(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

}