package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {

        String sqlCreate = "CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT(19) not NULL AUTO_INCREMENT, name VARCHAR(70) not NULL, " +
                "lastname VARCHAR(70) not NULL, age TINYINT, " +
                "PRIMARY KEY (id))";

        try (Connection conn = Util.getConnection();
            Statement st = conn.createStatement()) {
            st.executeUpdate(sqlCreate);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropSql = "DROP TABLE IF EXISTS users";

        try (Connection conn = Util.getConnection();
            Statement st = conn.createStatement()) {
            st.executeUpdate(dropSql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        String sqlSave = "INSERT users (name, lastName, age) " +
                "VALUES ('" + name + "', " + "'" + lastName + "', " + age + ")";

        try (Connection conn = Util.getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate(sqlSave);
            conn.commit();
            System.out.printf("User с именем %s добавлен в базу данных" + "\n", name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String deleteSql = "DELETE FROM users WHERE id = " + id;

        try (Connection conn = Util.getConnection();
            Statement st = conn.createStatement()) {
            st.executeUpdate(deleteSql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {

        List<User> list = new ArrayList<>();
        String sqlSelect = "SELECT name, lastname, age FROM users";
        try (Connection conn = Util.getConnection();
            Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sqlSelect);
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setAge(rs.getByte(3));
                list.add(user);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String truncateSql = "TRUNCATE TABLE users";

        try (Connection conn = Util.getConnection();
             Statement st = conn.createStatement()) {
            st.executeUpdate(truncateSql);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
