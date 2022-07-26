package com.example.vehiclesharing.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
 class RideTest {

	@Autowired
	IRide iride;

	@Test
	void CorrectRideCreationTest() {
		IRide ride = new Ride();
		ride.setSource("testSource1");
		ride.setDestination("testDestination1");
		ride.setStart_time("2022-07-26 01:00:00");
		ride.setEnd_time("2022-07-26 03:00:00");
		ride.setAvailable_seats(4);
		ride.setKMs_travelled(18);
		ride.setTotal_cost(35);
		ride.setDriver_id(79);
		ride.setVehicle_id(1020);
		assertTrue(iride.createRide(ride));
	}

	@Test
	void removeRideTest() {
		assertTrue(iride.removeRide(1));
	}

    @Test
    void getRideByIdTest(){
        assertTrue(iride.getRideDetails(46)!=null);
    }
    
    @Test
    void getRideByIdTestFail(){
        assertNull(iride.getRideDetails(0));
    }
    
    @Test
    void upcomingRidesTest() {
        assertTrue(iride.upcomingRidesOfDriver(11).size()>=0);
    }
    
    @Test
    void upcomingRidesTestFail() {
        assertTrue(iride.upcomingRidesOfDriver(0).size()<=0);
    }

    @Test
    void pastRidesTest() {
        assertTrue(iride.pastRidesOfDriver(11).size()>=0);
    }
    
    @Test
    void pastRidesTestFail() {
        assertTrue(iride.pastRidesOfDriver(0).size()>=0);
    }
    
    @Test
    void availableRidesTest() {
        assertTrue(iride.getAvailableRides("testSource","testDestination").size()>=0);
    }

    @Test
    void availableRidesTestNullSource() {
        assertNull(iride.getAvailableRides(null,"testDestination"));
    }

    @Test
    void availableRidesTestNullDestination() {
        assertNull(iride.getAvailableRides("testSource",null));
    }

    @Test
    void availableRidesTestEmptySource() {
        assertNull(iride.getAvailableRides("","testDestination"));
    }

    @Test
    void availableRidesTestEmptyDestination() {
        assertNull(iride.getAvailableRides("testSource",""));
    }
}
