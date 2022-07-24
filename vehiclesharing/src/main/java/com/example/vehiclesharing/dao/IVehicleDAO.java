package com.example.vehiclesharing.dao;

import java.util.List;
import com.example.vehiclesharing.model.*;

public interface IVehicleDAO {

	boolean NewVehicle(IVehicle vehicle);

	IVehicle getVehicleDetails(int vehicle_id);

	List<IVehicle> getVehicles(int driverId);

	boolean removeVehicle(int vehicleId);

}