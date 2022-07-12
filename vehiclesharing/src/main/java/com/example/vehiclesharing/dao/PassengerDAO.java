package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Passenger;
import com.example.vehiclesharing.model.User;

public interface PassengerDAO {

//
//    public boolean savePassenger(Passenger passenger);
//
//    public Passenger getPassengerByEmail(String

    boolean save(Object entity);

    //This method is used to insert customer object into the database
    boolean savePassenger(Passenger passenger);

    //This method is used to get a customer by his email from the customer table
    Passenger getpassengerByEmail(String email);


    //This method is used to get a customer by his id from the customer table
    Passenger getPassengerById(int id);

    public boolean updateAvaialableCredits(int customer_id, int available_credits);

    Object getObject(String queryparam);

}
