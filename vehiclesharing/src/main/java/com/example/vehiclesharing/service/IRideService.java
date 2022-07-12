package com.example.vehiclesharing.service;

import com.example.vehiclesharing.dao.*;
import com.example.vehiclesharing.model.*;

import java.util.List;

import com.example.*;

public interface IRideService {

	public boolean createRide(Ride ride);

	public float calculateTotalCost(Vehicle vehicle, Ride ride);

	public boolean removeRide(int ride_id);
	
	public Ride getRideDetails(int ride_id);
	
	public List<String> getSources();

	public List<String> getDestinations();

}