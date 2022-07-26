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
    private String userType;


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


    public int getId() {
        return driver_id;
    }


    public void setId(int driver_id) {
        this.driver_id = driver_id;
    }


    public String getFirst_name() {
        return driver_fname;
    }


    public void setFirst_name(String driver_fname) {
        this.driver_fname = driver_fname;
    }


    public String getLast_name() {
        return driver_lname;
    }


    public void setLast_name(String driver_lname) {
        this.driver_lname = driver_lname;
    }


    public String getEmail() {
        return driver_email;
    }


    public void setEmail(String driver_email) {
        this.driver_email = driver_email;
    }


    public String getPassword() {
        return driver_password;
    }


    public void setPassword(String driver_password) {
        this.driver_password = driver_password;
    }


    public String getUserType() {
        return userType;
    }


    public void setUserType(String userType) {
        this.userType=userType;

    }


    public float getCredits() {
        return driver_credits;
    }

    public void setCredits(float driver_credits) {
        this.driver_credits = driver_credits;
    }


    @Override
    public boolean saveDriver(User user) {
        if(null==user){
            return false;
        }
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
    public boolean addCredits(String driver_email, float value) {
        if(driver_email.isEmpty() || null==driver_email){
            return false;
        }
        Driver driver=getDriverByEmail(driver_email);
        float driver_credits= driver.getCredits()+value;
        boolean isUpdated= driverDAO.updateObject(driver_email, IAppConstants.DRIVER_CREDITS,driver_credits);
        return isUpdated;
    }


    @Override
    public boolean depositCreditsToDriver(String driver_email, float value) {
        if(null == driver_email || driver_email.isEmpty()){
            return false;
        }
        Driver driver=getDriverByEmail(driver_email);
        float driver_credits= driver.getCredits()+value;
        boolean isUpdated= driverDAO.updateObject(driver_email, IAppConstants.DRIVER_CREDITS,driver_credits);
        return isUpdated;
    }

    @Override
    public boolean resetPassword(String email, String newPassword) {
            if (null == email|| email.isEmpty() || null== newPassword || newPassword.isEmpty()) {
                return false;
            }
            else{
                return driverDAO.resetPassword(email, newPassword);
            }


    }

}
