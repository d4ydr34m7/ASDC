package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.model.Passenger;
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
public class PassengerDAOImplTest {


        @Autowired
        PassengerDAO passengerDAO;

        @Test
        void testSaveNullPassenger() {
            assertFalse(passengerDAO.save(null));
        }

        @Test
        void testSaveEmptyPasssenger() {
            Passenger passenger = new Passenger();
            passenger.setPassenger_fname("");
            assertFalse(passengerDAO.save(passenger));
        }

        @Test
        void testSavePasseneger() {
            if(passengerDAO.getObject("abc@gmail.com")==null)

                assertTrue(passengerDAO.save(new Passenger("test_fname","test_lname","test_lname","testmail@case.com","testpassword")));
        }

        @Test
        void testGetPassenegerByCorrectEmail() {
            assertTrue(passengerDAO.getObject("tesmailt@case.com")!=null);
        }

        @Test
        void testGetPassenegerByWrongEmail() {
            assertNull(passengerDAO.getObject("doremi.pogiso"));
        }

        @Test
        void testGetPassengerBybadEmail() {
            assertNull(passengerDAO.getObject("hasdfik@coml"));
        }

        @Test
        void testGetPassengerByEmptyEmail() {
            assertNull(passengerDAO.getObject(""));
        }


//        @Test
//        void testupdateAvaialableCredits(){
//            assertTrue(passengerDAO.updateAvaialableCredits(9,22));
//        }
    }

