package com.example.vehiclesharing.model;

import com.example.vehiclesharing.constants.IAppConstants;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application.properties")
public class UserTest {

    @Autowired
    User user;

    @Test
    void testGetCredits() {
        user.setCredits(10);
        assertEquals(user.getCredits(), 10);
    }

    @Test
    void testGetEmail() {
        user.setEmail("test@case.com");
        assertEquals(user.getEmail(), "test@case.com");
    }

    @Test
    void testGetFirst_name() {
        user.setFirst_name("test");
        assertEquals(user.getFirst_name(), "test");
    }

    @Test
    void testGetId() {
        user.setId(2);
        assertEquals(user.getId(), 2);

    }

    @Test
    void testGetLast_name() {
        user.setLast_name("test");
        assertEquals(user.getLast_name(), "test");
    }

    @Test
    void testGetPassword() {
        user.setPassword("test");
        assertEquals(user.getPassword(), "test");
    }

    @Test
    void testGetUserType() {
        user.setUserType(IAppConstants.PASSENGER);
        assertEquals(user.getUserType(), IAppConstants.PASSENGER);

    }

    @Test
    void testSetCredits() {
        user.setCredits(100);
        assertEquals(user.getCredits(), 100);
    }

    @Test
    void testSetEmail() {
        user.setEmail("test@case.com");
        assertEquals(user.getEmail(), "test@case.com");
    }

    @Test
    void testSetFirst_name() {
        user.setFirst_name("test");
        assertEquals(user.getFirst_name(), "test");

    }

    @Test
    void testSetId() {
        user.setId(2);
        assertEquals(user.getId(), 2);

    }

    @Test
    void testSetLast_name() {
        user.setLast_name("test");
        assertEquals(user.getLast_name(), "test");

    }

    @Test
    void testSetPassword() {
        user.setPassword("test");
        assertEquals(user.getPassword(), "test");
    }

    @Test
    void testSetUserType() {
        user.setUserType(IAppConstants.PASSENGER);
        assertEquals(user.getUserType(), IAppConstants.PASSENGER);
    }
}
