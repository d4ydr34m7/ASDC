package com.example.vehiclesharing.model;

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
class BookingTest {
    @Autowired
    IBooking iBooking;
    @Test
    void testGetUpcomingRidesForCorrectCustomer() {
        assertTrue(iBooking.getUpcomingRidesForPassenger(5).size()>=0);
    }

    @Test
    void testGetUpcomingRidesForWrongCustomer() {
        assertTrue(iBooking.getUpcomingRidesForPassenger(00).size()<=0);
    }

    @Test
    void testSaveNullRide() {
        Booking booking=null;
        assertFalse(iBooking.saveRide(booking));

    }
    @Test
    void testSaveEmptyRide() {
        Booking booking=new Booking();
        assertFalse(iBooking.saveRide(booking));

    }
    @Test
    void testSaveCorrectRide() {
        IRide ride=new Ride();
        ride.setSource("testSource1");
        ride.setDestination("testDestination1");
        ride.setStart_time("2022-07-26 01:00:00");
        ride.setEnd_time("2022-07-26 03:00:00");
        ride.setAvailable_seats(4);
        ride.setKMs_travelled(18);
        ride.setTotal_cost(35);
        ride.setDriver_id(11);
        ride.setVehicle_id(1020);
        Booking booking = new Booking(7,5,"2022-03-31",55,2,6,ride);
        assertTrue(iBooking.saveRide(booking));

    }
    @Test
    void testSaveinCorrectRide() {
        Ride ride=new Ride();
        ride.setRide_id(5);
        ride.setSource("Bangalore");
        ride.setDestination("Mysore");
        ride.setKMs_travelled(55);
        ride.setAvailable_seats(4);
        ride.setStart_time("2022-03-31T16:42");
        ride.setEnd_time("2022-04-03T16:42");
        ride.setTotal_cost(1000);
        ride.setDriver_id(1111);
        ride.setVehicle_id(1010);
        Booking booking = new Booking(2,2,"2022-03-31",55,2,1,ride);

        assertFalse(iBooking.saveRide(booking));
    }

    @Test
    void testSaveRideException() {
        Ride ride=new Ride();
        ride.setRide_id(5);
        ride.setSource("Bangalore");
        ride.setDestination("Mysore");
        ride.setKMs_travelled(50);
        ride.setAvailable_seats(9);
        ride.setStart_time("01-01-2022hjdhahdkehkhwieu");
        ride.setEnd_time("01-01-2023");
        ride.setTotal_cost(1000);
        ride.setDriver_id(1111);
        Booking booking = new Booking(2,5,"2022-03-31",55,20,6,ride);
        assertFalse(iBooking.saveRide(booking));
    }

    @Test
    void testgetPreviousTripsForCorrectCustomer() {
        assertTrue(iBooking.getPreviousRidesForPassenger(4).size()>=0);
    }
    @Test
    void testgetPreviousTripsForWrongCustomer() {
        assertTrue(iBooking.getPreviousRidesForPassenger(-1).size()<=0);
    }


}