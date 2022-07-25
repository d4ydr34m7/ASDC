package com.example.vehiclesharing.model;

import java.util.List;

public interface IVehicle {

	int getVehicle_id();

	void setVehicle_id(int vehicle_id);

	String getVehicle_brand();

	void setVehicle_brand(String vehicle_brand);

	String getVehicle_model();

	void setVehicle_model(String vehicle_model);

	String getRegn_no();

	void setRegn_no(String regn_no);

	float getKMs_driven();

	void setKMs_driven(float kMs_driven);

	int getCapacity();

	void setCapacity(int capacity);

	int getDriver_id();

	void setDriver_id(int driver_id);

	// Method to add a new vehicle
	boolean NewVehicle(IVehicle vehicle);

	// This method searches for the vehicle using the vehicle id.
	IVehicle getVehicleDetails(int vehicle_id);

	// This method searches for the list of vehicles under a driver id.
	List<IVehicle> getVehicles(int driverId);

	// Method to remove a vehicle
	boolean removeVehicle(int vehicleId);

}