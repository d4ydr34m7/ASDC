package com.example.vehiclesharing.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.vehiclesharing.constants.*;
import com.example.vehiclesharing.model.*;

public class VehicleController {
	@Autowired
	IVehicle vehicle;

	   @PostMapping("/add-new-vehicle")
	    public String addNewVehicle(Vehicle vehicles, Model model, HttpSession session) {
	        if(vehicle.NewVehicle(vehicles)){
	            model.addAttribute("messageStatus", VehicleStringMessage.SUCCESSFUL);
	            model.addAttribute("message",VehicleStringMessage.ADDED_NEW_CAR);
	        }
	        else {
	            model.addAttribute("messageStatus", VehicleStringMessage.FAILED);
	            model.addAttribute("message",VehicleStringMessage.ERROR);
	        }
	        session.setAttribute("listOfVehicle", vehicle.getVehicles(vehicles.getDriver_id()));
	        return "cars";
	    }

}
