package com.example.vehiclesharing.dao;

import java.util.List;

public interface PassengerDAO {


    boolean save(Object entity);


    //public boolean updateAvaialableCredits(int customer_id, int available_credits);



    Object getObjectByEmail(String queryParamEmail);

    Object getObjectById(int id);

    boolean updateObject(String passenger_email, String passengerCredits, float passenger_credits);

    //admin
    boolean removeObject(int id);

    List<Object> getObjectsList();

    boolean resetPassword(String email, String newPassword);
}
