package com.example.vehiclesharing.model;

//@Component
public class Trip {

    private int vehicle_id;
    private String regn_no;
    private String fname;
    private int driverID;
    private int seats;
    private IRide ride;

    public Trip(int vehicle_id, String regn_no, String fname, int driverID, int seats, IRide ride) {
        this.vehicle_id = vehicle_id;
        this.regn_no = regn_no;
        this.fname = fname;
        this.driverID = driverID;
        this.seats = seats;
        this.ride = (IRide) ride;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public String getRegn_no() {
        return regn_no;
    }

    public void setRegn_no(String regn_no) {
        this.regn_no = regn_no;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public IRide getRide() {
        return ride;
    }

    public void setRide(IRide ride) {
        this.ride = ride;
    }
}