package com.example.vehiclesharing.dao;

import java.util.List;
import com.example.vehiclesharing.model.*;

public interface IRideCreationDAO {

	public boolean createRide(Ride ride);

	public boolean removeRide(int ride_id);
	
	public List<Ride> availableRides(String source, String destination);
	
	public Ride getRideDetails(int ride_id);
	
	public List<String> getSources();

	public List<String> getDestinations();

}