package com.example.vehiclesharing.dao;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
public class VehicleDAOTest {

	@Autowired
	IVehicleDAO ivehicle;
	
	@Test
	void NewVehicleTest() {
		IVehicle vehicle = new Vehicle();
		vehicle.setVehicle_brand("testBrand");
		vehicle.setVehicle_model("testModel");
		vehicle.setRegn_no("TEST01");
		vehicle.setKMs_driven(125);
		vehicle.setCapacity(4);
		vehicle.setDriver_id(10);
		assertTrue(ivehicle.NewVehicle(vehicle));
	}
	
	@Test
	void removeVehicleTest() {
		assertTrue(ivehicle.removeVehicle(1));
	}
	
	@Test
	void getVehicleDetailsTest() {
		assertTrue(ivehicle.getVehicleDetails(2) != null);
	}
	
	@Test
	void getVehicleDetailsTestFail() {
		assertNull(ivehicle.getVehicleDetails(0));
	}
	
	@Test
	void getVehiclesOfCorrectDriverTest(){
		assertTrue(ivehicle.getVehicles(10).size()>0);
  }
	
	@Test
	void getVehiclesOfIncorrectDriverTest(){
		assertTrue(ivehicle.getVehicles(0).size()<=0);
  }
}
