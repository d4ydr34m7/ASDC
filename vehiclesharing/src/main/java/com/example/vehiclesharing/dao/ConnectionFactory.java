package com.example.vehiclesharing.dao;

import com.example.vehiclesharing.constants.IAppConstants;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static ConnectionFactory instance;
    static String configFilePath= IAppConstants.APP_RUN_PATH;
    static Properties prop = new Properties();
    private Connection connection=null;

    private ConnectionFactory() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            FileInputStream configfile = new FileInputStream(configFilePath);
            prop.load(configfile);
            this.connection= DriverManager.getConnection(prop.getProperty("connectionURL"), prop.getProperty("uname"), prop.getProperty("pwd"));

        }
        catch(Exception ex){
            ex.getMessage();
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static ConnectionFactory getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
            return instance;

    }
}
