package com.example.vehiclesharing.controller;

import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.IDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    IDriver iDriver;

    @RequestMapping("/showDrivers")
    public String showListOfDrivers(HttpSession httpSession, Model model){
        List<Driver> listDriver = iDriver.viewDriverDetails();
        model.addAttribute("driverDetails", listDriver);
        return IAppConstants.LIST_OF_DRIVERS;
    }

    @RequestMapping("/showPassengers")
    public String showListOfPassengers(HttpSession httpSession, Model model){
        return IAppConstants.LIST_OF_PASSENGERS;
    }

    @RequestMapping("/showRides")
    public String showListOfRides(HttpSession httpSession, Model model){
        return IAppConstants.LIST_OF_RIDES;
    }

    @RequestMapping("/showVehicles")
    public String showListOfCars(HttpSession httpSession, Model model){
        return IAppConstants.LIST_OF_VEHICLES;
    }

}
