package com.example.vehiclesharing.dao;

import java.util.List;
import com.example.vehiclesharing.model.*;

public interface IRideCreationDAO {

	public boolean createRide(IRide ride);

	public boolean removeRide(int ride_id);

	public List<IRide> availableRides(String source, String destination, String timeStamp);

	public List<IRide> getRidesOfDriver(int driverId);

	public IRide getRideDetails(int ride_id);

}