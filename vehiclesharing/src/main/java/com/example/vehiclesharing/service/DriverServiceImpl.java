package com.example.vehiclesharing.service;

import com.example.vehiclesharing.dao.DriverDAO;
import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.User;
import org.springframework.beans.factory.annotation.Autowired;


public class DriverServiceImpl implements DriverService{

    @Autowired
    private DriverDAO driverDAO;

    @Override
    public boolean saveDriver(User user) {
        Driver driver=new Driver(user.getFirst_name(),user.getLast_name(),user.getEmail(),user.getPassword(),user.getContact());
        boolean isDriverSaved=driverDAO.save(driver);
        return isDriverSaved;
    }

    @Override
    public Driver getDriverByEmail(String email) {
        Driver driver=(Driver) driverDAO.getObject(email);
        return driver;
    }
}
