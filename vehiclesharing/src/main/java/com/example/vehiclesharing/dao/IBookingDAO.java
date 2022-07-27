package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Booking;
import java.util.List;

public interface IBookingDAO {
    public boolean saveRide(Booking booking);
    public List<Booking> getAllRidesForPassenger(int passenger_id);
}
