package com.example.vehiclesharing.service;

import com.example.vehiclesharing.model.User;

public interface AuthService {
    public boolean checkIfUserExists(User user);

    public boolean validateUser(User user);

}
