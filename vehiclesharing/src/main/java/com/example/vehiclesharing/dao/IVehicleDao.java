package com.example.vehiclesharing.dao;

import java.util.List;
import com.example.vehiclesharing.model.*;

public interface IVehicleDao {

	boolean NewVehicle(Vehicle vehicle);

	Vehicle getVehicleDetails(int vehicle_id);

	List<Vehicle> getVehicles(int driverId);

	boolean removeVehicle(int vehicleId);

}