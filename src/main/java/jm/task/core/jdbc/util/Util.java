package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Properties;


public class Util {

    private static final String hostName = "jdbc:mysql://localhost/pre_project?serverTimezone=UTC";
    private static final String userName = "root";
    private static final String password = "indie4ever";

    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(hostName, userName, password);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection.isClosed()) {
                connection = DriverManager.getConnection(hostName, userName, password);
        }
        return connection;
    }

    public Util()  {
    }



    // Конфигурация hibernate
    
}
