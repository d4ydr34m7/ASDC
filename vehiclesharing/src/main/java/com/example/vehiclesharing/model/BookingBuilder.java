package com.example.vehiclesharing.model;

public class BookingBuilder implements IBookingBuilder{
    private int booking_id;
    private int passenger_id;
    private String timestamp;
    private float amount;
    private int seats_booked;
    private int ride_id;
    private IRide ride;


    @Override
    public int getRide_id() {
        return ride_id;
    }

    @Override
    public IBookingBuilder addRide_id(int ride_id) {
        this.ride_id = ride_id;
        return this;
    }

    @Override
    public int getBooking_id() {
        return booking_id;
    }

    @Override
    public IBookingBuilder addBooking_id(int booking_id) {
        this.booking_id = booking_id;
        return this;
    }

    @Override
    public int getPassenger_id() {
        return passenger_id;
    }

    @Override
    public IBookingBuilder addPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
        return this;
    }

    @Override
    public String getTimestamp() {
        return timestamp;
    }

    @Override
    public IBookingBuilder addTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public float getAmount() {
        return amount;
    }

    @Override
    public IBookingBuilder addAmount(float amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public int getSeats_booked() {
        return seats_booked;
    }

    @Override
    public IBookingBuilder addSeats_booked(int seats_booked) {
        this.seats_booked = seats_booked;
        return this;
    }

    @Override
    public Booking build() {

        return null;
    }

    @Override
    public IRide getRide() {
        return ride;
    }
}
