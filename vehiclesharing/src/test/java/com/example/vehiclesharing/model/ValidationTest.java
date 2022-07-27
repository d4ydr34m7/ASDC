package com.example.vehiclesharing.model;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
 class ValidationTest {

    @Autowired
    Validation validation;

    @Test
    void testCheckUserExistNull() {

        assertFalse(validation.checkIfUserExists(null));
    }

    @Test
    void checkIfUserExistsTest(){
        User user = new User();
        user.setEmail("subham@gmail.com");
        user.setUserType("Driver");
        assertTrue(validation.checkIfUserExists(user));
    }

    @Test
    void validateUserTest(){
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("admin@1234");
        user.setUserType("Admin");
        assertTrue(validation.validateUser(user));
    }

}
