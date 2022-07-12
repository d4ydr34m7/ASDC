package com.example.vehiclesharing.controller;

import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.constants.IAppMessages;
import com.example.vehiclesharing.model.User;
import com.example.vehiclesharing.service.AuthService;
import com.example.vehiclesharing.service.DriverService;
import com.example.vehiclesharing.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    PassengerService passengerService;

    @Autowired
    DriverService driverService;

    @RequestMapping("/")
    public String mainPage(){
        return IAppConstants.LOGIN_PAGE;
    }

    @PostMapping("/signup")
    public String signup(User user, Model model){
        boolean isValidUser= authService.checkIfUserExists(user);
        boolean isSuccess;
        if(isValidUser)
        {
            if(user.getUserType().equals(IAppConstants.PASSENGER))
            {
                 isSuccess=passengerService.savePassenger(user);
            }
            else
            {
                 isSuccess=driverService.saveDriver(user);
            }
            if(isSuccess)
            {
                return IAppConstants.LOGIN_PAGE;
            }
            else
            {
                return IAppMessages.ERROR_REGISTERING_USER;
            }
        }
        return IAppMessages.USER_ALREADY_EXISTS;
    }

    @PostMapping("/login")
    public String login(User user, Model model){
        boolean isValidUser= authService.validateUser(user);
        if(isValidUser)
        {
            if(user.getUserType()==IAppConstants.PASSENGER){
                return IAppConstants.PASSENGER_DASHBOARD;
            }
            else if(user.getUserType()==IAppConstants.DRIVER){
                return IAppConstants.DRIVER_DASHBOARD;
            }
            else{
                return IAppConstants.ADMIN_DASHBOARD;
            }
        }
        else
            return IAppMessages.INVALID_CREDENTIALS;
    }

}
