package com.example.vehiclesharing.model;

import com.example.vehiclesharing.constants.AdminCredentials;
import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.dao.DriverDAO;
import com.example.vehiclesharing.dao.PassengerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    @Autowired
    PassengerDAO passengerDAO;

    @Autowired
    DriverDAO driverDAO;

    public boolean checkIfUserExists(User user) {
        if(user.getUserType().equals(IAppConstants.PASSENGER)) {
            Passenger passenger = (Passenger) passengerDAO.getObjectByEmail(user.getEmail());
            if (null == passenger) {
                return false;
            }
        }
        else
        {
            Driver driver=(Driver) driverDAO.getObjectByEmail(user.getEmail());
            if(null==driver) {
                return false;
            }
        }
        return true;
    }


    public boolean validateUser(User user) {
        if(checkIfUserExists(user)) {
            if (user.getUserType().equals(IAppConstants.PASSENGER)) {
                Passenger passenger = (Passenger) passengerDAO.getObjectByEmail(user.getEmail());
                if(passenger.getPassword().equals(user.getPassword())){
                    return true;
                }
            }
            else if(user.getUserType().equals(IAppConstants.DRIVER))
            {
                Driver driver=(Driver) driverDAO.getObjectByEmail(user.getEmail());
                if(driver.getPassword().equals(user.getPassword()))
                {
                    return true;
                }
            }
            else if(user.getUserType().equals((IAppConstants.ADMIN))){
                if(user.getEmail()== AdminCredentials.ADMIN_EMAIL && user.getPassword()==AdminCredentials.ADMIN_PASSWORD){
                    return true;
                }
            }
            else{
                return false;
            }

        }
        return false;

    }
}
