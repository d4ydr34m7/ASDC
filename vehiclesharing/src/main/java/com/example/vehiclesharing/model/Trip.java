package com.example.vehiclesharing.model;

import org.springframework.stereotype.Component;

@Component
public class Trip {

    private int vehicle_id;
    private String numberPlate;
    private String phone;
    private float amount;
    private int availableSeats;
    private Ride ride;

//    public Trip(Ride ride, int vehicle_id, String numberPlate, String phone, float amount, int availableSeats) {
//        this.ride = ride;
//        this.vehicle_id = vehicle_id;
//        this.numberPlate = numberPlate;
//        this.phone = phone;
//        this.amount = amount;
//        this.availableSeats = availableSeats;
//    }

    public Trip(int vehicle_id, String numberPlate, String phone, float amount, int availableSeats, Ride ride) {
        this.vehicle_id = vehicle_id;
        this.numberPlate = numberPlate;
        this.phone = phone;
        this.amount = amount;
        this.availableSeats = availableSeats;
        this.ride = ride;
    }
    public Trip()
    {

    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
