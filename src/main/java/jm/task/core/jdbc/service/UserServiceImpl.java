package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    UserDaoJDBCImpl UserDAO = new UserDaoJDBCImpl();
    public void createUsersTable() {
        UserDAO.createUsersTable();
    }

    public void dropUsersTable() {
        UserDAO.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDAO.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDAO.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return UserDAO.getAllUsers();

    }

    public void cleanUsersTable() {
        UserDAO.cleanUsersTable();
    }
}
