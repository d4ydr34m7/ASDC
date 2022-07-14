package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO{

    Connection connection= ConnectionFactory.getConnection();
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;


    //This method is used to insert booking object into the database
    @Override
    public boolean saveRide(Booking booking) {
        if(booking==null||booking.getTimestamp().isEmpty())
            return false;
        try {
            String query = "INSERT INTO booking(booking_id, timestamp, amount,seats_booked, passenger_id)" + "VALUES (?, ?, ?, ?, ?)";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setInt(1, booking.getBooking_id());
            preparedStatement.setString(2, booking.getTimestamp());
            preparedStatement.setFloat(3, booking.getAmount());
            preparedStatement.setInt(4, booking.getSeats_booked());
            preparedStatement.setInt(5, booking.getPassenger_id());
            int result =preparedStatement.executeUpdate();

            if(result>0){
                return true;
            }

        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public List<Booking> getAllRidesForPassenger(int passenger_id) {
        List<Booking> rides = new ArrayList<>();
        try {
            String selectBookingQuery = "select * from booking where passenger_id= ?" ;
            preparedStatement= connection.prepareStatement(selectBookingQuery);
            preparedStatement.setInt(1, passenger_id);
            resultSet=preparedStatement.executeQuery();

            if(resultSet.next()){
                return (List<Booking>) extractDetails(resultSet);
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }
    public Booking extractDetails(ResultSet resultSet) throws SQLException {
        Booking booking= new Booking();
        booking.setBooking_id(resultSet.getInt("booking_id"));
        booking.setTimestamp(resultSet.getString("timestamp"));
        booking.setAmount(resultSet.getFloat("amount"));
        booking.setSeats_booked(resultSet.getInt("seats_booked"));
        booking.setPassenger_id(resultSet.getInt("passenger_id"));
        return booking;
    }

    //This method is used to update the IsPaid field to 1 when the passenger pays for the ride
    @Override
    public boolean updateIsPaid(int passenger_id, int booked_ride_id) {
        try{
            String sql = "update booking set isPaid=1 where passenger_id= ? and booked_ride_id= ?";
            preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1, passenger_id);
            preparedStatement.setInt(2, booked_ride_id);
            int result1 =preparedStatement.executeUpdate();
            if(result1>0){
                return true;
            }

        }
        catch(Exception e)
        {

        }
        return false;
    }

}
