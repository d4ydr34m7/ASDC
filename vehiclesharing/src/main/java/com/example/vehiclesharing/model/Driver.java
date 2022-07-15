package com.example.vehiclesharing.model;

public class Driver {
    private int driver_id;
    private String driver_fname;
    private String driver_lname;
    private String driver_email;
    private String driver_password;
    //private String driver_contact;
//
//    public String getDriver_contact() {
//        return driver_contact;
//    }
//
//    public void setDriver_contact(String driver_contact) {
//        this.driver_contact = driver_contact;
//    }

    public Driver(){}

    public Driver( String driver_fname, String driver_lname, String driver_email, String driver_password) {
        this.driver_fname = driver_fname;
        this.driver_lname = driver_lname;
        this.driver_email = driver_email;
        this.driver_password = driver_password;
        //this.driver_contact = driver_contact;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_fname() {
        return driver_fname;
    }

    public void setDriver_fname(String driver_fname) {
        this.driver_fname = driver_fname;
    }

    public String getDriver_lname() {
        return driver_lname;
    }

    public void setDriver_lname(String driver_lname) {
        this.driver_lname = driver_lname;
    }

    public String getDriver_email() {
        return driver_email;
    }

    public void setDriver_email(String driver_email) {
        this.driver_email = driver_email;
    }

    public String getDriver_password() {
        return driver_password;
    }

    public void setDriver_password(String driver_password) {
        this.driver_password = driver_password;
    }
}
