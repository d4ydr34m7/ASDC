package com.example.vehiclesharing.model;

import java.util.List;

import com.example.vehiclesharing.controller.DriverController;
import com.example.vehiclesharing.model.User;

public interface IDriver extends IUser{

    public boolean saveDriver(IUser user);
    public Driver getDriverByEmail(String email);
    public Driver getDriverById(int id);
    public List<Driver> viewDriverDetails();
    public boolean deleteDriver(int driver_id);
    public boolean addCredits(String driver_email, String columnName, float value);
    public boolean depositCreditsToDriver(String driver_email, String columnName, float value);
    public Driver convertObject();
    public boolean resetPassword(String email, String newPassword);
}
