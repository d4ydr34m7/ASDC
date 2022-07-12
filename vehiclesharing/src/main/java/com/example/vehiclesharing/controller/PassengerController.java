package com.example.vehiclesharing.controller;

import com.example.vehiclesharing.constants.IAppMessages;
import com.example.vehiclesharing.dao.IRideCreationDAO;
import com.example.vehiclesharing.dao.RideCreationDAO;
import com.example.vehiclesharing.model.Booking;
import com.example.vehiclesharing.model.Passenger;
import com.example.vehiclesharing.model.Ride;
import com.example.vehiclesharing.service.BookingService;
import com.example.vehiclesharing.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

public class PassengerController {

    @Autowired
    PassengerService passengerService;

    @Autowired
    BookingService bookingService;

    @Autowired
    RideCreationDAO rideCreationDAO;


    @PostMapping("/searchride")
    public String searchRides(Ride ride)
    {
        String source = ride.getSource();
        String destination = ride.getDestination();
        List<Ride> availableList = rideCreationDAO.availableRides(source,destination);
        if(availableList.isEmpty()){
            return (String) IAppMessages.NO_RIDES;
        }
        else {
            return IAppMessages.RIDES_AVAILABLE;
        }
    }

    @PostMapping("/bookride")
    public String bookRide(Booking booking) {
        if(bookingService.saveRide(booking)){
            return IAppMessages.RIDE_BOOKED;
        }
        else {
            return IAppMessages.ERROR_OCCURRED;
        }
    }


    @PostMapping("/payride")
    public String payRide(Booking booking){
        String paymentStatusMessage = bookingService.payforRide(booking);
        if(paymentStatusMessage.equals(IAppMessages.PAYMENT_COMPLETE)){
            return (String) IAppMessages.SUCCESS_STATUS;
        }
        else {
            return (String) IAppMessages.FAILURE_STATUS;
        }
//        model.addAttribute("message",paymentStatusMessage);
        //Passenger passenger = passengerService.getPassengerByEmail(booking.getEmail_id());
//        session.setAttribute("passenger", passenger);
//        session.setAttribute("upcomingRides", bookingService.getUpcomingRidesForCustomer(booking.getPassenger_id()));
//        session.setAttribute("previousRides", bookingService.getPreviousRidesForCustomer(booking.getPassenger_id()));
//        return "dashboard";
    }
    @RequestMapping("/booking-history")
    public String showBookingHistory(HttpSession session, Model model){
        return "booking-history";
    }

    @PostMapping("/add-credits-customer")
    public String addCreditsOwner() {

        return null;
        //need credits module
    }




}
