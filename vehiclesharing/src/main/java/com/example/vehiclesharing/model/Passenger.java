package com.example.vehiclesharing.model;

public class Passenger {
    private int passenger_id;
    private String passenger_fname;
    private String passenger_lname;
    private String passenger_email;
    private String passenger_password;
    private String passenger_contact;

    public Passenger( String passenger_fname, String passenger_lname, String passenger_email, String passenger_password, String passenger_contact) {
        this.passenger_fname = passenger_fname;
        this.passenger_lname = passenger_lname;
        this.passenger_email = passenger_email;
        this.passenger_password = passenger_password;
        this.passenger_contact = passenger_contact;
    }

    Passenger(){}

    public Passenger(int passenger_id, String passenger_fname, String passenger_lname, String passenger_email, String passenger_password) {
        this.passenger_id = passenger_id;
        this.passenger_fname = passenger_fname;
        this.passenger_lname = passenger_lname;
        this.passenger_email = passenger_email;
        this.passenger_password = passenger_password;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getPassenger_fname() {
        return passenger_fname;
    }

    public void setPassenger_fname(String passenger_fname) {
        this.passenger_fname = passenger_fname;
    }

    public String getPassenger_lname() {
        return passenger_lname;
    }

    public void setPassenger_lname(String passenger_lname) {
        this.passenger_lname = passenger_lname;
    }

    public String getPassenger_email() {
        return passenger_email;
    }

    public void setPassenger_email(String passenger_email) {
        this.passenger_email = passenger_email;
    }

    public String getPassenger_password() {
        return passenger_password;
    }

    public void setPassenger_password(String passenger_password) {
        this.passenger_password = passenger_password;
    }
}