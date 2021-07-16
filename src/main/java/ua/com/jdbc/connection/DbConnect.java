package ua.com.jdbc.connection;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.Properties;

public class DbConnect {
    Connection connection;

    public Connection getDbConnect(){
        Properties properties = loadProperties();
        String url = properties.getProperty("url");
        try{
            connection = DriverManager.getConnection(url, properties);
            connection.setAutoCommit(false);
            return connection;
        } catch (SQLException e) {
            System.out.println("Connection wasn`mt created");
            return null;
        }
    }

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (InputStream input = DbConnect.class.getResourceAsStream("/connection.properties")) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Couldn`t load Properties");
        }
        return properties;
    }

    public void closeConnection(){
        try{
            connection.close();
        } catch (SQLException e) {
            System.out.println("Connection wasn`t closed");
        }
    }
}


