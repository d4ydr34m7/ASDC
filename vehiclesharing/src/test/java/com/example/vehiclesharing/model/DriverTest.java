package com.example.vehiclesharing.model;

import com.example.vehiclesharing.constants.IAppConstants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application.properties")
public class DriverTest {

    @Autowired
    IDriver iDriver;

//    @Autowired
//    UserFactory userFactory;

    @Test
    void testSaveDriverCorrect() {
        User user= new User();
        //IUser user= userFactory.getInstance(IAppConstants.DRIVER);
        user.setFirst_name("test2p");
        user.setLast_name("case");
        user.setEmail("test3@case.com");
        user.setPassword("1234563");
        user.setCredits(220);
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
        assertFalse(iDriver.deleteDriver(00));
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
