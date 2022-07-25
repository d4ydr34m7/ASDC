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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    IDriver iDriver;

    @Autowired
    IPassenger iPassenger;

    @Autowired
    Validation validation;

    Logger logger = LoggerFactory.getLogger(AuthController.class);


    @RequestMapping("/")
    public String mainPage(){
        return IAppConstants.LOGIN_PAGE;
    }

    @RequestMapping("/signup")
    public String signupUser() {
        return IAppConstants.REGISTER_PAGE;
    }

    @RequestMapping("/login")
    public String login(HttpSession httpSession) {
        System.out.println("********************HII***************************************");
        System.out.println(httpSession.getAttribute("userType"));
        //clear here
        httpSession.invalidate();
        return IAppConstants.LOGIN_PAGE;
    }

    @PostMapping("/signup")
    public String signupUser(User user, Model model){
        //user.getEmail();
        boolean userExists= validation.checkIfUserExists(user);
        //logger.debug(user.getUserType());
        //logger.info("hi");
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


    @PostMapping("/dashboard")
    public String login(User user, Model model, HttpSession httpSession){
        boolean isValidUser= validation.validateUser(user);
        if(isValidUser)
        {
            //httpSession.setAttribute("userType", user);
            if(user.getUserType().equals(IAppConstants.PASSENGER)){
                Passenger passenger= iPassenger.getPassengerByEmail(user.getEmail());
                httpSession.setAttribute("userType",passenger);
                return IAppConstants.PASSENGER_DASHBOARD;
            }
            else if(user.getUserType().equals(IAppConstants.DRIVER)){
                Driver driver= iDriver.getDriverByEmail(user.getEmail());
                httpSession.setAttribute("userType", driver);
                return IAppConstants.DRIVER_DASHBOARD;
            }
            else if(user.getUserType().equals(IAppConstants.ADMIN)){
                List<Driver> listDriver= iDriver.viewDriverDetails();
                httpSession.setAttribute("driverDetails",listDriver);
                List<Passenger> listPassenger= iPassenger.viewPassengerDetails();
                httpSession.setAttribute("passengerDetails",listDriver);
                return IAppConstants.ADMIN_DASHBOARD;
            }
        }
            return IAppMessages.INVALID_CREDENTIALS;      //check page
    }

    @RequestMapping("/forgetPassEmail")
    public String forgotPassword() {
        return IAppConstants.PASSWORD_RESET_PAGE;
    }


    @PostMapping("/forgetPassword")
    public String forgotPassword(User user, HttpSession httpSession){
        boolean isUserExist= validation.checkIfUserExists(user);
        if(isUserExist){
            httpSession.setAttribute("userPass",user.getUserType());
            httpSession.setAttribute("userMail", user.getEmail());
            return IAppConstants.RESET_PASSWORD;
        }
        return IAppMessages.NO_USER;

    }

    @PostMapping("/login")
    public String setNewPassword(User user, HttpSession httpSession){
        if(httpSession.getAttribute("userPass").toString().equals(IAppConstants.PASSENGER)){
            iPassenger.resetPassword(httpSession.getAttribute("userMail").toString(), user.getPassword());

        }
       if(httpSession.getAttribute("userPass").toString().equals(IAppConstants.DRIVER)){
            iDriver.resetPassword(httpSession.getAttribute("userMail").toString(), user.getPassword());

        }
        return IAppConstants.LOGIN_PAGE;
    }




}
