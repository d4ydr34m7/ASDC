package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class vehicleDAO implements IVehicleDao {
	

    @Autowired
    Connection connection= ConnectionFactory.getConnection();
    Logger log = LoggerFactory.getLogger(Vehicle.class);

	//Add new vehicle
	public boolean NewVehicle(Vehicle vehicle) {
		if(vehicle==null||vehicle.getVehicle_model()==null)
		return false;
		try {
			String query = vehicle.getVehicle_brand() + "','" + vehicle.getVehicle_model()
			+ "','" +vehicle.getRegn_no() + "','" + vehicle.getKMs_driven()
			+ "','" + vehicle.getCapacity() + ",'" + vehicle.getDriver_id();
			
			String create = "insert into vehicle values(" + null + ",'" + query + "');";
			PreparedStatement statement = connection.prepareStatement(create);
		    statement.executeUpdate();
			log.info("Vehicle successfully added.");
			return true;
		} 
		catch (Exception exception)
		{
			log.error("Error while adding Vehicle", exception);
			return false;
			
		}

	}

	//id specific vehicle detail
	public Vehicle getVehicleDetails(int vehicle_id) {
		try
		{
			String sql = "select * from vehicle where vehicle_id=" + vehicle_id;
			PreparedStatement statement = connection.prepareStatement(sql);
	        ResultSet result= statement.executeQuery();
	        if(result.next()){
                return extractDetails(result);
            }
		}
		catch(Exception e)
		{
			log.error("Incorrect vehicle details",e);
			return null;

		}
		return null;
	}

	//list of vehicles of specific driver
	public List<Vehicle> getVehicles(int driverId) {
		List<Vehicle> vehicles=new ArrayList<Vehicle>();
		try{
			String selectVehicleQuery = "select * from vehicle where driver_id=" + driverId;
			PreparedStatement statement = connection.prepareStatement(selectVehicleQuery);
	        ResultSet result= statement.executeQuery();
	       
		return vehicles;
		}
		catch(Exception e)
		{
			log.error("Error getting list of vehicles",e);
			return vehicles;

		}
	}

	//Remove a vehicle
	public boolean removeVehicle(int vehicleId) {
		try {
			String sql = "delete from vehicle where vehicle_id=" + vehicleId;
			PreparedStatement statement = connection.prepareStatement(sql);
		    statement.executeUpdate();
			log.info("Vehicle successfully deleted.");
			return true;
		} catch (Exception ex){
			log.error("Error while deleting Vehicle", ex);
			return false;
		}
	}
    private Vehicle extractDetails(ResultSet result) throws SQLException{
        Vehicle vehicle= new Vehicle();
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
