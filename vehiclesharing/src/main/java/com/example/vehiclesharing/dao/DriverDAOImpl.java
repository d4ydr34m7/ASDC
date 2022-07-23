package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Driver;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DriverDAOImpl implements DriverDAO{

    Connection connection= ConnectionFactory.getInstance().getConnection();
    Statement statement;
    PreparedStatement preparedStatement;
    String query;
    ResultSet resultSet;

    public DriverDAOImpl() throws SQLException {
    }


    @Override
    public boolean save(Object entity) {
        if(entity == null){
            return false;
        }
        try{
            Driver driver=(Driver) entity;
            query= "insert into driver values(NULL, ? ,?, ?, ?,100)";
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
    public Object getObjectByEmail(String queryParamEmail) {
        if(queryParamEmail==null || queryParamEmail.isEmpty()){
            return null;
        }
        try{
            query= "select * from driver where driver_email= ?";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, queryParamEmail);
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

    @Override
    public Object getObjectById(int id){
        if(id==0){
            return null;
        }
        try{
            query= "select * from driver where driver_id= ?";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
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

    @Override
    public boolean updateObject(String queryParam, String columnName, float value) {
        if(queryParam == null|| columnName==null){
            return false;
        }
        try{
            query= "update driver set "+columnName+"="+value+" where driver_email='"+queryParam+"'";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return false;
    }


    //admin
    @Override
    public boolean removeObject(int id) {
        if(id==0){
            return false;
        }
        try{
            statement= connection.createStatement();
            int i= statement.executeUpdate("delete from driver where driver_id=" +id);

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
    public List<Object> getObjectsList() {
        List<Object> driverList= new ArrayList<>();
        try{
            statement= connection.createStatement();
            resultSet= statement.executeQuery("select * from driver");
            while(resultSet.next()){
                Driver driver= extractDetails(resultSet);
                driverList.add(driver);

            }
            return driverList;

        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {
        if(email==null){
            return false;
        }
        try{
            query= "update driver set driver_password='"+newPassword+"' where driver_email='"+email+"'";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return false;

    }


    private Driver extractDetails(ResultSet resultSet) throws SQLException{
        Driver driver= new Driver();
        driver.setDriver_id(resultSet.getInt("driver_id"));
        driver.setDriver_fname(resultSet.getString("driver_fname"));
        driver.setDriver_lname(resultSet.getString("driver_lname"));
        driver.setDriver_email(resultSet.getString("driver_email"));
        driver.setDriver_credits(resultSet.getFloat("driver_credits"));
        return driver;
    }
}
