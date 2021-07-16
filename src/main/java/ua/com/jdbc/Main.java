package ua.com.jdbc;

import ua.com.jdbc.GeneralService;
import ua.com.jdbc.connection.DbConnect;
import ua.com.jdbc.dao.DaoLocation;
import ua.com.jdbc.dao.DaoProblem;
import ua.com.jdbc.dao.DaoRoute;
import ua.com.jdbc.entity.Problem;
import ua.com.jdbc.entity.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args){
        GeneralService generalService = new GeneralService();
        generalService.start();
    }
}
