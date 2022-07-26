package com.example.vehiclesharing.model;

import java.util.List;

public interface IBooking {
    public boolean saveRide(Booking booking);

    public List<Booking> getUpcomingRidesForCustomer(int passenger_id);

    public List<Booking> getPreviousRidesForCustomer(int passenger_id);
    public String payforRide(Booking booking);
}