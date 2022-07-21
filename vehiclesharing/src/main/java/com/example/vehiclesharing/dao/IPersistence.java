package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Driver;

import java.util.List;

public interface IPersistence {
    public boolean save(Object entity);
    public Object getObjectByEmail(String queryParamEmail);    //by email
    public Object getObjectById(int id);
    public boolean updateObject(String queryParam, String columnName, float value);
    public boolean removeObject(int id);        //admin
    public List<Object> getObjectsList();      //admin and implement methods by driverdao, passengerdao

}
