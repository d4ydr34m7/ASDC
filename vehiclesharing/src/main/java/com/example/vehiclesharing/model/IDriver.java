package com.example.vehiclesharing.model;

import java.util.List;

public interface IDriver extends IUser{
    public boolean saveDriver(User user);
    public Driver getDriverByEmail(String email);
    public Driver getDriverById(int id);
    public List<Driver> viewDriverDetails();
    public boolean deleteDriver(int driver_id);
    public boolean addCredits(String driver_email,  float value);
    public boolean depositCreditsToDriver(String driver_email, float value);
    public boolean resetPassword(String email, String newPassword);
}
