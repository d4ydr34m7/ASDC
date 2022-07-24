package com.example.vehiclesharing.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.vehiclesharing.dao.vehicleDAO;

@Component
public class Vehicle implements IVehicle {
	@Autowired
	vehicleDAO VehicleDAO;

	private int vehicle_id;
	private String vehicle_brand;
	private String vehicle_model;
	private String regn_no;
	private float KMs_driven;
	private int capacity;
	private int driver_id;

	public Vehicle() {
	}

	public Vehicle(int vehicle_id, String vehicle_brand, String vehicle_model, String regn_no, float KMs_driven,
			int capacity, int driver_id) {
		this.vehicle_brand = vehicle_brand;
		this.vehicle_model = vehicle_model;
		this.regn_no = regn_no;
		this.KMs_driven = KMs_driven;
		this.capacity = capacity;
		this.driver_id = driver_id;
	}

	public int getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getVehicle_brand() {
		return vehicle_brand;
	}

	public void setVehicle_brand(String vehicle_brand) {
		this.vehicle_brand = vehicle_brand;
	}

	public String getVehicle_model() {
		return vehicle_model;
	}

	public void setVehicle_model(String vehicle_model) {
		this.vehicle_model = vehicle_model;
	}

	public String getRegn_no() {
		return regn_no;
	}

	public void setRegn_no(String regn_no) {
		this.regn_no = regn_no;
	}

	public float getKMs_driven() {
		return KMs_driven;
	}

	public void setKMs_driven(float kMs_driven) {
		KMs_driven = kMs_driven;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	// Method to add a new vehicle
	public boolean NewVehicle(IVehicle vehicle) {
		if (vehicle == null || vehicle.getVehicle_model() == null) {
			return false;
		}

		return true;
	}

	// This method searches for the vehicle using the vehicle id.
	public IVehicle getVehicleDetails(int vehicle_id) {
		try {
			IVehicle vehicle = VehicleDAO.getVehicleDetails(vehicle_id);
			return vehicle;
		} catch (Exception e) {
			return null;
		}
	}

	// This method searches for the list of vehicles under a driver id.
	public List<IVehicle> getVehicles(int driverId) {
		try {
			List<IVehicle> vehicles = VehicleDAO.getVehicles(driverId);
			return vehicles;
		} catch (Exception e) {
			return new ArrayList<IVehicle>();
		}
	}

	// Method to remove a vehicle
	public boolean removeVehicle(int vehicleId) {
		try {
			return VehicleDAO.removeVehicle(vehicleId);
		} catch (Exception e) {
			return false;
		}
	}
}
