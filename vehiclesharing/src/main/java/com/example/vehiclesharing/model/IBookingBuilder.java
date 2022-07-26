package com.example.vehiclesharing.model;

public interface IBookingBuilder {
    public int getRide_id();

    public IBookingBuilder addRide_id(int ride_id);

    public int getBooking_id();

    public IBookingBuilder addBooking_id(int booking_id);

    public int getPassenger_id();

    public IBookingBuilder addPassenger_id(int passenger_id) ;

    public String getTimestamp();

    public IBookingBuilder addTimestamp(String timestamp) ;


    public float getAmount();

    public IBookingBuilder addAmount(float amount) ;

    public int getSeats_booked();

    public IBookingBuilder addSeats_booked(int seats_booked) ;

    public int getIs_paid();

    public IBookingBuilder addIs_paid(int is_paid) ;

    public Booking build();
}
