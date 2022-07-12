package com.example.vehiclesharing.service;

import com.example.vehiclesharing.model.Passenger;
import com.example.vehiclesharing.model.User;
import org.springframework.transaction.annotation.Transactional;

public interface PassengerService {
    public boolean savePassenger(User user);

    public Passenger getPassengerByEmail(String email);
    @Transactional
    public Passenger getPassengerById(int Id);



}
