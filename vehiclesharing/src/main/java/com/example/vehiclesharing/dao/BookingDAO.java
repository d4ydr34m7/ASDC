package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Booking;

import java.util.List;

public interface BookingDAO {

    public boolean saveRide(Booking booking);

    //This method is used to insert booking object into the database

    public List<Booking> getAllRidesForPassenger(int passenger_id);

    public boolean updateIsPaid(int passenger_id, int booked_ride_id);

}
