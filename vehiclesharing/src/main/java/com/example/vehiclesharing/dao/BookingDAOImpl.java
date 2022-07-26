package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Booking;
import com.example.vehiclesharing.model.IBookingBuilder;
import com.example.vehiclesharing.model.ModelFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class BookingDAOImpl implements IBookingDAO {

    Connection connection= ConnectionFactory.getInstance().getConnection();
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public BookingDAOImpl() throws SQLException {
    }


    //This method is used to insert booking object into the database
    @Override
    public boolean saveRide(Booking booking) {
        if(booking==null||booking.getTimestamp().isEmpty()||booking.getAmount()==0)
            return false;
        try {
            String query = "INSERT INTO booking(booking_id, passenger_id, timestamp, amount,seats_booked, ride_id )" + "VALUES (NULL, ?, ?, ?, ?,?)";
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(2, booking.getTimestamp());
            preparedStatement.setFloat(4, booking.getAmount());
            preparedStatement.setInt(3, booking.getSeats_booked());
            preparedStatement.setInt(1, booking.getPassenger_id());
            preparedStatement.setInt(5, booking.getRide_id());

            int result =preparedStatement.executeUpdate();

            if(result>0){
                return true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public List<Booking> getAllRidesForPassenger(int passenger_id) {
        List<Booking> rides = new ArrayList<>();
        try {
            String selectBookingQuery = "select * from booking where passenger_id= ?";
            preparedStatement = connection.prepareStatement(selectBookingQuery);
            preparedStatement.setInt(1, passenger_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Booking booking = extractDetails(resultSet);
                rides.add(booking);

                }
            return rides;
            }
        catch(SQLException sqlException){
                sqlException.printStackTrace();
            }
            return null;
        }


    public Booking extractDetails(ResultSet resultSet) throws SQLException {
        Booking bookingObject = null;
        IBookingBuilder iBookingBuilder = ModelFactory.instance().createBookingBuilder();

                bookingObject = iBookingBuilder
        .addBooking_id(resultSet.getInt("booking_id"))
                        .addTimestamp(resultSet.getString("timestamp"))
                        .addAmount(resultSet.getFloat("amount"))
                        .addSeats_booked(resultSet.getInt("seats_booked"))
                        .addPassenger_id(resultSet.getInt("passenger_id"))
                        .addRide_id(resultSet.getInt("ride_id"))
                        .build();
                return bookingObject;
//        Booking booking= new Booking();
//        booking.setBooking_id(resultSet.getInt("booking_id"));
//        booking.setTimestamp(resultSet.getString("timestamp"));
//        booking.setAmount(resultSet.getFloat("amount"));
//        booking.setSeats_booked(resultSet.getInt("seats_booked"));
//        booking.setPassenger_id(resultSet.getInt("passenger_id"));
//        booking.setRide_id(resultSet.getInt("ride_id"));
//        return booking;
    }


}
