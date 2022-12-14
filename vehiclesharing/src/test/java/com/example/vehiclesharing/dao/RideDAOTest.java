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
@TestPropertySource("/application-test.properties")
 class RideDAOTest {

	@Autowired
	IRideCreationDAO iride;

	@Test
	void NullRideTest() {
		IRide ride = null;
		assertFalse(iride.createRide(ride));
	}

	@Test
	 void EmptyRideTest() {
		IRide ride = new Ride();
		assertFalse(iride.createRide(ride));
	}

	@Test
	 void CorrectRideCreationTest() {
		IRide ride = new Ride();
		ride.setSource("testSource");
		ride.setDestination("testDestination");
		ride.setStart_time("2022-07-26 12:00:00");
		ride.setEnd_time("2022-07-26 03:00:00");
		ride.setAvailable_seats(4);
		ride.setKMs_travelled(15);
		ride.setTotal_cost(25);
		ride.setDriver_id(79);
		ride.setVehicle_id(1010);
		assertTrue(iride.createRide(ride));
	}

	@Test
	void removeRideTest() {
		assertTrue(iride.removeRide(15));
	}

	@Test
	void getRideDetailsTest() {
		assertTrue(iride.getRideDetails(46) != null);
	}

	@Test
	void WrongRideDetailsTest() {
		assertNull(iride.getRideDetails(00));
	}
	
	@Test
    void availableRidesTest() {
        assertTrue(iride.availableRides("testSource","testDestination",Date.valueOf("2022-07-23").toString()).size()>=0);
    }

    @Test
    void availableRidesTestNullSource() {
        assertNull(iride.availableRides(null,"testDestination",Date.valueOf("2022-07-23").toString()));
    }

    @Test
    void availableRidesTestNullDestination() {
        assertNull(iride.availableRides("testSource",null,Date.valueOf("2022-07-23").toString()));
    }

    @Test
    void availableRidesTestEmptySource() {
        assertTrue(iride.availableRides("","testDestination",Date.valueOf("2022-07-23").toString()).size()<=0);
    }

    @Test
    void availableRidesTestEmptyDestination() {
        assertTrue(iride.availableRides("testSource","",Date.valueOf("2022-07-23").toString()).size()<=0);
    }

	@Test
    void getRidesOfCorrectDriverTest(){
        assertTrue(iride.getRidesOfDriver(79).size()>0);
    }
    
	@Test
    void getRidesOfIncorrectDriverTest(){
        assertTrue(iride.getRidesOfDriver(999).size()<=0);
    }
}
