package com.example.vehiclesharing.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
    static String configFilePath="resources/application.properties";
    static Properties prop = new Properties();
    static Connection connection;

    public static Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            FileInputStream configfile = new FileInputStream(configFilePath);
            prop.load(configfile);
            connection= DriverManager.getConnection(prop.getProperty("connectionURl"), prop.getProperty("uname"), prop.getProperty("pwd"));
            return connection;

        }
        catch (Exception e){
            throw new RuntimeException("Cannot connect to database", e);
        }
    }
}
