package com.example.vehiclesharing.controller;

import com.example.vehiclesharing.constants.IAppMessages;
import com.example.vehiclesharing.dao.RideCreationDAO;
import com.example.vehiclesharing.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

public class PassengerController {


    @Autowired
    IBooking booking;

    @Autowired
    RideCreationDAO rideCreationDAO;

    @Autowired
    Passenger passenger;

    @PostMapping("/search_rides")
    public String searchRides(IRide ride, Model model)
    {
        String source = ride.getSource();
        String destination = ride.getDestination();
        List<Trip> availableRidesList = ride.getAvailableRides(source, destination);
        if(availableRidesList.isEmpty()){
            model.addAttribute("messageStatus", IAppMessages.FAILURE_STATUS);
            model.addAttribute("message",IAppMessages.NO_RIDES);
        }
        model.addAttribute("listOfRides", availableRidesList);
        return "dashboard";
    }

    @PostMapping("/book_ride")
    public String bookRide(Booking booking, HttpSession session, Model model) {
        if(booking.saveRide(booking)){
            model.addAttribute("messageStatus", IAppMessages.SUCCESS_STATUS);
            model.addAttribute("message",IAppMessages.RIDE_BOOKED);
        }
        else {
            model.addAttribute("messageStatus", IAppMessages.FAILURE_STATUS);
            model.addAttribute("message",IAppMessages.ERROR_OCCURRED);
        }
        session.setAttribute("upcomingRides", booking.getUpcomingRidesForCustomer(booking.getPassenger_id()));
        return "dashboard";
    }

    @RequestMapping(value = "/open-credit-customer")
    public String openCredit( Model model) {
        return "customer-credits";
    }



    @RequestMapping("/booking-history")
    public String showBookingHistory(HttpSession session, Model model){
        return "booking-history";
    }



    @RequestMapping("/customer-dashboard")
    public String showOwnerDashboard(HttpSession session, Model model){
        return "customer-dashboard";
    }
}




