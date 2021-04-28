package com.smoglog.models;

import java.io.Serializable;

/**
 * ClientVehicle is the invoice object that holds the client information, vehicle information,
 * any work done on the vehicle, and whether or not said vehicle was accepted
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */

public class ClientVehicle implements Serializable {

    /**
     * global variables for the ClientVehicle class
     * attributes that make up the object
     */
    private int clientId;
    private String firstName;
    private String lastName;
    private String address;
    private String cityState;
    private String phone;
    private int year;
    private String modelMake;
    private String vehicleId;
    private String jobNumber;
    private double estimateCost;
    private String nameOfJob;
    private String workDone;
    private Boolean accept;

    /**
     * 0 argument initialization of the ClientVehicle class
     */
    public ClientVehicle() {

    }

    /**
     * proper initialization of the clientvehicle class
     * sets the variables using the input from its initialization
     *
     * @param clientId
     * @param firstName
     * @param lastName
     * @param address
     * @param cityState
     * @param phone
     * @param year
     * @param modelMake
     * @param vehicleId
     * @param jobNumber
     * @param estimateCost
     * @param nameOfJob
     * @param workDone
     * @param accept
     */
    public ClientVehicle(int clientId, String firstName, String lastName, String address,
                         String cityState, String phone, int year, String modelMake,
                         String vehicleId, String jobNumber, double estimateCost,
                         String nameOfJob, String workDone, Boolean accept) {

        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.cityState = cityState;
        this.phone = phone;
        this.year = year;
        this.modelMake = modelMake;
        this.vehicleId = vehicleId;
        this.jobNumber = jobNumber;
        this.estimateCost = estimateCost;
        this.nameOfJob = nameOfJob;
        this.workDone = workDone;
        this.accept = accept;

    }

    /**
     * getter method for the client id
     * @return clientid
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * setter method for the client id
     * @param clientId
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * getter method for the first name
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter method for the first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * getter method for the last name
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter method for the last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * getter method for the address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * setter method for the address
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * getter method for the city and state
     * @return cityState
     */
    public String getCityState() {
        return cityState;
    }

    /**
     * setter method for the city and state
     * @param cityState
     */
    public void setCityState(String cityState) {
        this.cityState = cityState;
    }

    /**
     * getter method for the phone
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * setter method for the phone
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * getter method for the year of the vehicle
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * setter method for the year of the vehicle
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * getter method for the model and make of the vehicle
     * @return modelMake
     */
    public String getModelMake() {
        return modelMake;
    }

    /**
     * setter method for the model and make of the vehicle
     * @param modelMake
     */
    public void setModelMake(String modelMake) {
        this.modelMake = modelMake;
    }

    /**
     * getter method for the vehicle id
     * @return vehicleID
     */
    public String getVehicleId() {
        return vehicleId;
    }

    /**
     * setter method for the vehicle ID
     * @param vehicleId
     */
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    /**
     * getter method for the job number
     * @return jobNumber
     */
    public String getJobNumber() {
        return jobNumber;
    }

    /**
     * setter method for the job number
     * @param jobNumber
     */
    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    /**
     * getter method for the estimate cost of the job
     * @return estimateCost
     */
    public double getEstimateCost() {
        return estimateCost;
    }

    /**
     * setter method for the estimate cost of the job
     * @param estimateCost
     */
    public void setEstimateCost(double estimateCost) {
        this.estimateCost = estimateCost;
    }

    /**
     * getter method for the type of job to be done on the vehicle
     * @return nameOfJob
     */
    public String getNameOfJob() {
        return nameOfJob;
    }

    /**
     * setter method for the type of job to be done on the vehicle
     * @param nameOfJob
     */
    public void setNameOfJob(String nameOfJob) {
        this.nameOfJob = nameOfJob;
    }

    /**
     * getter method for the work done on the vehicle
     * @return workDone
     */
    public String getWorkDone() {
        return workDone;
    }

    /**
     * setter method for the work done on the vehicle
     * @param workDone
     */
    public void setWorkDone(String workDone) {
        this.workDone = workDone;
    }

    /**
     * getter method for whether or not the vehicle is accepted (smog)
     * @return accept
     */
    public Boolean isAccept() {
        return accept;
    }

    /**
     * setter method for whether or not the vehicle is accepted (smog)
     * @param accept
     */
    public void setAccept(Boolean accept) {
        this.accept = accept;
    }

}
