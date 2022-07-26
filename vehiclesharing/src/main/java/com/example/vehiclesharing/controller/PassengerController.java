package com.example.vehiclesharing.controller;

import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.IDriver;
import com.example.vehiclesharing.model.IPassenger;
import com.example.vehiclesharing.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpSession;

@Controller
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

    @RequestMapping("/searchBookRide")
    public String showSearchBookRide(HttpSession session, Model model) {
        return IAppConstants.PASSENGER_SEARCH_BOOK_RIDE ;
    }

    @RequestMapping("/rideHistoryPassenger")
    public String showRideHistoryPassenger(HttpSession session, Model model) {
        return IAppConstants.PASSENGER_RIDE_HISTORY ;
    }

    @RequestMapping("/purchaseCredits")
    public String showPurchaseCredits(HttpSession session, Model model) {
        return IAppConstants.PASSENGER_PURCHASE_CREDITS ;
    }
}
