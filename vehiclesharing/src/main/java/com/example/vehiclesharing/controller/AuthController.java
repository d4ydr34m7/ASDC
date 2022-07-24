package com.example.vehiclesharing.controller;

import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.constants.IAppMessages;
import com.example.vehiclesharing.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

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

    @GetMapping("/signup")
    public String signupUser() {
        return IAppConstants.REGISTER_PAGE;
    }


    @PostMapping("/signup")
    public String signupUser(@ModelAttribute User user, Model model){
        boolean userExists= validation.checkIfUserExists(user);
        boolean isSuccess;
        if(userExists==false) {
            if (user.getUserType().equalsIgnoreCase(IAppConstants.PASSENGER)) {
                isSuccess = iPassenger.savePassenger(user);

            } else {
                isSuccess = iDriver.saveDriver(user);
            }

            if (isSuccess) {
                model.addAttribute("status", IAppMessages.USER_REGISTERED_SUCCESSFULLY);
                model.addAttribute("message", IAppMessages.USER_REGISTERED_MESSAGE);
                return IAppConstants.LOGIN_PAGE;
            }

            else {
                return IAppMessages.ERROR_REGISTERING_USER;
            }
        }
        else {
            model.addAttribute("status", IAppMessages.USER_EXISTS_STATUS);
            model.addAttribute("message", IAppMessages.USER_ALREADY_EXISTS);
            return IAppConstants.REGISTER_PAGE;
        }
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

//    @PostMapping("/forgotpassword")      //will need to ask user type
//    public String forgotPassword(User user){
//        boolean isUserExist= validation.checkIfUserExists(user);
//        if(isUserExist){
//            if(user.getUserType()==IAppConstants.PASSENGER){
//                iPassenger.resetPassword(user.getEmail(), user)
//                        return
//            }
//            else if(user.getUserType()==IAppConstants.DRIVER){
//                iDriver.resetPassword(user.getEmail(), )
//                return IAppConstants.DRIVER_DASHBOARD;
//            }
//        }
//        return IAppMessages.NO_USER;
//
//    }


}
