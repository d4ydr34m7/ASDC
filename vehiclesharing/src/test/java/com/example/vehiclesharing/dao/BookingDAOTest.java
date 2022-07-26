package com.example.vehiclesharing.dao;


import com.example.vehiclesharing.model.Booking;
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
class BookingDAOTest {

    @Autowired
    IBookingDAO bookingDAO;

    @Test
    void testSaverideCorrect() {
        Booking booking = new Booking();
        booking.setPassenger_id(5);
        booking.setBooking_id(2);
        booking.setTimestamp("2022-03-31T16:42");
        booking.setAmount(1);
        booking.setSeats_booked(4);
        booking.setRide_id(6);
        assertTrue(bookingDAO.saveRide(booking));
    }

    @Test
    void testSaverideIncorrect() {
        Booking booking = new Booking();
        booking.setTimestamp("");
        assertFalse(bookingDAO.saveRide(booking));
    }

    @Test
    void testSaverideEmptyTimestamp(){
        Booking booking = new Booking();
        booking.setPassenger_id(4);
        booking.setBooking_id(2);
        booking.setTimestamp("");
        booking.setAmount(1);
        booking.setSeats_booked(0);
        booking.setRide_id(6);
        assertFalse(bookingDAO.saveRide(booking));
    }

    @Test
    void testgetAllridesforCustomerCorrect() {
        assertTrue(bookingDAO.getAllRidesForPassenger(5).size()>0);
    }
    @Test
    void testgetAllridesforCustomerWrong() {
        assertFalse(bookingDAO.getAllRidesForPassenger(0).size()>0);
    }

    @Test
    void testSaverideMissingParameter()
    {
        Booking booking = new Booking();
        booking.setPassenger_id(4);
        booking.setBooking_id(2);
        booking.setTimestamp("2022-04-01");
        booking.setSeats_booked(0);
        booking.setRide_id(6);
        assertFalse(bookingDAO.saveRide(booking));
    }



}
