package ua.com.jdbc;

import ua.com.jdbc.algorithm.ShortestPath;
import ua.com.jdbc.connection.DbConnect;
import ua.com.jdbc.dao.DaoLocation;
import ua.com.jdbc.dao.DaoProblem;
import ua.com.jdbc.dao.DaoRoute;
import ua.com.jdbc.dao.DaoSolution;
import ua.com.jdbc.entity.Problem;
import ua.com.jdbc.entity.Route;
import ua.com.jdbc.entity.Solution;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GeneralService {
    private DbConnect dbConnect;
    private DaoRoute daoRoute;
    private DaoProblem daoProblem;
    private DaoLocation daoLocation;
    private DaoSolution daoSolution;
    private ShortestPath shortestPath;

    public GeneralService(){
        dbConnect = new DbConnect();
        Connection connection = dbConnect.getDbConnect();
        daoLocation = new DaoLocation(connection);
        daoProblem = new DaoProblem(connection);
        daoRoute = new DaoRoute(connection);
        daoSolution = new DaoSolution(connection);
        shortestPath = new ShortestPath();
    }

    public void start(){
        shortestPath.setCities(daoLocation.getLocationCount());
        List<Route> routes = daoRoute.getRoute();
        shortestPath.setGraph(getAdjacencyMatrix(routes, shortestPath.getCities()));
        List<Problem> problems = daoProblem.getProblems();
        List<Solution> solutions = getSolution(problems);
        daoSolution.addSolution(solutions);
        dbConnect.closeConnection();
    }

    private int[][] getAdjacencyMatrix(List<Route> routes, int count) {
        int[][] graph = new int[count][count];
        for (Route r : routes) {
            graph[r.getFrom_id() - 1][r.getTo_id() - 1] = r.getCost();
        }
        return graph;
    }

    private List<Solution> getSolution(List<Problem> problems){
        List<Solution> solutions = new ArrayList<Solution>();
        for (Problem p: problems) {
            Solution s = new Solution();
            s.setProblem_id(p.getId());
            s.setCost(shortestPath.dijkstra(p.getFrom_id()-1, p.getTo_id()-1));
            solutions.add(s);
        }
        return solutions;
    }
}
