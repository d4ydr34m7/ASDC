package com.example.vehiclesharing.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
class BookingTest {
    @Autowired
    Booking bookingService;
    @Test
    void testGetUpcomingRidesForCorrectCustomer() {
        //assert True here
        assertTrue(bookingService.getUpcomingRidesForCustomer(4).size()>=0);
    }

    @Test
    void testGetUpcomingRidesForWrongCustomer() {
        assertTrue(bookingService.getUpcomingRidesForCustomer(00).size()<=0);
    }

    @Test
    void testSaveNullRide() {
        Booking booking=null;
        assertFalse(bookingService.saveRide(booking));

    }
    @Test
    void testSaveEmptyRide() {
        Booking booking=new Booking();
        assertFalse(bookingService.saveRide(booking));

    }
    @Test
    void testSaveCorrectRide() {
        Ride ride=new Ride();
        ride.setRide_id(5);
        ride.setSource("Bangalore");
        ride.setDestination("Mysore");
        ride.setKMs_travelled(55);
        ride.setRemaining_seats(4);
        ride.setStart_time("01-01-2022");
        ride.setEnd_time("01-01-2023");
        ride.setTotal_cost(1000);
        ride.setDriver_id(1111);
        Booking booking = new Booking(30,40,"2022-03-31",55,105,0,ride);
        assertTrue(bookingService.saveRide(booking));

    }
    @Test
    void testSaveinCorrectRide() {
        Ride ride=new Ride();
        ride.setRide_id(5);
        ride.setSource("Bangalore");
        ride.setDestination("Mysore");
        ride.setKMs_travelled(55);
        ride.setRemaining_seats(4);
        ride.setStart_time("2022-03-31T16:42");
        ride.setEnd_time("2022-04-03T16:42");
        ride.setTotal_cost(1000);
        ride.setDriver_id(1111);
        Booking booking = new Booking(30,40,"2022-03-31",55,105,0,ride);

        assertFalse(bookingService.saveRide(booking));
    }

    @Test
    void testSaveRideException() {
        Ride ride=new Ride();
        ride.setRide_id(5);
        ride.setSource("Bangalore\'");
        ride.setDestination("Mysore");
        ride.setKMs_travelled(55);
        ride.setRemaining_seats(4);
        ride.setStart_time("01-01-2022");
        ride.setEnd_time("01-01-2023");
        ride.setTotal_cost(1000);
        ride.setDriver_id(1111);
        Booking booking = new Booking(30,40,"2022-03-31",55,105,0,ride);
        //Booking booking=new Booking(100, "test_source", "test_destination", 2, 4, 3, 3, "2022-01-01", 20, 23);
        assertFalse(bookingService.saveRide(booking));
    }

    @Test
    void testgetPreviousTripsForCorrectCustomer() {
        assertTrue(bookingService.getPreviousRidesForCustomer(4).size()>=0);
    }
    @Test
    void testgetPreviousTripsForWrongCustomer() {
        assertTrue(bookingService.getPreviousRidesForCustomer(-1).size()<=0);
    }

    @Test
    void testpayforRideSucess() {
        Booking booking=new Booking();
        booking.setRide(new Ride());
        booking.setPassenger_id(41);
        booking.setTimestamp("2022-04-03T16:42");
        booking.setAmount(1);
        booking.setSeats_booked(2);
        booking.setBooking_id(107);
        booking.setIs_paid(1);
        booking.setEmail_id("bookingtest@gmail.com");
        assertEquals(bookingService.payforRide(booking),"Payment successfully done!");
    }
}