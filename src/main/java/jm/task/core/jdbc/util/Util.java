package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;

public class Util {

    private final String hostName = "jdbc:mysql://localhost/pre_project?serverTimezone=UTC";
    private final String userName = "root";
    private final String password = "indie4ever";

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public Util() {
        try {
            connection = DriverManager.getConnection(hostName, userName, password);
            System.out.println("connection ok");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("connection error");
        }
    }
}
