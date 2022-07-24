//package com.example.vehiclesharing.connection;
//
//import com.example.vehiclesharing.dao.ConnectionFactory;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.sql.SQLException;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//@SpringBootTest
//@RunWith(SpringJUnit4ClassRunner.class)
//@TestPropertySource("/application.properties")
//public class ConnectionTest {
//
//
//    private ConnectionFactory connectionFactory;
//
//    @Before
//    public void setUp() throws SQLException {
//        connectionFactory=  mock(ConnectionFactory.class);
//        when(ConnectionFactory.getInstance()).thenReturn(connectionFactory);
//    }
//
//
//
//
//}
