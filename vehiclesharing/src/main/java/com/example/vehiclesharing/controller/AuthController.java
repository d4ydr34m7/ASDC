package com.example.vehiclesharing.controller;

import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.constants.IAppMessages;
import com.example.vehiclesharing.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthController {


    @Autowired
    IDriver iDriver;

    @Autowired
    IPassenger iPassenger;

    @Autowired
    Validation validation;

    @RequestMapping("/")
    public String mainPage(){
        return IAppConstants.LOGIN_PAGE;
    }

    @PostMapping("/signup")
    public String signup(User user, Model model){
        boolean isValidUser= validation.checkIfUserExists(user);
        boolean isSuccess;
        if(isValidUser)
        {
            if(user.getUserType().equals(IAppConstants.PASSENGER))
            {
                 isSuccess=iPassenger.savePassenger(user);
            }
            else
            {
                 isSuccess=iDriver.saveDriver(user);
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
        boolean isValidUser= validation.validateUser(user);
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
            return IAppMessages.INVALID_CREDENTIALS;
    }






}
