//package com.example.vehiclesharing.service;
//
//import com.example.vehiclesharing.constants.IAppConstants;
//import com.example.vehiclesharing.constants.IAppMessages;
//import com.example.vehiclesharing.dao.DriverDAO;
//import com.example.vehiclesharing.model.Driver;
//import com.example.vehiclesharing.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class DriverServiceImpl implements DriverService{
//
//    @Autowired
//    private DriverDAO driverDAO;
//
//    @Autowired
//    private NotifyService notifyService;
//
//    @Override
//    public boolean saveDriver(User user) {
//        Driver driver=new Driver(user.getFirst_name(),user.getLast_name(),user.getEmail(),user.getPassword());
//        boolean isDriverSaved=driverDAO.save(driver);
//        if(isDriverSaved)
//        {
//            notifyService.sendEmail(IAppMessages.USER_REGISTERED_NOTIFY_MESSAGE,IAppMessages.USER_REGISTERED_NOTIFY_SUBJECT,user.getEmail());
//        }
//        return isDriverSaved;
//    }
//
//    @Override
//    public Driver getDriverByEmail(String email) {
//        Driver driver=(Driver) driverDAO.getObject(email);
//        return driver;
//    }
//
//    //admin
//
//    @Override
//    public List<Driver> viewDriverDetails() {
//        try{
//            List<Object> driverObjList=  driverDAO.getObjectsList();
//            List<Driver> driverList= new ArrayList<>();
//            for(Object o:driverObjList)
//            {
//                driverList.add((Driver)o);
//            }
//            return driverList;
//        }
//        catch(Exception exception){
//            return null;
//
//        }
//
//    }
//
//    @Override
//    public boolean deleteDriver(int driver_id) {
//        return driverDAO.removeObject(driver_id);
//
//    }
//
//    @Override
//    public boolean addCredits(String driver_email, String columnName, float value) {
//        Driver driver=getDriverByEmail(driver_email);
//        float driver_credits= driver.getDriver_credits()+value;
//        boolean isUpdated= driverDAO.updateObject(driver_email, IAppConstants.DRIVER_CREDITS,driver_credits);
//        return isUpdated;
//    }
//
//    @Override
//    public boolean depositCreditsToDriver(String driver_email, String columnName, float value) {
//        Driver driver=getDriverByEmail(driver_email);
//        float driver_credits= driver.getDriver_credits()+value;
//        boolean isUpdated= driverDAO.updateObject(driver_email, IAppConstants.DRIVER_CREDITS,driver_credits);
//        return isUpdated;
//    }
//
//
//}
