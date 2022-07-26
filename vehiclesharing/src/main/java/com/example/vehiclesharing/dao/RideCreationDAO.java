package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.*;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class RideCreationDAO implements IRideCreationDAO {
	public RideCreationDAO() throws SQLException {
	}

	Connection connection = ConnectionFactory.getInstance().getConnection();
	Logger log = LoggerFactory.getLogger(Ride.class);
	PreparedStatement statement;

	public boolean createRide(IRide ride) {
		if (ride == null || ride.getSource() == null || ride.getDestination() == null) {
			return false;
		}
		try {
			String query = "insert into ride values(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, ride.getSource());
			statement.setString(2, ride.getDestination());
			statement.setString(3, ride.getStart_time());
			statement.setString(4, ride.getEnd_time());
			statement.setInt(5, ride.getAvailable_seats());
			statement.setFloat(6, ride.getKMs_travelled());
			statement.setFloat(7, ride.getTotal_cost());
			statement.setInt(8, ride.getDriver_id());
			statement.setInt(9, ride.getVehicle_id());
			int i = statement.executeUpdate();
			statement.close();

			if (i == 1) {
				return true;
			}
		} catch (Exception e) {
			log.error("Error while saving the ride", e);
		}
		return false;
	}

	public boolean removeRide(int ride_id) {
		try {
			String remove = "delete from ride where ride_id=" + ride_id;
			PreparedStatement statement1 = connection.prepareStatement(remove);
			statement1.executeUpdate();
			return true;
		} catch (Exception e) {
			log.error("Error deleting the ride", e);
			return false;
		}
	}

	public List<IRide> availableRides(String source, String destination, String timeStamp) {
		List<IRide> rides = new ArrayList<IRide>();
		if (source == null || destination == null) {
			return null;
		}
		try {
			String selectQuery = "select * from ride where source='" + source + "' and destination='" + destination
					+ "'";
			PreparedStatement statement2 = connection.prepareStatement(selectQuery);
			ResultSet result = statement2.executeQuery();
			while (result.next()) {
				IRide ride = getDetails(result);
				rides.add(ride);
			}
			return rides;
		} catch (Exception e) {
			log.error("No available rides", e);
		}
		return null;
	}

	public IRide getRideDetails(int ride_id) {
		try {
			String query = "select * from ride where ride_id=" + ride_id;
			PreparedStatement statement3 = connection.prepareStatement(query);
			ResultSet result = statement3.executeQuery();
			if (result.next()) {
				return (IRide) getDetails(result);
			}
		} catch (Exception e) {
			log.error("Error getting ride details", e);
			return null;
		}
		return null;
	}

	public List<IRide> getRidesOfDriver(int driverId) {
		List<IRide> ride = new ArrayList<>();
		try {
			String selectquery = "select * from ride where driver_id=" + driverId;
			PreparedStatement statement4 = connection.prepareStatement(selectquery);
			ResultSet result = statement4.executeQuery();
			while (result.next()) {
				IRide rides = getDetails(result);
				ride.add(rides);
			}
			return ride;
		} catch (Exception e) {
			log.error("Error getting rides for Driver", e);
		}
		return null;
	}

	private IRide getDetails(ResultSet result) throws SQLException {
		IRide ride = new Ride();
		ride.setRide_id(result.getInt("ride_id"));
		ride.setSource(result.getString("source"));
		ride.setDestination(result.getString("destination"));
		ride.setStart_time(result.getString("start_time"));
		ride.setEnd_time(result.getString("end_time"));
		ride.setAvailable_seats(result.getInt("available_seats"));
		ride.setKMs_travelled(result.getFloat("KMs_travelled"));
		ride.setTotal_cost(result.getFloat("total_cost"));
		ride.setDriver_id(result.getInt("driver_id"));
		ride.setVehicle_id(result.getInt("vehicle_id"));
		return ride;
	}
}
