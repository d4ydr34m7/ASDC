package com.example.vehiclesharing.model;

import com.example.vehiclesharing.constants.AdminCredentials;
import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.dao.DriverDAO;
import com.example.vehiclesharing.dao.PassengerDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Validation {

    @Autowired
    PassengerDAO passengerDAO;

    @Autowired
    DriverDAO driverDAO;

    Logger logger = LoggerFactory.getLogger(Validation.class);

    public boolean checkIfUserExists(User user) {
        if(null == user)
        {
            return false;
        }
       logger.debug(user.getUserType());
        if(user.getUserType().equals(IAppConstants.PASSENGER)) {
            Passenger passenger = (Passenger) passengerDAO.getObjectByEmail(user.getEmail());
            if (null == passenger) {
                return false;
            }
        }
        else if(user.getUserType().equals(IAppConstants.DRIVER))
        {
            Driver driver = (Driver) driverDAO.getObjectByEmail(user.getEmail());
            if(null == driver) {
                return false;
            }
        }
        else if(user.getUserType().equals(IAppConstants.ADMIN) && user.getEmail().equals(AdminCredentials.ADMIN_EMAIL)){    //changes
            return true;
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
                Driver driver = (Driver) driverDAO.getObjectByEmail(user.getEmail());
                if(driver.getPassword().equals(user.getPassword()))
                {
                    return true;
                }
            }
            else if(user.getUserType().equals(IAppConstants.ADMIN)){
                if(user.getEmail().equals(AdminCredentials.ADMIN_EMAIL) && user.getPassword().equals(AdminCredentials.ADMIN_PASSWORD)){
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
