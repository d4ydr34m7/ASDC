package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Passenger;
import com.example.vehiclesharing.model.User;

public interface PassengerDAO {


    boolean save(Object entity);


    public boolean updateAvaialableCredits(int customer_id, int available_credits);

    Object getObject(String queryparam);

}
