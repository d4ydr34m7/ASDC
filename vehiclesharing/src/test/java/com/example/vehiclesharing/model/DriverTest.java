package com.example.vehiclesharing.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application.properties")
public class DriverTest {

    @Autowired
    IDriver iDriver;

    @Test
    void testSaveDriverCorrect() {
        User user = new User();
        user.setFirst_name("test2p");
        user.setLast_name("case");
        user.setEmail("test@case.com");
        user.setPassword("1234563");
        user.setCredits(220);
        user.setUserType("DRIVER");
        //Driver d= idriver.convertObject();
        //Driver d = new Driver(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword());
        assertTrue(iDriver.saveDriver(user));
    }

    @Test
    void getDriverByEmailCorrect(){
        assertTrue(iDriver.getDriverByEmail("test@case.com")!=null);
    }

    @Test
    void getNullDriverByEmail(){
        assertNull(iDriver.getDriverByEmail(null));
    }

    @Test
    void getDriverByIdCorrect(){
        assertTrue(iDriver.getDriverById(2)!=null);
    }

    @Test
    void viewDriverDetailsTest(){
        assertTrue(iDriver.viewDriverDetails()!=null);
    }

    @Test
    void deleteDriverTest(){
        assertTrue(iDriver.deleteDriver(3));
    }

    @Test
    void addCreditsTest(){
        assertTrue(iDriver.addCredits("test@case.com", "driver_credits", 50));
    }

    @Test
    void checkIfPasswordUpdated(){
        assertTrue(iDriver.resetPassword("test@case.com", "xxxyyyzz"));
    }




}
