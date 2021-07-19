package ua.com.jdbc.dao;

import ua.com.jdbc.connection.DbConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoLocation {
    private static final String GET_COUNT = "SELECT COUNT(*) FROM LOCATION";
    private Connection connection;

    public DaoLocation(Connection c) {
        this.connection = c;
    }

    public int getLocationCount() {
        try (PreparedStatement pr = connection.prepareStatement("SELECT count(*) FROM dbcity.location")) {
            ResultSet resultSet = pr.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            return count;
        } catch (SQLException e) {
            System.out.println("Couldn`t get city count");
        }
        throw new RuntimeException();
    }
}
