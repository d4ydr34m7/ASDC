package com.example.vehiclesharing.model;

import org.springframework.stereotype.Component;

@Component
public class User{

    public User() {
    }

    protected int id;
    protected String email;
    protected String password;
    protected String userType;
    protected String first_name;
    protected String last_name;
    protected float credits;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
