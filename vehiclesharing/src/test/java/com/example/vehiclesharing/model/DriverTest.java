package com.example.vehiclesharing.model;

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
@TestPropertySource("/application-test.properties")
 class DriverTest {

    @Autowired
    IDriver iDriver;

    @Test
    void testSaveDriverCorrect() {
        User user= new User();
        user.setFirst_name("test2p");
        user.setLast_name("case");
        user.setEmail("test3@case.com");
        user.setPassword("1234563");
        user.setCredits(220);
        assertTrue(iDriver.saveDriver(user));
    }

    @Test
    void testSaveDriverIInCorrect() {
        User user= new User();
        assertTrue(iDriver.saveDriver(user));
    }

    @Test
    void testSaveDriverNull() {
        assertFalse(iDriver.saveDriver(null));
    }

    @Test
    void getDriverByEmailCorrect() {
        assertTrue(iDriver.getDriverByEmail("test3@case.com") != null);
    }

    @Test
    void getNullDriverByEmail() {
        assertNull(iDriver.getDriverByEmail(null));
    }

    @Test
    void getDriverByEmailEmpty() {
        assertNull(iDriver.getDriverByEmail(""));
    }

    @Test
    void getDriverByIdCorrect() {
        assertTrue(iDriver.getDriverById(80) != null);
    }

    @Test
    void viewDriverDetailsTest() {
        assertTrue(iDriver.viewDriverDetails() != null);
    }

    @Test
    void deleteDriverTest() {
        assertTrue(iDriver.deleteDriver(111));
    }

    @Test
    void addCreditsTest() {
        assertTrue(iDriver.addCredits("test3@case.com", 50));
    }

    @Test
    void addCreditsInCorrectTest() {
        assertFalse(iDriver.addCredits("",  50));
    }

    @Test
    void checkIfPasswordUpdated() {
        assertTrue(iDriver.resetPassword("test3@case.com", "xxxyyyzz"));
    }

    @Test
    void checkIfPasswordUpdatedInCorrect() {
        assertFalse(iDriver.resetPassword("", "xxxyyyzz"));
    }

    @Test
    void checkIfPasswordUpdatedNull() {
        assertFalse(iDriver.resetPassword("test3@case.com", null));
    }

    @Test
    void checkIfPasswordUpdatedEmpty() {
        assertFalse(iDriver.resetPassword("test3@case.com", ""));
    }

    @Test
    void testDepositCreditsToDriver() {
        assertTrue(iDriver.depositCreditsToDriver("test3@case.com", 30));
    }

    @Test
    void testDepositCreditsToDriverEmpty() {
        assertFalse(iDriver.depositCreditsToDriver("", 30));
    }

    @Test
    void testDepositCreditsToDriverNull() {
        assertFalse(iDriver.depositCreditsToDriver(null, 30));
    }


}
