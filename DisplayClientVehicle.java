package com.smoglog;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.smoglog.models.ClientVehicle;

/**
 * display activity that shows the user the client vehicle information
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */
public class DisplayClientVehicle extends AppCompatActivity {

    /**
     * global variables for the DisplayClientVehicle class
     * attributes are the information stored within a ClientVehicle object and the
     * case handling of the edit and accept button
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
    private EditText resultF;
    private Button edit;
    private Button accept;
    private ClientVehicle vehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_client_vehicle);

        /**
         * initialize text to be displayed on activity
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
        resultF = findViewById(R.id.cn_result);

        /**
         * sets the EditText objects to false
         */
        clientIdF.setEnabled(false);
        firstNameF.setEnabled(false);
        lastNameF.setEnabled(false);
        addressF.setEnabled(false);
        cityStateF.setEnabled(false);
        phoneF.setEnabled(false);
        yearF.setEnabled(false);
        modelMakeF.setEnabled(false);
        vehicleIdF.setEnabled(false);
        jobNumberF.setEnabled(false);
        estimateCostF.setEnabled(false);
        nameOfJobF.setEnabled(false);
        workDoneF.setEnabled(false);
        resultF.setEnabled(false);

        /**
         * vehicle gets the vehicle object from which to draw the
         * information from
         */
        vehicle = (ClientVehicle) getIntent().getSerializableExtra("vehicle");
        /**
         * setter methods called after getting vehicle information
         */
        clientIdF.setText(vehicle.getClientId()+"");
        firstNameF.setText(vehicle.getFirstName());
        lastNameF.setText(vehicle.getLastName());
        addressF.setText(vehicle.getAddress());
        cityStateF.setText(vehicle.getCityState());
        phoneF.setText(vehicle.getPhone());
        yearF.setText(vehicle.getYear()+"");
        modelMakeF.setText(vehicle.getModelMake());
        vehicleIdF.setText(vehicle.getVehicleId());
        jobNumberF.setText(vehicle.getJobNumber());
        estimateCostF.setText(vehicle.getEstimateCost()+"");
        nameOfJobF.setText(vehicle.getNameOfJob());
        workDoneF.setText(vehicle.getWorkDone());

        /**
         * if statement determines whether the vehicle is finished and whether the vehicle passed or
         * failed its smog test
         */
        if(vehicle.isAccept() == null) {
            resultF.setText("Not Done");
        } else {
            boolean result = vehicle.isAccept().booleanValue();
            resultF.setText(result ? "Pass" : "Fail");
        }
        /**
         * finish activity
         */
        (findViewById(R.id.display_edit)).setOnClickListener(v -> {

            Intent intent = new Intent(DisplayClientVehicle.this, NewClient.class);
            intent.putExtra("vehicle", vehicle);
            startActivity(intent);
            finish();

        });

        /**
         * finish activity
         */
        (findViewById(R.id.display_accept)).setOnClickListener(v -> {

            Intent intent = new Intent(DisplayClientVehicle.this, ResultActivity.class);
            intent.putExtra("id", vehicle.getClientId());
            startActivity(intent);
            finish();

        });

    }


}