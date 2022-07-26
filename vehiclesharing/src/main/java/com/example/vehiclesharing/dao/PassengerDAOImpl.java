package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Passenger;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PassengerDAOImpl implements PassengerDAO{
    Connection connection = ConnectionFactory.getInstance().getConnection();
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
            preparedStatement.setString(1, passenger.getFirst_name());
            preparedStatement.setString(2,passenger.getLast_name());
            preparedStatement.setString(3, passenger.getEmail());
            preparedStatement.setString(4, passenger.getPassword());
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();

            if(i == 1){
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
        if(queryParamEmail == null || queryParamEmail.isEmpty()){
            return null;
        }
        try{
            query = "select * from passenger where passenger_email= ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, queryParamEmail);
            resultSet = preparedStatement.executeQuery();

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
        if(id == 0){
            return null;
        }
        try{
            query = "select * from passenger where passenger_id= ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();

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
        if(queryParam == null|| columnName == null || queryParam.isEmpty() || columnName.isEmpty()){
            return false;
        }
        try{
            query = "update passenger set "+columnName+"="+value+" where passenger_email='"+queryParam+"'";
            preparedStatement = connection.prepareStatement(query);
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;

        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeObject(int id) {
        if(id == 0){
            return false;
        }
        try{
            statement = connection.createStatement();
            int i = statement.executeUpdate("delete from passenger where passenger_id=" +id);

            if(i == 1){
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

    @Override
    public boolean resetPassword(String email, String newPassword) {
        if(email == null){
            return false;
        }
        try{
            query = "update passenger set passenger_password='"+newPassword+"' where passenger_email='"+email+"'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        }
        catch(SQLException sqlException){
            sqlException.printStackTrace();
        }
        return false;
    }

    private Passenger extractDetails(ResultSet resultSet) throws SQLException{
        Passenger passenger = new Passenger();
        passenger.setId(resultSet.getInt("passenger_id"));
        passenger.setFirst_name(resultSet.getString("passenger_fname"));
        passenger.setLast_name(resultSet.getString("passenger_lname"));
        passenger.setEmail(resultSet.getString("passenger_email"));
        passenger.setCredits(resultSet.getFloat("passenger_credits"));
        passenger.setPassword(resultSet.getString("passenger_password"));
        return passenger;
    }

}
