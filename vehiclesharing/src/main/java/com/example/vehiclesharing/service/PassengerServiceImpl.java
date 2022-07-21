package com.example.vehiclesharing.service;

import com.example.vehiclesharing.dao.PassengerDAO;
import com.example.vehiclesharing.model.Passenger;
import com.example.vehiclesharing.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class PassengerServiceImpl implements PassengerService{

    @Autowired
    PassengerDAO passengerDAO;
    @Override
    public boolean savePassenger(User user) {
        Passenger passenger=new Passenger(user.getFirst_name(),user.getLast_name(),user.getEmail(),user.getPassword(),user.getContact());
        boolean isPassengerSaved= passengerDAO.save(passenger);
        return isPassengerSaved;
    }

    @Override
    public Passenger getPassengerByEmail(String email) {
        Passenger passenger= (Passenger) passengerDAO.getObject(email);
        return passenger;
    }
}
