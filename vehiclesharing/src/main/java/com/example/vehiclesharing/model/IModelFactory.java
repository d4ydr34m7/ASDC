package com.example.vehiclesharing.model;

public interface IModelFactory {
    public Booking createBookingWithBuilder(IBookingBuilder builder);
    public IBookingBuilder createBookingBuilder();
}
