package com.example.vehiclesharing.service;

import com.example.vehiclesharing.model.Booking;
import com.example.vehiclesharing.model.Passenger;

import java.util.List;

public interface BookingService {


    boolean saveRide(Booking booking);


    public List<Booking> getUpcomingRidesForCustomer(int customer_id);

    public List<Booking> getPreviousRidesForCustomer(int customer_id);
    public String payforRide(Booking booking);




}
