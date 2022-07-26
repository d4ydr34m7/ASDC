package com.example.vehiclesharing.model;

import java.util.List;

public interface IBooking {
    public boolean saveRide(Booking booking);

    public List<Booking> getUpcomingRidesForPassenger(int passenger_id);

    public List<Booking> getPreviousRidesForPassenger(int passenger_id);
}