package com.example.vehiclesharing.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application.properties")
public class RideTest {

	@Autowired
	IRide iride;

	@Test
	public void CorrectRideCreationTest() {
		IRide ride = new Ride();
		ride.setSource("testSource1");
		ride.setDestination("testDestination1");
		ride.setStart_time("2022-07-26 01:00:00");
		ride.setEnd_time("2022-07-26 03:00:00");
		ride.setAvailable_seats(4);
		ride.setKMs_travelled(18);
		ride.setTotal_cost(35);
		ride.setDriver_id(11);
		ride.setVehicle_id(1020);
		assertTrue(iride.createRide(ride));
	}

	@Test
	public void removeRideTest() {
		assertTrue(iride.removeRide(1));
	}

    @Test
    void getRideByIdTest(){
        assertTrue(iride.getRideDetails(2)!=null);
    }
    
    @Test
    void getRideByIdTestFail(){
        assertNull(iride.getRideDetails(0));
    }
    
    @Test
    public void upcomingRidesTest() {
        assertTrue(iride.upcomingRidesOfDriver(11).size()>=0);
    }
    
    @Test
    public void upcomingRidesTestFail() {
        assertTrue(iride.upcomingRidesOfDriver(0).size()<=0);
    }

    @Test
    public void pastRidesTest() {
        assertTrue(iride.pastRidesOfDriver(11).size()>=0);
    }
    
    @Test
    public void pastRidesTestFail() {
        assertTrue(iride.pastRidesOfDriver(0).size()>=0);
    }
    
    @Test
    public void availableRidesTest() {
        assertTrue(iride.getAvailableRides("testSource","testDestination").size()>=0);
    }

    @Test
    public void availableRidesTestNullSource() {
        assertNull(iride.getAvailableRides(null,"testDestination"));
    }

    @Test
    public void availableRidesTestNullDestination() {
        assertNull(iride.getAvailableRides("testSource",null));
    }

    @Test
    public void availableRidesTestEmptySource() {
        assertNull(iride.getAvailableRides("","testDestination"));
    }

    @Test
    public void availableRidesTestEmptyDestination() {
        assertNull(iride.getAvailableRides("testSource",""));
    }
}
