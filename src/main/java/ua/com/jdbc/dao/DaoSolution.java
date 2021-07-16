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

    public DaoSolution(Connection c){
        this.connection = c;
    }

    private boolean checkIFSolutionExists(int problem_id){
        try(PreparedStatement pr = connection.prepareStatement(IF_EXIST)){
            pr.setInt(1, problem_id);
            ResultSet resultSet = pr.executeQuery();
            if(resultSet.next()){
                return false;
            }
    } catch (SQLException e) {
            System.out.println("Couldn`t read id "  + problem_id + " from DB");
        }
        return true;
    }

        public void addSolution(List<Solution> solutions){
        for (Solution s: solutions) {
         if(checkIFSolutionExists(s.getProblem_id())){
             try(PreparedStatement pr = connection.prepareStatement(ADD_SOLUTIONS)){
                 pr.setInt(1, s.getProblem_id());
                 pr.setInt(2, s.getCost());
                 pr.executeUpdate();
                 connection.commit();
             } catch (SQLException e) {
                 System.out.println("Couldn`t insert solution with id "  + s.getProblem_id()  + " into DB");
             }
         }
        }
    }
}
