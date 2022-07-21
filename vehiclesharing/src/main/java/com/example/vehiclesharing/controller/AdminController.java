package com.example.vehiclesharing.controller;

import com.example.vehiclesharing.constants.IAppMessages;
import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.IDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.yaml.snakeyaml.events.Event;

import java.util.List;


public class AdminController {

    @Autowired
    IDriver iDriver;

    @RequestMapping("/showDriverDetails")
        public String driverDetails(Model model){
            List<Driver> driverList= iDriver.viewDriverDetails();
            if(driverList.isEmpty()){
                model.addAttribute("message", IAppMessages.NOTIFY_NO_DRIVER);

            }
            model.addAttribute("driverList", driverList);
            return "adminDashboard";
        }





}
