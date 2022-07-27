package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.IPassenger;
import com.example.vehiclesharing.model.Passenger;
import com.example.vehiclesharing.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
 class PassengerDAOTest {

    @Autowired
    PassengerDAO passengerDAO;

    @Test
    void testSavePassengerCorrect() {
        User user = new User();
        user.setFirst_name("Shreya");
        user.setLast_name("J");
        user.setEmail("shjayan@gmail.com");
        user.setPassword("123456");
        user.setCredits(220);
        Passenger p = new Passenger(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword());
        assertTrue(passengerDAO.save(p));
    }

    @Test
    void testSavePassengerNull() {
        assertFalse(passengerDAO.save(null));
    }

    @Test
    void testExtractPassengerCorrectByEmail() {
        assertNotNull(passengerDAO.getObjectByEmail("shjayan@gmail.com"));
    }

    @Test
    void testExtractPassengerByEmailIncorrect() {
        assertNull(passengerDAO.getObjectByEmail("test2@case.com"));
    }

    @Test
    void testExtractPassengerByEmailNull() {
        assertNull(passengerDAO.getObjectByEmail(null));
    }

    @Test
    void testExtractPassengerByEmailEmpty() {
        assertNull(passengerDAO.getObjectByEmail(""));
    }

    @Test
    void testExtractPassengerCorrectById() {
        assertNotNull(passengerDAO.getObjectById(29));
    }

    @Test
    void testExtractPassengerByIdIncorrect() {
        assertNull(passengerDAO.getObjectById(100));
    }

    @Test
    void testUpdatePassengerCorrect() {
        assertTrue(passengerDAO.updateObject("shjayan@gmail.com", "passenger_credits", 70));

    }

    @Test
    void testUpdatePassengerInCorrect() {
        assertFalse(passengerDAO.updateObject("pass@case.com", "passenger_cred", 50));

    }

    @Test
    void testUpdatePassengerNull() {
        assertFalse(passengerDAO.updateObject(null, "passenger_credits", 50));

    }

    @Test
    void testUpdatePassengerEmptyEmail() {
        assertFalse(passengerDAO.updateObject("", "passenger_credits", 50));

    }

    @Test
    void testUpdatePassengerEmptyCredits() {
        assertFalse(passengerDAO.updateObject("", "", 50));

    }

    @Test
    void removeByIdCorrect() {
        assertTrue(passengerDAO.removeObject(38));

    }

    @Test
    void removeByIdInCorrect() {
        assertFalse(passengerDAO.removeObject(100));

    }

    @Test
    void getObjectListCorrect() {
        assertTrue(passengerDAO.getObjectsList() != null);
    }

}
