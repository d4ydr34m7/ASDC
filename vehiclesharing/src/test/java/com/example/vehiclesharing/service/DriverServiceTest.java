package com.example.vehiclesharing.service;
import com.example.vehiclesharing.ProjectApplication;
import com.example.vehiclesharing.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestPropertySource("/application-test.properties")
class DriverServiceTest {

    @Autowired
    DriverService driverService;

    @Test
    void testSaveDriverCorrect() {
        User user = new User();
        user.setFirst_name("test");
        user.setLast_name("case");
        user.setEmail("test@case.com");
        user.setPassword("1234563");
        user.setCredits(220);
        user.setUserType("DRIVER");
        assertTrue(driverService.saveDriver(user));
    }

}