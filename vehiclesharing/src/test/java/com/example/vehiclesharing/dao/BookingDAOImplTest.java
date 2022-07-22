package com.example.vehiclesharing.dao;


import com.example.vehiclesharing.model.Booking;
import com.example.vehiclesharing.model.Ride;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
public class BookingDAOImplTest {

    @Autowired
    BookingDAO bookingDAO;

    @Test
    void testSaverideCorrect() {
        Booking booking = new Booking();
        booking.setPassenger_id(4);
        booking.setTimestamp("2022-03-31T16:42");
        booking.setAmount(1);
        booking.setSeats_booked(0);
        booking.setRide(new Ride());
        booking.setIs_paid(0);
        assertTrue(bookingDAO.saveRide(booking));
    }

    @Test
    void testSaverideIncorrect() {
        Booking booking = new Booking();
        booking.setTimestamp("");
        assertFalse(bookingDAO.saveRide(booking));
    }

    @Test
    void testSaverideException(){
        Booking booking = new Booking();
        booking.setPassenger_id(4);
        booking.setTimestamp("2022-04-01\'");
        booking.setAmount(1);
        booking.setSeats_booked(0);
        booking.setRide(new Ride());
        booking.setIs_paid(0);
        assertFalse(bookingDAO.saveRide(booking));
    }

    @Test
    void testgetAllridesforCustomerCorrect() {
        assertTrue(bookingDAO.getAllRidesForPassenger(4).size()>0);
    }
    @Test
    void testgetAllridesforCustomerWrong() {
        assertFalse(bookingDAO.getAllRidesForPassenger(-1).size()>0);
    }

    @Test
    void testupdateIsPaidCorrect() {
        assertTrue(bookingDAO.updateIsPaid(109,1));
    }

}
