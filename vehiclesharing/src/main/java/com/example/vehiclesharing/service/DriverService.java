package com.example.vehiclesharing.service;

import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.User;

public interface DriverService {

    public boolean saveDriver(User user);

    public Driver getDriverByEmail(String email);
}
