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
            Connection conn = Util.getConnection();

            String sqlCreate = "CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT(19) not NULL AUTO_INCREMENT, name VARCHAR(70) not NULL, " +
                    "lastname VARCHAR(70) not NULL, age TINYINT, " +
                    "PRIMARY KEY (id))";

            try (Statement st = conn.createStatement()) {
                st.executeUpdate(sqlCreate);
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    conn.rollback();
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
    }

    public void dropUsersTable() {
        Connection conn = Util.getConnection();
        String dropSql = "DROP TABLE IF EXISTS users";

        try (Statement st = conn.createStatement()) {
            st.executeUpdate(dropSql);
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection conn = Util.getConnection();
        String sqlSave = "INSERT users (name, lastName, age) " +
                "VALUES ('" + name + "', " + "'" + lastName + "', " + age + ")";

        try (Statement st = conn.createStatement()) {
            st.executeUpdate(sqlSave);
            conn.commit();
            System.out.printf("User с именем %s добавлен в базу данных" + "\n", name);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        Connection conn = Util.getConnection();
        String deleteSql = "DELETE FROM users WHERE id = " + id;

        try (Statement st = conn.createStatement()) {
            st.executeUpdate(deleteSql);
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        Connection conn = Util.getConnection();
        List<User> list = new ArrayList<>();
        String sqlSelect = "SELECT name, lastname, age FROM users";

        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sqlSelect);
            while (rs.next()) {
                User user = new User();
                user.setName(rs.getString(1));
                user.setLastName(rs.getString(2));
                user.setAge(rs.getByte(3));
                list.add(user);
            }
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        Connection conn = Util.getConnection();
        String truncateSql = "TRUNCATE TABLE users";

        try (Statement st = conn.createStatement()) {
            st.executeUpdate(truncateSql);
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
