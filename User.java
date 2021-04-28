package com.smoglog.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * the User class acts as both the object for the user that logs in to the smoglog app as well as
 * where the client vehicle list will be located, unique to each user
 *
 * Since April 2021
 *
 * Author: Group 8 Hao Do, Mahdi Fugfugosh, Geovany Felix
 */

public class User implements Serializable {

    /**
     * global variables for User class
     * attributes are the features and information needed for a log in or registration from the
     * worker and shop
     */
    public String email;
    public String tech;
    public String last;
    public String first;
    public String shop;
    private List<ClientVehicle> clientVehicleList;

    /**
     * 0 argument initialization on the User class
     */
    public User() {

        clientVehicleList = new ArrayList<>();

    }

    /**
     * proper initialization of the User class
     * sets the variables using the input from its initialization
     *
     * @param email
     * @param tech
     * @param last
     * @param first
     * @param shop
     */
    public User(String email, String tech, String last, String first, String shop) {

        this.email = email;
        this.tech = tech;
        this.last = last;
        this.first = first;
        this.shop = shop;
        this.clientVehicleList = new ArrayList<>();

    }

    /**
     * getter method for the client vehicle
     * @param last
     * @param vin
     * @return clientVehicle
     */
    public ClientVehicle getVehicle(String last, String vin) {

        /**
         * enters a for and if statement to ensure the requested vehicle exists in the current database
         * null is returned otherwise
         */
        for(ClientVehicle clientVehicle: clientVehicleList) {
            if(clientVehicle.getLastName().equals(last) &&
                clientVehicle.getVehicleId().equals(vin)) {
                return clientVehicle;
            }
        }
        return null;
    }

    /**
     * this method is used to add a new ClientVehicle object to the clientVehicleList
     * @param clientVehicle
     */
    public void addClientVehicle(ClientVehicle clientVehicle) {
        this.clientVehicleList.add(clientVehicle);
    }

    /**
     * getter method for the current client vehicle list
     * @return clientVehicleList
     */
    public List<ClientVehicle> getClientVehicleList() {
        return clientVehicleList;
    }

    /**
     * setter method for clientVehicleList
     * @param clientVehicleList
     */
    public void setClientVehicleList(List<ClientVehicle> clientVehicleList) {
        this.clientVehicleList = clientVehicleList;
    }

    /**
     * getter method for the user email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter method for the user email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter method for the technician
     * @return tech
     */
    public String getTech() {
        return tech;
    }

    /**
     * setter method for the technician
     * @param tech
     */
    public void setTech(String tech) {
        this.tech = tech;
    }

    /**
     * getter method for the last name of the user
     * @return last
     */
    public String getLast() {
        return last;
    }

    /**
     * setter method for the last name of the user
     * @param last
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * getter method for the first name of the user
     * @return first
     */
    public String getFirst() {
        return first;
    }

    /**
     * setter method for the first name of the user
     * @param first
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * getter method for the shop name
     * @return shop
     */
    public String getShop() {
        return shop;
    }

    /**
     * setter method for the shop name
     * @param shop
     */
    public void setShop(String shop) {
        this.shop = shop;
    }
}
