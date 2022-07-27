package com.example.vehiclesharing.dao;

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
@TestPropertySource("/application-test.properties")
 class DriverDAOTest {

    @Autowired
    DriverDAO driverDAO;

    @Autowired
    IDriver idriver;

    @Test
    void testSaveDriverCorrect() {
        User user = new User();
        user.setFirst_name("Aneri");
        user.setLast_name("Shah");
        user.setEmail("shreya7187@gmail.com");
        user.setPassword("qwerty");
        user.setCredits(220);
        Driver d = new Driver(user.getFirst_name(), user.getLast_name(), user.getEmail(), user.getPassword());
        assertTrue(driverDAO.save(d));
    }

    @Test
    void testSaveDriverNull() {
        assertFalse(driverDAO.save(null));
    }


    @Test
    void testExtractDriverCorrectByEmail() {
        assertNotNull(driverDAO.getObjectByEmail("shreya7187@gmail.com"));
    }

    @Test
    void testExtractDriverByEmailIncorrect() {
        assertNull(driverDAO.getObjectByEmail("test2@case.com"));
    }

    @Test
    void testExtractDriverByEmailNull() {
        assertNull(driverDAO.getObjectByEmail(null));
    }

    @Test
    void testExtractDriverByEmailEmpty() {
        assertNull(driverDAO.getObjectByEmail(""));
    }

    @Test
    void testExtractDriverCorrectById() {
        assertNotNull(driverDAO.getObjectById(79));
    }

    @Test
    void testExtractDriverByIdIncorrect() {
        assertNull(driverDAO.getObjectById(100));
    }

    @Test
    void testExtractDriverByIdNull() {
        assertNull(driverDAO.getObjectById(00));
    }

    @Test
    void testUpdateDriverCorrect() {
        assertTrue(driverDAO.updateObject("test@case.com", "driver_credits", 100));

    }

    @Test
    void testUpdateDriverInCorrect() {
        assertFalse(driverDAO.updateObject("test@case.com", "driver_credi", 100));

    }

    @Test
    void testUpdateDriverNull() {
        assertFalse(driverDAO.updateObject(null, "driver_credits", 100));

    }

    @Test
    void testUpdateDriverEmpty() {
        assertFalse(driverDAO.updateObject("", "", 100));

    }

    @Test
    void removeByIdCorrect() {
        assertTrue(driverDAO.removeObject(92));
    }

    @Test
    void removeByIdInCorrect() {
        assertFalse(driverDAO.removeObject(1));

    }
    @Test
    void getObjectListCorrect() {
        assertTrue(driverDAO.getObjectsList() != null);
    }

}
