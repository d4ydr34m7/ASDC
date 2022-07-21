//package com.example.vehiclesharing.service;
//
//import com.example.vehiclesharing.constants.AdminCredentials;
//import com.example.vehiclesharing.constants.IAppConstants;
//import com.example.vehiclesharing.dao.DriverDAO;
//import com.example.vehiclesharing.dao.PassengerDAO;
//import com.example.vehiclesharing.model.Driver;
//import com.example.vehiclesharing.model.Passenger;
//import com.example.vehiclesharing.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthServiceImpl implements AuthService {
//
//    @Autowired
//    private PassengerDAO passengerDAO;
//
//    @Autowired
//    private DriverDAO driverDAO;
//
//    @Override
//    public boolean checkIfUserExists(User user) {
//        if(user.getUserType().equals(IAppConstants.PASSENGER)) {
//            Passenger passenger = (Passenger) passengerDAO.getObject(user.getEmail());
//            if (null == passenger) {
//                return false;
//            }
//        }
//        else
//        {
//            Driver driver=(Driver) driverDAO.getObject(user.getEmail());
//            if(null==driver) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    @Override
//    public boolean validateUser(User user) {
//        if(checkIfUserExists(user)) {
//            if (user.getUserType().equals(IAppConstants.PASSENGER)) {
//                Passenger passenger = (Passenger) passengerDAO.getObject(user.getEmail());
//                if(passenger.getPassenger_password().equals(user.getPassword())){
//                    return true;
//                }
//            }
//            else if(user.getUserType().equals(IAppConstants.DRIVER))
//            {
//                Driver driver=(Driver) driverDAO.getObject(user.getEmail());
//                if(driver.getDriver_password().equals(user.getPassword()))
//                {
//                    return true;
//                }
//            }
//            else if(user.getUserType().equals((IAppConstants.ADMIN))){
//                if(user.getEmail()== AdminCredentials.ADMIN_EMAIL && user.getPassword()==AdminCredentials.ADMIN_PASSWORD){
//                    return true;
//                }
//            }
//            else{
//                return false;
//            }
//
//        }
//        return false;
//
//    }
//}
