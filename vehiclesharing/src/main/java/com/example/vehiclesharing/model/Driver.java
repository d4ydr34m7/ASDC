package com.example.vehiclesharing.model;

import com.example.vehiclesharing.constants.AdminCredentials;
import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.constants.IAppMessages;
import com.example.vehiclesharing.dao.DriverDAO;
import com.example.vehiclesharing.dao.PassengerDAO;
import com.example.vehiclesharing.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.vehiclesharing.model.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class Driver implements IDriver{
    private int driver_id;
    private String driver_fname;
    private String driver_lname;
    private String driver_email;
    private String driver_password;
    private float driver_credits;


    @Autowired
    private DriverDAO driverDAO;

    @Autowired
    PassengerDAO passengerDAO;

    @Autowired
    private Notification notification;


    public Driver(){}

    public Driver( String driver_fname, String driver_lname, String driver_email, String driver_password) {
        this.driver_fname = driver_fname;
        this.driver_lname = driver_lname;
        this.driver_email = driver_email;
        this.driver_password = driver_password;
        //this.driver_contact = driver_contact;
    }


    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public String getDriver_fname() {
        return driver_fname;
    }

    public void setDriver_fname(String driver_fname) {
        this.driver_fname = driver_fname;
    }

    public String getDriver_lname() {
        return driver_lname;
    }

    public void setDriver_lname(String driver_lname) {
        this.driver_lname = driver_lname;
    }

    public String getDriver_email() {
        return driver_email;
    }

    public void setDriver_email(String driver_email) {
        this.driver_email = driver_email;
    }

    public String getDriver_password() {
        return driver_password;
    }

    public void setDriver_password(String driver_password) {
        this.driver_password = driver_password;
    }

    public float getDriver_credits() {
        return driver_credits;
    }

    public void setDriver_credits(float driver_credits) {
        this.driver_credits = driver_credits;
    }


    @Override
    public boolean saveDriver(User user) {
        Driver driver=new Driver(user.getFirst_name(),user.getLast_name(),user.getEmail(),user.getPassword());
        boolean isDriverSaved=driverDAO.save(driver);
        if(isDriverSaved)
        {
            notification.sendEmail(IAppMessages.USER_REGISTERED_NOTIFY_MESSAGE,IAppMessages.USER_REGISTERED_NOTIFY_SUBJECT,user.getEmail());
        }
        return isDriverSaved;
    }


    @Override
    public Driver getDriverByEmail(String email) {
        if(email==null){
            return null;
        }
        Driver driver=(Driver) driverDAO.getObjectByEmail(email);
        return driver;
    }

    @Override
    public Driver getDriverById(int id) {
        Driver driver=(Driver) driverDAO.getObjectById(id);  //check null cond
        return driver;
    }


    //admin


    @Override
    public List<Driver> viewDriverDetails() {
        try{
            List<Object> driverObjList=  driverDAO.getObjectsList();
            List<Driver> driverList= new ArrayList<>();
            for(Object o:driverObjList)
            {
                driverList.add((Driver)o);
            }
            return driverList;
        }
        catch(Exception exception){
            return null;

        }

    }


    @Override
    public boolean deleteDriver(int driver_id) {
        return driverDAO.removeObject(driver_id);

    }


    @Override
    public boolean addCredits(String driver_email, String columnName, float value) {
        Driver driver=getDriverByEmail(driver_email);
        float driver_credits= driver.getDriver_credits()+value;
        boolean isUpdated= driverDAO.updateObject(driver_email, IAppConstants.DRIVER_CREDITS,driver_credits);
        return isUpdated;
    }


    @Override
    public boolean depositCreditsToDriver(String driver_email, String columnName, float value) {
        Driver driver=getDriverByEmail(driver_email);
        float driver_credits= driver.getDriver_credits()+value;
        boolean isUpdated= driverDAO.updateObject(driver_email, IAppConstants.DRIVER_CREDITS,driver_credits);
        return isUpdated;
    }

    @Override
    public Driver convertObject(){
        Driver driver = new Driver(getDriver_fname(), getDriver_lname(), getDriver_email(), getDriver_password());
        return driver;
    }



}
