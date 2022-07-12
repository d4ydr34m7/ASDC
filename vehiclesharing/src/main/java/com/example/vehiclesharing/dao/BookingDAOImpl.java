package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO{
    @Autowired
    JdbcTemplate jdbcTemplate;

    Logger logger = LoggerFactory.getLogger(BookingDAOImpl.class);

    //This method is used to insert booking object into the database
    @Override
    public boolean saveRide(Booking booking) {
        if(booking==null||booking.getTimestamp().isEmpty())
            return false;
        try {
            String subQuery1 = booking.getPassenger_id() + ",'"
                    + booking.getTimestamp() + "'," + booking.getAmount();
            String subQuery2 = booking.getSeats_booked() + ","
                    + booking.getRide() + "," + booking.getIs_paid();
            String query = subQuery1 + "," + subQuery2;
            String sql = "insert into booking values(" + null + "," + query + ");";
            jdbcTemplate.update(sql);
            logger.debug("Ride saved successfully");
            return true;
        } catch (Exception e) {
            logger.error("Error while adding Rides", e);
            return false;

        }
    }

    @Override
    public List<Booking> getAllRidesForCustomer(int passenger_id) {
        List<Booking> rides = new ArrayList<>();
        try {
            String selectBookingQuery = "select * from booking where customer_id=" + passenger_id;
            rides = jdbcTemplate.query(selectBookingQuery,
                    BeanPropertyRowMapper.newInstance(Booking.class));
            return rides;
        } catch (Exception e) {
            logger.error("Error while getting rides for customer", e);
            return rides;

        }
    }

    //This method is used to update the IsPaid field to 1 when the customer pays for the ride
    @Override
    public boolean updateIsPaid(int passenger_id, int booked_ride_id) {
        try{
            String sql = "update booking set isPaid=1 where customer_id="+passenger_id+" and booked_ride_id="+ booked_ride_id;
            jdbcTemplate.update(sql);
            return true;
        }
        catch(Exception e)
        {
            logger.error("Error updating isPaid",e);
            return false;

        }
    }

}
