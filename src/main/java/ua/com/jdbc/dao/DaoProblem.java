package ua.com.jdbc.dao;

import ua.com.jdbc.entity.Problem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoProblem {
    private static final String GET_PROBLEMS = "SELECT * FROM problem LEFT JOIN solution on problem.id = solution.problem_id WHERE solution.cost IS NULL";
    private Connection connection;

    public DaoProblem(Connection c) {
        this.connection = c;
    }

    public List<Problem> getProblems() {
        List<Problem> problemsList = new ArrayList<Problem>();
        try (PreparedStatement pr = connection.prepareStatement(GET_PROBLEMS)) {
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Problem problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setFrom_id(resultSet.getInt("from_id"));
                problem.setTo_id(resultSet.getInt("to_id"));
                problemsList.add(problem);
            }
            return problemsList;
        } catch (SQLException e) {
            System.out.println("Couldn`t get list of Problems");
        }
        throw new RuntimeException();
    }
}
