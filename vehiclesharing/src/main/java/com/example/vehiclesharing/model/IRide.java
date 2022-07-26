package com.example.vehiclesharing.model;

import java.util.List;

public interface IRide {
	int getRide_id();
	void setRide_id(int ride_id);
	String getSource();
	void setSource(String source);
	String getDestination();
	void setDestination(String destination);
	String getStart_time();
	void setStart_time(String start_time);
	String getEnd_time();
	void setEnd_time(String end_time);
	int getAvailable_seats();
	void setAvailable_seats(int available_seats);
	float getKMs_travelled();
	void setKMs_travelled(float kMs_travelled);
	float getTotal_cost();
	void setTotal_cost(float total_cost);
	int getVehicle_id();
	void setVehicle_id(int vehicle_id);
	int getDriver_id();
	void setDriver_id(int driver_id);
	boolean createRide(IRide ride);
	float calculateTotalCost(IVehicle vehicle, IRide ride);
	boolean removeRide(int ride_id);
	IRide getRideDetails(int ride_id);
	List<Ride> upcomingRidesOfDriver(int driverId);
	List<Ride> pastRidesOfDriver(int driverId);
	public List<Trip> getAvailableRides(String source, String destination);

}