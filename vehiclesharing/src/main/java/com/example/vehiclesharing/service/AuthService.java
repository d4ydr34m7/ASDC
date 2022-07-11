package com.example.vehiclesharing.service;

import com.example.vehiclesharing.model.User;

public interface AuthService {

    //registration
    public boolean checkIfUserExists(User user);

    //login
    public boolean validateUser(User user);

}
