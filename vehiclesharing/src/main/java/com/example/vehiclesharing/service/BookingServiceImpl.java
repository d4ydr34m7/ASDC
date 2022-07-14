package com.example.vehiclesharing.service;

import com.example.vehiclesharing.Utility;
import com.example.vehiclesharing.constants.IAppMessages;
import com.example.vehiclesharing.dao.BookingDAO;
import com.example.vehiclesharing.dao.IDriverDAO;
import com.example.vehiclesharing.dao.PassengerDAO;
import com.example.vehiclesharing.dao.RideCreationDAO;
import com.example.vehiclesharing.model.Booking;
import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.Passenger;
import com.example.vehiclesharing.model.Ride;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

    public class BookingServiceImpl implements BookingService{

        @Autowired
        BookingDAO bookedRidesDAO;

        @Autowired
        RideCreationDAO rideCreationDAO;

        @Autowired
        PassengerDAO passengerDAO;

        @Autowired
        com.example.vehiclesharing.dao.IDriverDAO IDriverDAO;

        @Autowired
        //NotificationService notificationService;

        Logger logger = LoggerFactory.getLogger(com.example.vehiclesharing.service.BookingServiceImpl.class);

        //This method contains logic for saving ride before saving it to the database using booking DAO
        @Override
        @Transactional
        public boolean saveRide(Booking booking) {
            if(booking==null)
                return false;
            try {
                logger.info("Inside saveRide method of BookingServiceImpl");
                Ride ride = rideCreationDAO.getRideDetails(booking.getRide().getRide_id());
                if(ride==null)
                    return false;
                if ((ride.getRemaining_seats()) - booking.getSeats_booked() >= 0) {
                    booking.setAmount(ride.getTotal_cost()*booking.getSeats_booked());
                    booking.setTimestamp(ride.getStart_time());
                    booking.setIs_paid(0);
                    logger.info("There are available seats in the vehicle, attempting to book " + booking.getSeats_booked()
                            + " seats now");
                    boolean isSuccess = bookedRidesDAO.saveRide(booking);
                    if (isSuccess) {
                        logger.info("Successfully booked seats");
                        String email = (String) passengerDAO.getObject(booking.getEmail_id());
                        String message = IAppMessages.RIDE_BOOKED_SUCCESSFULLY + ride.getSource() + "-->" + ride.getDestination();
                        //notificationService.sendEmail(message, ServiceStringMessages.RIDE_BOOKED,
                        //email);
                        rideCreationDAO.availableRides(ride.getSource(), ride.getDestination());
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

        //This method gets list of upcoming rides from the database using booking DAO, converts date and cost of the ride to a specific format and return it to the controller
        @Override
        @Transactional
        public List<Booking> getUpcomingRidesForCustomer(int customer_id) {
            try {
                List<Booking> bookingList = bookedRidesDAO.getAllRidesForCustomer(customer_id);
                List<Booking> upcomingBookings = new ArrayList<>();
                for (Booking booking : bookingList) {
                    float cost = 0;
                    cost = (float) Math.ceil(booking.getAmount());
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

        //This method gets list of previous rides from the database using booking DAO, converts date and cost of the ride to a specific format and return it to the controller
        @Override
        @Transactional
        public List<Booking> getPreviousRidesForCustomer(int customer_id) {
            try {
                List<Booking> allRides = bookedRidesDAO.getAllRidesForCustomer(customer_id);
                List<Booking> previousRides = new ArrayList<>();
                for (Booking ride : allRides) {
                    float cost=(float) Math.ceil(ride.getAmount());
                    ride.setAmount(cost);
                    String start_time = ride.getTimestamp().replace("T", " ");
                    String current_time = Utility.getCurrentTime();
                    ride.setTimestamp(Utility.convertDate(ride.getTimestamp()));
                    ride.setRide(rideCreationDAO.getRideDetails(ride.getBooking_id()));
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

        //This method updates IsPaid to 1 from booking table and transfers credits from Customer credits to vehicleOwner credits when the customer pays for the ride
        @Override
        @Transactional
        public String payforRide(Booking booking) {
            if(booking==null)
                return IAppMessages.ERROR_OCCURRED;
            Passenger passenger= (Passenger) passengerDAO.getObject(booking.getPassenger_id());
            int amount_credits=(int) Math.ceil(booking.getAmount());
            Ride ride=rideCreationDAO.getRideDetails(booking.getBooking_id());
            if(ride==null||passenger==null)
                return IAppMessages.ERROR_OCCURRED;
            if(passenger.getAvailable_credits()>= amount_credits)
            {
                bookedRidesDAO.updateIsPaid(passenger.getPassenger_id(), booking.getBooking_id());
                int driver_id = rideCreationDAO.getRideDetails(booking.getBooking_id()).getDriver_id();
                Driver driver=IDriverDAO.getDriverdetails(driver_id);
                int increment_credits=passenger.getAvailable_credits()+amount_credits;
                int decrease_credits=passenger.getAvailable_credits()-amount_credits;
                IDriverDAO.getDriverdetails(driver_id);
                passengerDAO.updateAvaialableCredits(passenger.getPassenger_id(), decrease_credits);
                return IAppMessages.PAYMENT_COMPLETE;
            }
            else
            {
                return IAppMessages.PLEASE_BUY_CREDITS;
            }
        }


    }

}
