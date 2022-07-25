package com.example.vehiclesharing.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.example.vehiclesharing.constants.IAppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vehiclesharing.constants.VehicleStringMessage;
import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.IRide;
import com.example.vehiclesharing.model.IVehicle;
import com.example.vehiclesharing.model.Ride;

@Controller
public class DriverController {
	@Autowired
	Driver driver;

	@Autowired
	IVehicle vehicle;

	@Autowired
	IRide ride;

	@PostMapping("/create-ride")
	public String createNewRide(IRide rideData, BindingResult result, Model model, HttpSession httpSession) {
		if (ride.createRide(rideData)) {
			model.addAttribute("messageStatus", VehicleStringMessage.SUCCESSFUL);
			model.addAttribute("message", VehicleStringMessage.CREATED_NEW_RIDE);
		} else {
			model.addAttribute("messageStatus", VehicleStringMessage.FAILED);
			model.addAttribute("message", VehicleStringMessage.ERROR);
		}
		int driver_id = vehicle.getVehicleDetails(rideData.getVehicle_id()).getDriver_id();
		Driver dr = driver.getDriverById(driver_id);
		model.addAttribute("driver", dr);
		model.addAttribute("listOfVehicle", vehicle.getVehicles(dr.getId()));
		List<Ride> previousridesFordriver = ride.pastRidesOfDriver(dr.getId());
		httpSession.setAttribute("previousRides", previousridesFordriver);
		List<Ride> upcomingridesFordriver = ride.upcomingRidesOfDriver(dr.getId());
		httpSession.setAttribute("upcomingRides", upcomingridesFordriver);
		return "dashboardOwner.html";

	}

	@PostMapping("/add-vehicle")
	public String addVehicle(IVehicle vehicle, Model model, HttpSession session) {
		if (vehicle.NewVehicle(vehicle)) {
			model.addAttribute("messageStatus", VehicleStringMessage.SUCCESSFUL);
			model.addAttribute("message", VehicleStringMessage.ADDED_NEW_CAR);
		} else {
			model.addAttribute("messageStatus", VehicleStringMessage.FAILED);
			model.addAttribute("message", VehicleStringMessage.ERROR);
		}
		Driver dr = driver.getDriverById(vehicle.getDriver_id());
		session.setAttribute("driver", dr);
		session.setAttribute("listOfVehicle", vehicle.getVehicles(dr.getId()));
		return "dashboardOwner.html";
	}

	@RequestMapping("/rideHistory")
	public String showRideHistory(HttpSession session, Model model) {
		return IAppConstants.DRIVER_RIDE_HISTORY ;
	}

	@RequestMapping("/createRide")
	public String showCreateRide(HttpSession session, Model model) {
		return IAppConstants.DRIVER_CREATE_RIDE ;
	}

	@RequestMapping("/vehicleDetails")
	public String showVehicleDetails(HttpSession session, Model model) {
		return IAppConstants.DRIVER_VEHICLE_DETAILS ;
	}

	@RequestMapping("/addVehicle")
	public String showAddVehicle(HttpSession session, Model model) {
		return IAppConstants.DRIVER_ADD_VEHICLE ;
	}
}
