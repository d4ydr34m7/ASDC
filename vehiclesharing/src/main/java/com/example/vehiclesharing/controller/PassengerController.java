package com.example.vehiclesharing.controller;

import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.IDriver;
import com.example.vehiclesharing.model.IPassenger;
import com.example.vehiclesharing.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

public class PassengerController {

    @Autowired
    IDriver iDriver;

    @Autowired
    IPassenger iPassenger;

    @RequestMapping("/payment")
    public String makePayment(){
        Driver driver= iDriver.getDriverByEmail("");
        Passenger passenger= iPassenger.getPassengerByEmail("");
        iPassenger.debitCreditsFromPassenger(passenger.getEmail(),20);
        iDriver.addCredits(driver.getEmail(), 20);
        return "payment-confirm";
    }
}
