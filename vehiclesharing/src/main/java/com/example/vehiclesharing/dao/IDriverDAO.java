package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Driver;

public interface IDriverDAO
{
    boolean saveDriver(Driver driver);

    Driver getDriverdetails(int driverId);
}
