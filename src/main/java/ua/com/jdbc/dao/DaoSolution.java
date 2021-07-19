package ua.com.jdbc.dao;

import ua.com.jdbc.entity.Solution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DaoSolution {
    private static final String ADD_SOLUTIONS = "INSERT INTO SOLUTION (problem_id, cost) VALUES (? , ?)";
    private static final String IF_EXIST = "SELECT problem_id FROM SOLUTION WHERE problem_id = ?";
    private Connection connection;

    public DaoSolution(Connection c) {
        this.connection = c;
    }

    public void addSolution(List<Solution> solutions) {
        try (PreparedStatement pr = connection.prepareStatement(ADD_SOLUTIONS)) {
            for (Solution s: solutions){
                pr.setInt(1, s.getProblem_id());
                pr.setInt(2, s.getCost());
                pr.addBatch();
            }
            pr.executeBatch();
        } catch (SQLException e) {
            System.out.println("Couldn`t insert solutions");
            throw new RuntimeException(e);
        }
    }
}
