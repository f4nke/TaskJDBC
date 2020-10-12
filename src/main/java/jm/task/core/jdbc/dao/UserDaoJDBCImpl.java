package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoJDBCImpl implements UserDao {

    Util util = new Util();
    private Statement statement;

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sqlCreate = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT(19) not NULL AUTO_INCREMENT, name VARCHAR(70) not NULL, " +
                "lastname VARCHAR(70) not NULL, age TINYINT, " +
                "PRIMARY KEY (id))";

        try {
            statement = util.getConnection().createStatement();
            statement.executeUpdate(sqlCreate);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void dropUsersTable() {
        String dropSql = "DROP TABLE IF EXISTS users";

        try {
            statement = util.getConnection().createStatement();
            statement.execute(dropSql);
            System.out.println("Table dropped");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sqlSave = "INSERT users (name, lastName, age) " +
                "VALUES ('" + name + "', " + "'" + lastName + "', " + age + ")";

        try {
            statement = util.getConnection().createStatement();
            statement.executeUpdate(sqlSave);

            System.out.printf("User с именем %s добавлен в базу данных" + "\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String deleteSql = "DELETE FROM users WHERE id = " + id;

        try {
            statement = util.getConnection().createStatement();
            statement.executeUpdate(deleteSql);
            System.out.println("Удален User c id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        String sqlSelect = "SELECT name, lastname, age FROM users";
        try {
            statement = util.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(sqlSelect);
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setAge(rs.getByte(3));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        String truncateSql = "TRUNCATE TABLE users";

        try {
            statement = util.getConnection().createStatement();
            statement.execute(truncateSql);
            System.out.println("Table clear");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
