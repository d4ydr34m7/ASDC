package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class vehicleDAO implements IVehicleDAO {
	
	public vehicleDAO() throws SQLException{}
	
	Connection connection = ConnectionFactory.getInstance().getConnection();
	Logger log = LoggerFactory.getLogger(Vehicle.class);
	PreparedStatement statement;

	@Override
	public boolean NewVehicle(IVehicle vehicle) {
		if (vehicle == null || vehicle.getVehicle_model() == null)
			return false;
		try {
			String query = "insert into vehicle values(NULL, ?, ?, ?, ?, ?, ?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, vehicle.getVehicle_brand());
			statement.setString(2, vehicle.getVehicle_model());
			statement.setString(3, vehicle.getRegn_no());
			statement.setFloat(4, vehicle.getKMs_driven());
			statement.setInt(5, vehicle.getCapacity());
			statement.setInt(6, vehicle.getDriver_id());

			int i = statement.executeUpdate();
			statement.close();
			if (i == 1) {
				return true;
			}
		} catch (Exception exception) {
			log.error("Error while adding Vehicle", exception);
		}
		return false;
	}

	@Override
	public IVehicle getVehicleDetails(int vehicle_id) {
		try {
			String sql = "select * from vehicle where vehicle_id=" + vehicle_id;
			PreparedStatement statement1 = connection.prepareStatement(sql);
			ResultSet result = statement1.executeQuery();
			if (result.next()) {
				return getDetails(result);
			}
		} catch (Exception e) {
			log.error("Incorrect vehicle details", e);
			return null;

		}
		return null;
	}

	@Override
	public List<IVehicle> getVehicles(int driverId) {
		List<IVehicle> vehicles = new ArrayList<IVehicle>();
		try {
			String selectVehicleQuery = "select * from vehicle where driver_id=" + driverId;
			PreparedStatement statement2 = connection.prepareStatement(selectVehicleQuery);
			ResultSet result = statement2.executeQuery();
			while (result.next()) {
				IVehicle vehicle = getDetails(result);
				vehicles.add(vehicle);
			}
			return vehicles;
		} catch (Exception e) {
			log.error("Error getting list of vehicles", e);
		}
		return null;
	}

	@Override
	public boolean removeVehicle(int vehicleId) {
		try {
			String sql = "delete from vehicle where vehicle_id=" + vehicleId;
			PreparedStatement statement3 = connection.prepareStatement(sql);
			statement3.executeUpdate();
			log.info("Vehicle successfully deleted.");
			return true;
		} catch (Exception ex) {
			log.error("Error while deleting Vehicle", ex);
			return false;
		}
	}

	private IVehicle getDetails(ResultSet result) throws SQLException {
		IVehicle vehicle = new Vehicle();
		vehicle.setVehicle_id(result.getInt("vehicle_id"));
		vehicle.setVehicle_brand(result.getString("vehicle_brand"));
		vehicle.setVehicle_model(result.getString("vehicle_model"));
		vehicle.setRegn_no(result.getString("regn_no"));
		vehicle.setKMs_driven(result.getFloat("KMs_driven"));
		vehicle.setCapacity(result.getInt("capacity"));
		vehicle.setDriver_id(result.getInt("driver_id"));
		return vehicle;
	}
}
