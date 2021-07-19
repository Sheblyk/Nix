package ua.com.jdbc.dao;

import ua.com.jdbc.entity.Route;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoRoute {

    private static final String GET_ROUTES = "SELECT * FROM ROUTE";
    private Connection connection;

    public DaoRoute(Connection c) {
        this.connection = c;
    }

    public List<Route> getRoute() {
        List<Route> routes = new ArrayList<Route>();
        try (PreparedStatement pr = connection.prepareStatement(GET_ROUTES)) {
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Route route = new Route();
                route.setId(resultSet.getInt("id"));
                route.setFrom_id(resultSet.getInt("from_id"));
                route.setTo_id(resultSet.getInt("to_id"));
                route.setCost(resultSet.getInt("cost"));
                routes.add(route);
            }
            return routes;
        } catch (SQLException e) {
            System.out.println("Couldn`t get list of routes");
        }
        throw new RuntimeException();
    }
}
