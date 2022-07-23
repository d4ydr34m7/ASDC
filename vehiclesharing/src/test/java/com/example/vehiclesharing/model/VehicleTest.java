package com.example.vehiclesharing.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application.properties")
public class VehicleTest {

	@Autowired
	IVehicle ivehicle;

	@Test
	public void NewVehicleTest() {
		IVehicle vehicle = new Vehicle();
		vehicle.setVehicle_brand("testBrand1");
		vehicle.setVehicle_model("testModel1");
		vehicle.setRegn_no("TEST001");
		vehicle.setKMs_driven(135);
		vehicle.setCapacity(4);
		vehicle.setDriver_id(11);
		assertTrue(ivehicle.NewVehicle(vehicle));
	}

	@Test
	public void NewVehicleTestFail() {
		assertFalse(ivehicle.NewVehicle(null));
	}

	@Test
	public void removeVehicleTest() {
		assertTrue(ivehicle.removeVehicle(1));
	}

	@Test
	public void getVehicleDetailsTest() {
		assertTrue(ivehicle.getVehicleDetails(2) != null);
	}

	@Test
	public void getVehicleDetailsTestFail() {
		assertTrue(ivehicle.getVehicleDetails(0) == null);
	}
}
