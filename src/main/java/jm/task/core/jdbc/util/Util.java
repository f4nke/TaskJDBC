package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;



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

    public static Connection getConnection() {
        try {
            if (connection.isClosed()) {
                connection = DriverManager.getConnection(hostName, userName, password);
                connection.setAutoCommit(false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public Util()  {
    }



    // Конфигурация hibernate
    public static SessionFactory getHiberSession() {

        Configuration cfg = new Configuration();
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", hostName);
        cfg.setProperty("hibernate.connection.username", userName);
        cfg.setProperty("hibernate.connection.password", password);
        cfg.setProperty("hibernate.show_sql", "true");
        cfg.setProperty("hibernate.connection.pool_size", "2");
        cfg.setProperty("hibernate.hbm2ddl.auto", "update");
        cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL55Dialect");
        cfg.addAnnotatedClass(User.class);

        return  cfg.buildSessionFactory( new StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties()).build());
    }
}
