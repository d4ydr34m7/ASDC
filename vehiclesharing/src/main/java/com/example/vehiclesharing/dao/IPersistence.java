package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Driver;

import java.util.List;

public interface IPersistence {
    public boolean save(Object table);
    public Object getObject(String queryParam);
    public List<Object> getObjectsList();

}
