package com.example.vehiclesharing.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.example.vehiclesharing.model.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application.properties")
public class RideDAOTest {

	@Autowired
	IRideCreationDAO iride;

	@Test
	public void NullRideTest() {
		IRide ride = null;
		assertFalse(iride.createRide(ride));
	}

	@Test
	public void EmptyRideTest() {
		IRide ride = new Ride();
		assertFalse(iride.createRide(ride));
	}

	@Test
	public void CorrectRideCreationTest() {
		IRide ride = new Ride();
		ride.setSource("testSource");
		ride.setDestination("testDestination");
		ride.setStart_time("2022-07-26 12:00:00");
		ride.setEnd_time("2022-07-26 03:00:00");
		ride.setAvailable_seats(4);
		ride.setKMs_travelled(15);
		ride.setTotal_cost(25);
		ride.setDriver_id(10);
		ride.setVehicle_id(1010);
		assertTrue(iride.createRide(ride));
	}

	@Test
	public void removeRideTest() {
		assertTrue(iride.removeRide(15));
	}

	@Test
	public void getRideDetailsTest() {
		assertTrue(iride.getRideDetails(2) != null);
	}

	@Test
	public void WrongRideDetailsTest() {
		assertNull(iride.getRideDetails(00));
	}
	
	@Test
    public void availableRidesTest() {
        assertTrue(iride.availableRides("testSource","testDestination",Date.valueOf("2022-07-23").toString()).size()>=0);
    }

    @Test
    public void availableRidesTestNullSource() {
        assertNull(iride.availableRides(null,"testDestination",Date.valueOf("2022-07-23").toString()));
    }

    @Test
    public void availableRidesTestNullDestination() {
        assertNull(iride.availableRides("testSource",null,Date.valueOf("2022-07-23").toString()));
    }

    @Test
    public void availableRidesTestEmptySource() {
        assertTrue(iride.availableRides("","testDestination",Date.valueOf("2022-07-23").toString()).size()<=0);
    }

    @Test
    public void availableRidesTestEmptyDestination() {
        assertTrue(iride.availableRides("testSource","",Date.valueOf("2022-07-23").toString()).size()<=0);
    }

	@Test
    public void getRidesOfCorrectDriverTest(){
        assertTrue(iride.getRidesOfDriver(10).size()>0);
    }
    
	@Test
    public void getRidesOfIncorrectDriverTest(){
        assertTrue(iride.getRidesOfDriver(999).size()<=0);
    }
}
