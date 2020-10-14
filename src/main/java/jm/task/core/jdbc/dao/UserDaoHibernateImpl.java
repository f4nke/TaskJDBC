package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.PersistenceException;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getHiberSession().openSession()) {
            Transaction tr = session.getTransaction();
            try {
                session.beginTransaction();
                session.createSQLQuery("CREATE TABLE IF NOT EXISTS users " +
                        "(id BIGINT(19) not NULL AUTO_INCREMENT, name VARCHAR(70) not NULL, " +
                        "lastname VARCHAR(70) not NULL, age TINYINT, " +
                        "PRIMARY KEY (id))").executeUpdate();
                tr.commit();
                session.getTransaction().rollback();
            } catch (HibernateError e) {
                e.printStackTrace();
                tr.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        try (Session session = Util.getHiberSession().openSession()) {
            Transaction tr = session.getTransaction();
            try {
                session.beginTransaction();
                session.createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
                session.getTransaction().commit();
                System.out.println("таблица удалена");
            } catch (HibernateError e) {
                e.printStackTrace();
                tr.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getHiberSession().openSession()) {
            Transaction tr = session.getTransaction();
            try {
            session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
            } catch (HibernateError e) {
                e.printStackTrace();
                tr.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getHiberSession().openSession()) {
            Transaction tr = session.getTransaction();
            try {
                session.beginTransaction();
                User user = session.get(User.class, id);
                if (user != null) {
                    session.delete(user);
                }
                session.getTransaction().commit();
        } catch (HibernateError e) {
                e.printStackTrace();
                tr.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try (Session session = Util.getHiberSession().openSession()) {
            Transaction tr = session.getTransaction();
            try {
                session.beginTransaction();
                list = session.createQuery("FROM User").list();
                session.getTransaction().commit();
            } catch (HibernateError e) {
                e.printStackTrace();
                tr.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getHiberSession().openSession()) {
            Transaction tr = session.getTransaction();
            try {
                session.beginTransaction();
                session.createQuery("DELETE FROM User").executeUpdate();
                session.getTransaction().commit();
            } catch (HibernateError e) {
                e.printStackTrace();
                tr.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
