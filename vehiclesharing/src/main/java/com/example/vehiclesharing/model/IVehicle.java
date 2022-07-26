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
	boolean NewVehicle(IVehicle vehicle);
	IVehicle getVehicleDetails(int vehicle_id);
	List<IVehicle> getVehicles(int driverId);
	boolean removeVehicle(int vehicleId);

}