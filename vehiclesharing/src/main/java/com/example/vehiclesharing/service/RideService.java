package com.example.vehiclesharing.service;

import java.util.List;
import com.example.vehiclesharing.dao.*;
import com.example.vehiclesharing.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideService implements IRideService 
{
	@Autowired
	IRideCreationDAO iride;
	IVehicleDao iVehicleDao;
	
	
	public boolean createRide(Ride ride)
	{
		if(ride==null||ride.getSource()==null||ride.getDestination()==null)
			return false;
		try 
		{
			ride.setTotal_cost(calculateTotalCost(iVehicleDao.getVehicleDetails(ride.getRide_id()), ride));
			ride.setRemaining_seats(ride.getRemaining_seats());
			boolean isSuccess = iride.createRide(ride);
			return isSuccess;
		} 
		catch (Exception e) 
		{
			return false;
		}
	}

	@org.springframework.transaction.annotation.Transactional
	public float calculateTotalCost(Vehicle vehicle, Ride ride)
	{
		float cost;
		float currentFuelPrice = 2;
		if (vehicle == null || ride == null) {
			cost= 0;
		} else if (ride.getRemaining_seats() == 0) {
			cost= 0;
		} else {
			cost= currentFuelPrice * 2 * ride.getKMs_travelled();
		}
		return cost;
	}

	@org.springframework.transaction.annotation.Transactional
	public boolean removeRide(int ride_id)
	{
		try {
			return iride.removeRide(ride_id);
		} catch (Exception e) {
			return false;
		}
	}

	public Ride getRideDetails(int ride_id)
	{
		try
		{
			Ride ride = iride.getRideDetails(ride_id);
			return ride;
		}
		catch (Exception e)
		{
			return null;
		}
	}


	public List<String> getSources() 
	{
		return iride.getSources();
	}


	public List<String> getDestinations() 
	{
		return iride.getDestinations();
	}
	

}
