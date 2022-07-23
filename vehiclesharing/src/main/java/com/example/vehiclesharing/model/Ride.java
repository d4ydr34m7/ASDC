package com.example.vehiclesharing.model;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import com.example.vehiclesharing.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.vehiclesharing.dao.DriverDAO;
import com.example.vehiclesharing.dao.DriverDAOImpl;
import com.example.vehiclesharing.dao.IRideCreationDAO;
import com.example.vehiclesharing.dao.IVehicleDAO;

@Component
public class Ride implements IRide {
	@Autowired
	IRideCreationDAO iride;
	@Autowired
	IVehicleDAO iVehicleDao;
	@Autowired
	DriverDAO driverDao;

	private int ride_id;
	private String source;
	private String destination;
	private String start_time;
	private String end_time;
	private int available_seats;
	private float KMs_travelled;
	private float total_cost;
	private int vehicle_id;
	private int driver_id;

	public Ride(int ride_id, String source, String destination, float KMs_travelled, int available_seats,
			String start_time, String end_time, int vehicle_id, float total_cost, int driver_id) {
		this.ride_id = ride_id;
		this.source = source;
		this.destination = destination;
		this.KMs_travelled = KMs_travelled;
		this.available_seats = available_seats;
		this.start_time = start_time;
		this.end_time = end_time;
		this.vehicle_id = vehicle_id;
		this.total_cost = total_cost;
		this.driver_id = driver_id;
	}

	public Ride() {
	}

	public int getRide_id() {
		return ride_id;
	}

	public void setRide_id(int ride_id) {
		this.ride_id = ride_id;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public float getKMs_travelled() {
		return KMs_travelled;
	}

	public void setKMs_travelled(float kMs_travelled) {
		KMs_travelled = kMs_travelled;
	}

	public float getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(float total_cost) {
		this.total_cost = total_cost;
	}

	public int getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(int vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	// Method to create a new ride
	public boolean createRide(IRide ride) {
		if (ride == null || ride.getSource() == null || ride.getDestination() == null) {
			return false;
		}
		try {
			ride.setTotal_cost(calculateTotalCost(iVehicleDao.getVehicleDetails(ride.getRide_id()), ride));
			ride.setAvailable_seats(ride.getAvailable_seats());
			boolean isSuccess = iride.createRide(ride);
			return isSuccess;
		} catch (Exception e) {
			return false;
		}
	}

	@Transactional
	public float calculateTotalCost(IVehicle vehicle, IRide ride) {
		float cost;
		float currentFuelPrice = 2;
		if (vehicle == null || ride == null) {
			cost = 0;
		} else if (ride.getAvailable_seats() == 0) {
			cost = 0;
		} else {
			cost = currentFuelPrice * 2 * ride.getKMs_travelled();
		}
		return cost;
	}

	// Method to remove a ride
	public boolean removeRide(int ride_id) {
		try {
			return iride.removeRide(ride_id);
		} catch (Exception e) {
			return false;
		}
	}

	// Method to get all ride details
	public IRide getRideDetails(int ride_id) {
		try {
			IRide ride = iride.getRideDetails(ride_id);
			return ride;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Trip> getAvailableRides(String source, String destination) {
		if (source == null || destination == null)
			return null;
		if(source.isEmpty()||destination.isEmpty())
		return null;
		try {
			List<Trip> tripList = new ArrayList<>();
			List<IRide> rideList = iride.availableRides(source, destination, dateTime.getCurrentTime().toString());
			for (IRide ride : rideList) {
				float rideCost=(float) Math.ceil(ride.getTotal_cost());
				ride.setTotal_cost(rideCost);
				IVehicle vehicle = iVehicleDao.getVehicleDetails(ride.getVehicle_id());
				int driver_id = iVehicleDao.getVehicleDetails(ride.getVehicle_id()).getDriver_id();
				Driver driver = (Driver) driverDao.getObjectById(driver_id);
				String start_time = ride.getEnd_time().replace("T", " ");
				//Float value
				ride.setStart_time(dateTime.convertDate(ride.getStart_time()));
				ride.setEnd_time(dateTime.convertDate(ride.getEnd_time()));
				int vehicleid = vehicle.getVehicle_id();
				String regn_no = vehicle.getRegn_no();
				String fname = driver.getDriver_fname();
				int driverId = vehicle.getDriver_id();
				int seats = ride.getAvailable_seats();
				Trip trip = new Trip(vehicleid, regn_no, fname, driverId, seats, ride);
				if(ride.getAvailable_seats()>0)
				{
				String current_time = dateTime.getCurrentTime();
				Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(start_time);
				Date current = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(current_time);
				if (current.compareTo(start) < 0) {
					tripList.add(trip);
				}
				}
			}
			return tripList;
		} catch (Exception e) {
			return new ArrayList<Trip>();

		}
	}

	// List of upcoming rides
	public List<Ride> upcomingRidesOfDriver(int driverId) {
		try {
			List<IRide> allRides = iride.getRidesOfDriver(driverId);
			List<Ride> upcomingRides = new ArrayList<>();
			for (IRide ride : allRides) {
				float cost = (float) Math.ceil(ride.getTotal_cost());
				ride.setTotal_cost(cost);
				String startTime = ride.getStart_time().replace("T", " ");
				String current_time = dateTime.getCurrentTime();
				ride.setStart_time(dateTime.convertDate(ride.getStart_time()));
				ride.setEnd_time(dateTime.convertDate(ride.getEnd_time()));
				Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(startTime);
				Date current = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(current_time);
				if (current.compareTo(start) < 0) {
					upcomingRides.add((Ride) ride);
				}
			}
			return upcomingRides;
		} catch (Exception e) {
			return new ArrayList<Ride>();
		}
	}

	// List of Past rides
	public List<Ride> pastRidesOfDriver(int driverId) {
		try {
			List<IRide> allRides = iride.getRidesOfDriver(driverId);
			List<Ride> pastRides = new ArrayList<>();
			for (IRide ride : allRides) {
				float cost = (float) Math.ceil(ride.getTotal_cost());
				ride.setTotal_cost(cost);
				String endTime = ride.getEnd_time().replace("T", " ");
				ride.setStart_time(dateTime.convertDate(ride.getStart_time()));
				ride.setEnd_time(dateTime.convertDate(ride.getEnd_time()));
				String current_time = dateTime.getCurrentTime();
				Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(endTime);
				Date current = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(current_time);
				if (current.compareTo(start) > 0) {
					pastRides.add((Ride) ride);
				}
			}
			return pastRides;
		} catch (Exception e) {
			return new ArrayList<Ride>();
		}
	}
}
