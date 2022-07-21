//package com.example.vehiclesharing.service;
//
//import com.example.vehiclesharing.constants.IAppConstants;
//import com.example.vehiclesharing.constants.IAppMessages;
//import com.example.vehiclesharing.dao.PassengerDAO;
//import com.example.vehiclesharing.model.Driver;
//import com.example.vehiclesharing.model.Passenger;
//import com.example.vehiclesharing.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class PassengerServiceImpl implements PassengerService{
//
//    @Autowired
//    PassengerDAO passengerDAO;
//
//    @Autowired
//    private NotifyService notifyService;
//
//    @Override
//    public boolean savePassenger(User user) {
//        Passenger passenger=new Passenger(user.getFirst_name(),user.getLast_name(),user.getEmail(),user.getPassword(),user.getContact());
//        boolean isPassengerSaved= passengerDAO.save(passenger);
//        if(isPassengerSaved)
//        {
//            notifyService.sendEmail(IAppMessages.USER_REGISTERED_NOTIFY_MESSAGE,IAppMessages.USER_REGISTERED_NOTIFY_SUBJECT,user.getEmail());
//        }
//        return isPassengerSaved;
//    }
//
//    @Override
//    public Passenger getPassengerByEmail(String email) {
//        Passenger passenger= (Passenger) passengerDAO.getObject(email);
//        return passenger;
//    }
//
//
//    //admin
//    @Override
//    public List<Passenger> viewPassengerDetails() {
//        try{
//            List<Object> passenger=  passengerDAO.getObjectsList();
//            List<Passenger> passengerList= (List<Passenger>) (List) passenger;
//            return passengerList;
//        }
//        catch(Exception exception){
//            return null;
//
//        }
//    }
//
//    @Override
//    public boolean deletePassenger(int passenger_id) {
//        return passengerDAO.removeObject(passenger_id);
//    }
//
//    @Override
//    public boolean addCredits(String passenger_email, String columnName, float value) {
//        Passenger passenger=getPassengerByEmail(passenger_email);
//        float passenger_credits= passenger.getPassenger_credits()+value;
//        boolean isUpdated= passengerDAO.updateObject(passenger_email, IAppConstants.PASSENGER_CREDITS,passenger_credits);
//        return isUpdated;    }
//
//    @Override
//    public boolean debitCreditsFromPassenger(String passenger_email, String columnName, float value) {
//        Passenger passenger=getPassengerByEmail(passenger_email);
//        if(passenger.getPassenger_credits()<value)
//        {
//            return false;
//        }
//        float passenger_credits= passenger.getPassenger_credits()+value;
//        boolean isUpdated= passengerDAO.updateObject(passenger_email, IAppConstants.PASSENGER_CREDITS,passenger_credits);
//        return isUpdated;
//    }
//}
