package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RideCreationDAO implements IRideCreationDAO 
{
	@Autowired
	Connection connection= ConnectionFactory.getConnection();
	Logger log = LoggerFactory.getLogger(Ride.class);
	
	//Ride creation
	public boolean createRide(Ride ride) 
	{
		if (ride == null||ride.getSource()==null||ride.getDestination()==null) 
			return false;
		
		try 
		{
			String query = ride.getSource() + "','" + ride.getDestination()
			+ "','" +ride.getStart_time() + "','" + ride.getEnd_time()
			+ "','" + ride.getRemaining_seats() + "," + ride.getKMs_travelled()
			+ "','" + ride.getTotal_cost() + ",'" + ride.getDriver_id();
			
			String create = "insert into ride values(" + null + ",'" + query + "');";
			PreparedStatement statement = connection.prepareStatement(create);
		    statement.executeUpdate();
		    log.info("Ride successfully created.");
			return true;
		}
		catch (Exception e) 
		{
			log.error("Error while saving the ride", e);
			return false;
		}
	}

	//Ride deletion
	public boolean removeRide(int ride_id) {
		try {
			String remove = "delete from ride where ride_id=" + ride_id;
			PreparedStatement statement = connection.prepareStatement(remove);
		    statement.executeUpdate();
			return true;
		} catch (Exception e) {
			log.error("Error deleting the ride", e);
			return false;
		}

	}
	
	//List of rides available based on specific source and destionation
	public List<Ride> availableRides(String source, String destination) 
	{
		List<Ride> rides = new ArrayList<Ride>();
		if (source == null || destination == null)
			return null;
		try 
		{
			String selectQuery = "select * from ride where source='" + source + "' and destination='" + destination + "'";
			PreparedStatement statement = connection.prepareStatement(selectQuery);
	        ResultSet result= statement.executeQuery();
			return rides;
		} 
		catch (Exception e) 
		{
			log.error("No available rides", e);
			return rides;
		}
	}

	//id specific ride details
	public Ride getRideDetails(int ride_id) {
		try 
		{
			String query = "select * from ride where ride_id=" + ride_id;
			PreparedStatement statement = connection.prepareStatement(query);
	        ResultSet result= statement.executeQuery();
	        if(result.next()){
                return extractDetails(result);
            }
		} 
		catch (Exception e) 
		{
			log.error("Error getting ride details", e);
			return null;
		}
		return null;
	}
	

	public List<String> getSources() {
		List<String> source = new ArrayList<String>();
		try 
		{
			String selectQuery = "select distinct source from ride";
			PreparedStatement statement = connection.prepareStatement(selectQuery);
	        ResultSet result= statement.executeQuery();
			return source;
		} 
		catch (Exception e) 
		{
			log.error("Error getting sources", e);
			return source;
		}
	}

	public List<String> getDestinations() {
		List<String> dest = new ArrayList<String>();
		try {
			String selectQuery = "select distinct destination from ride";
			PreparedStatement statement = connection.prepareStatement(selectQuery);
	        ResultSet result= statement.executeQuery();
			return dest;
		} catch (Exception e) {
			log.error("Error getting destinations", e);
			return dest;
		}
	}
	
	private Ride extractDetails(ResultSet result) throws SQLException{
        Ride ride= new Ride();
        ride.setRide_id(result.getInt("ride_id"));
        ride.setSource(result.getString("source"));
        ride.setDestination(result.getString("destination"));
        ride.setStart_time(result.getString("start_time"));
        ride.setEnd_time(result.getString("end_time"));
        ride.setRemaining_seats(result.getInt("remaining_seats"));
        ride.setKMs_travelled(result.getFloat("KMs_travelled"));
        ride.setTotal_cost(result.getFloat("total_cost"));
        ride.setDriver_id(result.getInt("driver_id"));
        return ride;
    }
}
