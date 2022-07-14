package com.example.vehiclesharing.model;

public class Booking {

private int booking_id;
private int passenger_id;
private String timestamp;
private float amount;
private int seats_booked;
private int is_paid;
private String email_id;

    public Booking() {

    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    private Ride ride;

    public Booking(int booking_id, int passenger_id, String timestamp, float amount, int seats_booked, int is_paid, Ride ride) {
        this.booking_id = booking_id;
        this.passenger_id = passenger_id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.seats_booked = seats_booked;
        this.is_paid = is_paid;
        this.ride = ride;
    }

    public Ride getRide() {
        return ride;
    }

    public void setRide(Ride ride) {
        this.ride = ride;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public int getPassenger_id() {
        return passenger_id;
    }

    public void setPassenger_id(int passenger_id) {
        this.passenger_id = passenger_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getSeats_booked() {
        return seats_booked;
    }

    public void setSeats_booked(int seats_booked) {
        this.seats_booked = seats_booked;
    }

    public int getIs_paid() {
        return is_paid;
    }

    public void setIs_paid(int is_paid) {
        this.is_paid = is_paid;
    }
}
