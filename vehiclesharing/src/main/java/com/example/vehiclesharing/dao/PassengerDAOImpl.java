package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.Passenger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PassengerDAOImpl implements PassengerDAO{
    Connection connection= ConnectionFactory.getInstance().getConnection();
    Statement statement;
    PreparedStatement preparedStatement;
    String query;
    ResultSet resultSet;

    public PassengerDAOImpl() throws SQLException {
    }


    @Override
    public boolean save(Object entity) {
        if(entity == null){
            return false;
        }
        try{
            Passenger passenger=(Passenger) entity;
            query= "insert into passenger values(NULL, ? ,?, ?, ?, 100)";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1, passenger.getPassenger_fname());
            preparedStatement.setString(2,passenger.getPassenger_lname());
            preparedStatement.setString(3, passenger.getPassenger_email());
            preparedStatement.setString(4, passenger.getPassenger_password());
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
            query= "select * from passenger where passenger_email= ?";
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
            query= "select * from passenger where passenger_id= ?";
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
            query= "update passenger set "+columnName+"="+value+"where passenger_email='"+queryParam+"'";
            preparedStatement= connection.prepareStatement(query);
            int i= preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;

        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }

        return false;    }


    //admin
    @Override
    public boolean removeObject(int id) {
        if(id==0){
            return false;
        }
        try{
            statement= connection.createStatement();
            int i= statement.executeUpdate("delete from passenger where passenger_id=" +id);

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
            resultSet= statement.executeQuery("select * from passenger");
            while(resultSet.next()){
                Passenger passenger= extractDetails(resultSet);
                driverList.add(passenger);

            }
            return driverList;

        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }
//
//    @Override
//    public List<Object> getObjectsList() {
//        return null;
//    }
//    @Override
//    public boolean savePassenger(Passenger passenger) {
//        return false;
//    }
//
//    @Override
//    public Passenger getPassengerByEmail(String email) {
//        return null;
//    }

    private Passenger extractDetails(ResultSet resultSet) throws SQLException{
        Passenger passenger= new Passenger();
        passenger.setPassenger_id(resultSet.getInt("passenger_id"));
        passenger.setPassenger_fname(resultSet.getString("passenger_fname"));
        passenger.setPassenger_lname(resultSet.getString("passenger_lname"));
        passenger.setPassenger_email(resultSet.getString("passenger_email"));
        return passenger;
    }

}
