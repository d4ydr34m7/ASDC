package com.example.vehiclesharing.dao;
import com.example.vehiclesharing.constants.IAppConstants;
import com.example.vehiclesharing.model.*;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application.properties")
public class DriverDAOTest {

    @Autowired
    DriverDAO driverDAO;

    @Autowired
    IDriver idriver;

    //@Autowired
    //UserFactory userFactory;

    @Test
    void testSaveDriverCorrect() {
        User user= new User();
        //IUser user= userFactory.getInstance(IAppConstants.DRIVER);
        user.setFirst_name("Aneri");
        user.setLast_name("Shah");
        user.setEmail("shreya7187@gmail.com");
        user.setPassword("qwerty");
        user.setCredits(220);
        Driver d= new Driver(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword());
        assertTrue(driverDAO.save(d));
    }

    @Test
    void testExtractDriverCorrectByEmail(){
        assertNotNull(driverDAO.getObjectByEmail("test@case.com"));
    }

    @Test
    void testExtractDriverByEmailIncorrect(){
        assertNull(driverDAO.getObjectByEmail("test2@case.com"));
    }

    @Test
    void testExtractDriverCorrectById(){
        assertNotNull(driverDAO.getObjectById(4));
    }

    @Test
    void testExtractDriverByIdIncorrect(){
        assertNull(driverDAO.getObjectById(100));
    }


    @Test
    void testUpdateDriverCorrect(){
        assertTrue(driverDAO.updateObject("test@case.com", "driver_credits", 100));

    }

    @Test
    void removeByIdCorrect(){
        assertTrue(driverDAO.removeObject(6));

    }

    @Test
    void getObjectListCorrect(){
        assertTrue(driverDAO.getObjectsList()!=null);
    }



}
