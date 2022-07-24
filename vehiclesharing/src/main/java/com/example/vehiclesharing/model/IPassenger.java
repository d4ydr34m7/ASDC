package com.example.vehiclesharing.model;

import java.util.List;

public interface IPassenger extends IUser{
    public boolean savePassenger(User user);
    public Passenger getPassengerByEmail(String email);
    public Passenger getPassengerById(int id);
    public List<Passenger> viewPassengerDetails();
    public boolean deletePassenger(int passenger_id);
    public boolean addCredits(String passenger_email, String columnName, float value);
    public boolean debitCreditsFromPassenger(String passenger_email, String columnName, float value);
    public boolean resetPassword(String email, String newPassword);

}
