package com.example.vehiclesharing.dao;
import com.example.vehiclesharing.model.Driver;
import com.example.vehiclesharing.model.IPassenger;
import com.example.vehiclesharing.model.Passenger;
import com.example.vehiclesharing.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application.properties")
public class PassengerDAOTest {

    @Autowired
    PassengerDAO passengerDAO;

    @Autowired
    IPassenger iPassenger;

    @Test
    void testSavePassengerCorrect() {
        User user = new User();
        user.setFirst_name("pass");
        user.setLast_name("case");
        user.setEmail("pass@case.com");
        user.setPassword("123456");
        user.setCredits(220);
        user.setUserType("PASSENGER");
        //Passenger p= iPassenger.convertObject();
        Passenger p=new Passenger(user.getFirst_name(), user.getLast_name(), user.getEmail(),user.getPassword());
        assertTrue(passengerDAO.save(p));
    }

    @Test
    void testExtractPassengerCorrectByEmail(){
        assertNotNull(passengerDAO.getObjectByEmail("pass@case.com"));
    }

    @Test
    void testExtractPassengerByEmailIncorrect(){
        assertNull(passengerDAO.getObjectByEmail("test2@case.com"));
    }

    @Test
    void testExtractPassengerCorrectById(){
        assertNotNull(passengerDAO.getObjectById(1));
    }

    @Test
    void testExtractPassengerByIdIncorrect(){
        assertNull(passengerDAO.getObjectById(100));
    }

    @Test
    void testUpdatePassengerCorrect(){
        assertTrue(passengerDAO.updateObject("pass@case.com", "passenger_credits", 50));

    }

    @Test
    void removeByIdCorrect(){
        assertTrue(passengerDAO.removeObject(2));

    }

    @Test
    void getObjectListCorrect(){
        assertTrue(passengerDAO.getObjectsList()!=null);
    }


}



