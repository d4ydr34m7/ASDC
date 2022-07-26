package com.example.vehiclesharing.model;

import com.example.vehiclesharing.Utility;
import com.example.vehiclesharing.constants.IAppMessages;
import com.example.vehiclesharing.dao.IBookingDAO;
import com.example.vehiclesharing.dao.RideCreationDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class Booking implements IBooking{

    @Autowired
    IBookingDAO bookedRidesDAO;

    @Autowired
    RideCreationDAO rideCreationDAO;

    private int booking_id;
private int passenger_id;
private String timestamp;
private float amount;
private int seats_booked;
private int is_paid;

private int ride_id;

    public int getRide_id() {
        return ride_id;
    }

    public void setRide_id(int ride_id) {
        this.ride_id = ride_id;
    }

    public Booking() {

    }

    private IRide ride;

    public Booking(int booking_id, int passenger_id, String timestamp, float amount, int seats_booked,int ride_id,IRide ride) {
        this.booking_id = booking_id;
        this.passenger_id = passenger_id;
        this.timestamp = timestamp;
        this.amount = amount;
        this.seats_booked = seats_booked;
        this.ride = ride;
        this.ride_id = ride_id;
    }

    public IRide getRide() {
        return ride;
    }

    public void setRide(IRide ride) {
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

    Logger logger = LoggerFactory.getLogger(Booking.class);


    @Override

    public boolean saveRide(Booking booking) {
        if(booking==null)
            return false;
        try {
            logger.info("Inside saveRide method of BookingServiceImpl");
            IRide ride = rideCreationDAO.getRideDetails(booking.getRide_id());
            if(ride==null)
                return false;
            if ((ride.getAvailable_seats()) - booking.getSeats_booked() >= 0) {
                booking.setAmount(ride.getTotal_cost()*booking.getSeats_booked());
                booking.setTimestamp(ride.getStart_time());
                booking.setIs_paid(0);
                logger.info("There are available seats in the vehicle, attempting to book " + booking.getSeats_booked()
                        + " seats now");
                boolean isSuccess = bookedRidesDAO.saveRide(booking);
                System.out.println(isSuccess);
                if (isSuccess) {
                    logger.info("Successfully booked seats");

                    String message = IAppMessages.RIDE_BOOKED_SUCCESSFULLY + ride.getSource() + "-->" + ride.getDestination();

                    rideCreationDAO.availableRides(ride.getSource(), ride.getDestination(),booking.getTimestamp());
                    logger.info("Successfully sent notification");
                }
                return isSuccess;
            }

            logger.info("Seats not available");
            return false;
        } catch (Exception e) {
            logger.info("Unable to book seats", e);
            return false;
        }

    }


    @Override
    public List<Booking> getUpcomingRidesForPassenger(int passenger_id) {
        try {
            List<Booking> bookingList = bookedRidesDAO.getAllRidesForPassenger(passenger_id);
            List<Booking> upcomingBookings = new ArrayList<>();
            for (Booking booking : bookingList) {
                booking.getAmount();
                String start_time = booking.getTimestamp().replace("T", " ");
                booking.setTimestamp(Utility.convertDate(booking.getTimestamp()));
                booking.setRide(rideCreationDAO.getRideDetails(booking.getBooking_id()));
                String current_time = Utility.getCurrentTime();
                Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(start_time);
                Date current = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(current_time);
                if (current.compareTo(start) < 0) {
                    upcomingBookings.add(booking);
                }
            }

            return upcomingBookings;
        } catch (Exception e) {
            return new ArrayList<Booking>();
        }
    }

    @Override
    public List<Booking> getPreviousRidesForPassenger(int passenger_id) {
        try {
            List<Booking> allRides = bookedRidesDAO.getAllRidesForPassenger(passenger_id);
            List<Booking> previousRides = new ArrayList<>();
            for (Booking ride : allRides) {
                float cost=(float) Math.ceil(ride.getAmount());
                ride.setAmount(cost);
                String start_time = ride.getTimestamp().replace("T", " ");
                String current_time = Utility.getCurrentTime();
                ride.setTimestamp(Utility.convertDate(ride.getTimestamp()));
                ride.setRide(rideCreationDAO.getRideDetails(ride.getRide_id()));
                Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(start_time);
                Date current = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(current_time);
                if (current.compareTo(end) > 0) {
                    previousRides.add(ride);
                }

            }
            return previousRides;
        } catch (Exception e) {
            return new ArrayList<Booking>();
        }

    }


}
