package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Driver;

import java.sql.ResultSet;
import java.util.List;

public interface IPersistence {
    public boolean save(Object entity);
    public Object getObject(String queryParam);
    //public List<Object> getObjectsList();

}
