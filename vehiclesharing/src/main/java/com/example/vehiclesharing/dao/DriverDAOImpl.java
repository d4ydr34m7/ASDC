package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Driver;

import java.sql.*;
import java.util.List;

public class DriverDAOImpl implements DriverDAO{

    Connection connection= ConnectionFactory.getConnection();
    Statement statement;
    PreparedStatement preparedStatement;
    String query;
    ResultSet resultSet;


    @Override
    public boolean save(Object entity) {
        if(entity == null){
            return false;
        }
        try{
            Driver driver=(Driver) entity;
            query= "insert into Driver values(NULL, ? ?, ?, ?)";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, driver.getDriver_fname());
            preparedStatement.setString(2,driver.getDriver_lname());
            preparedStatement.setString(3, driver.getDriver_email());
            preparedStatement.setString(4, driver.getDriver_password());
            int i= preparedStatement.executeUpdate();
            preparedStatement.close();

            if(i==1){
                return true;
            }
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }

        return false;
    }

    @Override
    public Object getObject(String queryParam) {
        if(queryParam==null || queryParam.isEmpty()){
            return null;
        }
        try{
            query= "select * from Driver where driver_email= ?";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, queryParam);
            resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                return extractDetails(resultSet);
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

//    @Override
//    public List<Object> getObjectsList() {
//
//        return null;
//    }

    private Driver extractDetails(ResultSet resultSet) throws SQLException{
        Driver driver= new Driver();
        driver.setDriver_id(resultSet.getInt("driver_id"));
        driver.setDriver_fname(resultSet.getString("driver_fname"));
        driver.setDriver_lname(resultSet.getString("driver_lname"));
        driver.setDriver_email(resultSet.getString("driver_email"));
        return driver;
    }
}
