package com.smoglog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.smoglog.models.ClientVehicle;
import com.smoglog.models.User;
import com.smoglog.models.Utilities;

/**
 * the activity used to enter in a new client in the smog log app for a new
 * job to be done on the corresponding client's vehicle
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */
public class NewClient extends AppCompatActivity {

    /**
     * global variables for the NewClient class]
     * attributes is the client vehicle information to be assigned
     */
    private EditText clientIdF;
    private EditText firstNameF;
    private EditText lastNameF;
    private EditText addressF;
    private EditText cityStateF;
    private EditText phoneF;
    private EditText yearF;
    private EditText modelMakeF;
    private EditText vehicleIdF;
    private EditText jobNumberF;
    private EditText estimateCostF;
    private EditText nameOfJobF;
    private EditText workDoneF;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);

        /**
         * initialize the client vehicle information
         */
        clientIdF = findViewById(R.id.cn_clientid);
        firstNameF = findViewById(R.id.cn_first);
        lastNameF = findViewById(R.id.cn_last);
        addressF = findViewById(R.id.cn_street);
        cityStateF = findViewById(R.id.cn_citystate);
        phoneF = findViewById(R.id.cn_phone);
        yearF = findViewById(R.id.cn_year);
        modelMakeF = findViewById(R.id.cn_model);
        vehicleIdF = findViewById(R.id.cn_vehiclevin);
        jobNumberF = findViewById(R.id.cn_jobnumber);
        estimateCostF = findViewById(R.id.cn_estimatecost);
        nameOfJobF = findViewById(R.id.cn_job);
        workDoneF = findViewById(R.id.cn_work);
        register = findViewById(R.id.submit);

        /**
         * sets the information for the global variables if the vehicle object is not null
         * which indicates the client exists
         */
        ClientVehicle vehicleObj = (ClientVehicle) getIntent().getSerializableExtra("vehicle");
        if(vehicleObj != null) {
            clientIdF.setText(vehicleObj.getClientId()+"");
            firstNameF.setText(vehicleObj.getFirstName());
            lastNameF.setText(vehicleObj.getLastName());
            addressF.setText(vehicleObj.getAddress());
            cityStateF.setText(vehicleObj.getCityState());
            phoneF.setText(vehicleObj.getPhone());
            yearF.setText(vehicleObj.getYear()+"");
            modelMakeF.setText(vehicleObj.getModelMake());
            vehicleIdF.setText(vehicleObj.getVehicleId());
            jobNumberF.setText(vehicleObj.getJobNumber());
            estimateCostF.setText(vehicleObj.getEstimateCost()+"");
            nameOfJobF.setText(vehicleObj.getNameOfJob());
            workDoneF.setText(vehicleObj.getWorkDone());
        }

        register.setOnClickListener(v -> {

            try {

                /**
                 * converts the input from the user into strings
                 */
                int id = Integer.parseInt(clientIdF.getText().toString());
                String first = firstNameF.getText().toString();
                String last = lastNameF.getText().toString();
                String address = addressF.getText().toString();
                String state = cityStateF.getText().toString();
                String phone = phoneF.getText().toString();
                int year = Integer.parseInt(yearF.getText().toString());
                String model = modelMakeF.getText().toString();
                String vin = vehicleIdF.getText().toString();
                String jobNumber = jobNumberF.getText().toString();
                double estimate = Double.parseDouble(estimateCostF.getText().toString());
                String job = nameOfJobF.getText().toString();
                String work = workDoneF.getText().toString();

                /**
                 * initialize firebase database
                 */
                DatabaseReference db = FirebaseDatabase.getInstance().getReference("smoglog");
                db.child("users").child(Utilities.loggedInUserId).get().addOnCompleteListener(task -> {

                    if(task.isSuccessful()){

                        boolean found = false;
                        /**
                         * new ClientVehicle created
                         */
                        ClientVehicle clientVehicle = new ClientVehicle(id, first, last, address, state,
                                phone, year, model, vin, jobNumber, estimate, job, work, null);
                        User user = task.getResult().getValue(User.class);
                        for(int i = 0; i < user.getClientVehicleList().size(); i++) {
                            ClientVehicle vehicle = user.getClientVehicleList().get(i);
                            if(vehicle.getClientId() == id) {
                                found = true;
                                user.getClientVehicleList().set(i, clientVehicle);
                                break;
                            }
                        }
                        /**
                         * add and register new client, send an alert otherwise
                         */
                        if(!found) {
                            user.addClientVehicle(clientVehicle);
                        }
                        db.child("users").child(Utilities.loggedInUserId)
                                .setValue(user)
                                .addOnSuccessListener(aVoid -> alert("Client Vehicle Registered/Updated Successfully!"))
                                .addOnFailureListener(e -> {e.printStackTrace();alert("Unable to register client vehicle");});

                    } else {
                        alert("Unable to register the client vehicle.");
                    }

                });

            } catch(Exception e){
                alert("Data should be valid.");
            }

        });

    }

    /**
     * alert method if there are complications with registering a new client
     * @param message
     */
    private void alert(String message) {

        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }

}