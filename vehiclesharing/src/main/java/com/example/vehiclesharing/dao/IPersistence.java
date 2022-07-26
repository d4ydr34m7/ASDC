package com.example.vehiclesharing.dao;

import java.util.List;

public interface IPersistence {
    public boolean save(Object entity);
    public Object getObjectByEmail(String queryParamEmail);
    public Object getObjectById(int id);
    public boolean updateObject(String queryParam, String columnName, float value);
    public boolean removeObject(int id);
    public List<Object> getObjectsList();
    public boolean resetPassword(String email, String newPassword);

}
