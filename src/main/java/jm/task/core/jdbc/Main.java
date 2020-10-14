package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        UserServiceImpl UserImpl = new UserServiceImpl();
        //создание таблицы
//        UserImpl.createUsersTable();
        // добавление 4-х юзеров
//        UserImpl.saveUser("Ivan", "Ivanov", (byte) 20);
//        UserImpl.saveUser("Petr", "Petrov", (byte) 20);
//        UserImpl.saveUser("Sidr", "Sidorov", (byte) 20);
//        UserImpl.saveUser("Pavel", "Pavlov", (byte) 20);
//        // получение и вывод юзеров
//        System.out.println(UserImpl.getAllUsers());
//        // очистка таблицы
        UserImpl.cleanUsersTable();
//        // удаление таблицы
//        UserImpl.dropUsersTable();
//        UserImpl.removeUserById(2);


    }
}
