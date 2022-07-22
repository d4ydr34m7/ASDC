package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Driver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public abstract class DriverDAO implements IDriverDAO {
    @Autowired
    JdbcTemplate jdbc;
    Logger log = LoggerFactory.getLogger(IDriverDAO.class);

    @Override
    public boolean saveDriver(Driver driver) {
        if (driver == null || driver.getDriver_email() == null)
            return false;
        try {
            String query = driver.getDriver_fname() + "','" + driver.getDriver_fname()
                    + "','" + driver.getDriver_lname() + "','" + driver.getDriver_email()
                    + "','" + driver.getDriver_password();

            String save = "insert into driver values(" + null + ",'" + query + "');";
            jdbc.update(save);
            log.info("Driver successfully added.");
            return true;
        } catch (Exception exception) {
            log.error("Error while adding Driver", exception);
            return false;
        }
    }

    @Override
    public Driver getDriverdetails(int driverId) {
        try{
            String selectQuery = "select * from driver where driver_id=" + driverId;
            return jdbc.queryForObject(selectQuery, BeanPropertyRowMapper.newInstance(Driver.class));
        }
        catch(Exception e)
        {
            log.error("Incorrect driver id ",e);
            return null;

        }
    }

    public abstract boolean save(Object table);

    public abstract Object getObject(String queryParam);

    public abstract List<Object> getObjectsList();
}