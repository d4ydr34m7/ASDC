package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Passenger;

import java.sql.*;

public class PassengerDAOImpl implements PassengerDAO{

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
            Passenger passenger=(Passenger) entity;
            query= "insert into Driver values(NULL, ? ?, ?, ?)";
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
    public boolean updateAvaialableCredits(int customer_id, int available_credits) {
        return false;
    }


    @Override
    public Object getObject(String queryParam) {
        if(queryParam==null || queryParam.isEmpty()){
            return null;
        }
        try{
            query= "select * from Passenger where passenger_email= ?";
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


    public Passenger extractDetails(ResultSet resultSet) throws SQLException{
        Passenger passenger= new Passenger();
        passenger.setPassenger_id(resultSet.getInt("passenger_id"));
        passenger.setPassenger_fname(resultSet.getString("passenger_fname"));
        passenger.setPassenger_lname(resultSet.getString("passenger_lname"));
        passenger.setPassenger_email(resultSet.getString("passenger_email"));
        return passenger;
    }

}
