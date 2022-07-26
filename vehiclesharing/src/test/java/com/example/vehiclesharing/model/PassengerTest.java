package com.example.vehiclesharing.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
 class PassengerTest {

    @Autowired
    IPassenger iPassenger;


    @Test
    void testSavePassengerCorrect() {
        User user= new User();
        user.setFirst_name("test");
        user.setLast_name("case");
        user.setEmail("test3@case.com");
        user.setPassword("1234563");
        user.setCredits(220);
        assertTrue(iPassenger.savePassenger(user));
    }

    @Test
    void testSavePassengerIInCorrect() {
        User user= new User();
        assertTrue(iPassenger.savePassenger(user));
    }

    @Test
    void testSavePassengerNull() {
        assertFalse(iPassenger.savePassenger(null));
    }

    @Test
    void getPassengerByEmailCorrect() {
        assertTrue(iPassenger.getPassengerByEmail("shjayan@gmail.com") != null);
    }

    @Test
    void getNullPassengerByEmail() {
        assertNull(iPassenger.getPassengerByEmail(null));
    }

    @Test
    void getPassengerByEmailEmpty() {
        assertNull(iPassenger.getPassengerByEmail(""));
    }

    @Test
    void getPassengerByIdCorrect() {
        assertNotNull(iPassenger.getPassengerById(4) != null);
    }

    @Test
    void viewPassengerDetailsTest() {
        assertTrue(iPassenger.viewPassengerDetails() != null);
    }

    @Test
    void deletePassengerTest() {
        assertTrue(iPassenger.deletePassenger(43));
    }

    @Test
    void addCreditsTest() {
        assertTrue(iPassenger.addCredits("shjayan@gmail.com", "passenger_credits", 50));
    }

    @Test
    void addCreditsInCorrectTest() {
        assertFalse(iPassenger.addCredits("test@case.com", "passenger_credits", 50));
    }

    @Test
    void addCreditsNullTest() {
        assertFalse(iPassenger.addCredits(null, "passenger_credits", 50));
    }

    @Test
    void checkIfPasswordUpdated() {
        assertTrue(iPassenger.resetPassword("test@case.com", "xxxyyyzz"));
    }

    @Test
    void checkIfPasswordUpdatedInCorrect() {
        assertFalse(iPassenger.resetPassword("", "xxxyyyzz"));
    }

    @Test
    void checkIfPasswordUpdatedNull() {
        assertFalse(iPassenger.resetPassword("test@case.com", null));
    }

    @Test
    void checkIfPasswordUpdatedEmpty() {
        assertFalse(iPassenger.resetPassword("test@case.com", ""));
    }

    @Test
    void testdebitCreditsFromPassenger() {
        assertTrue(iPassenger.debitCreditsFromPassenger("shjayan@gmail.com", 10));
    }

    @Test
    void testdebitCreditsFromPassengerInCorrect() {
        assertFalse(iPassenger.debitCreditsFromPassenger("", 2));
    }

    @Test
    void testdebitCreditsFromPassengerInNull() {
        assertFalse(iPassenger.debitCreditsFromPassenger(null, 2));
    }
}
