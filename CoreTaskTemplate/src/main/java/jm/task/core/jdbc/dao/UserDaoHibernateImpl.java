package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String tableName = "users_hib";
    SessionFactory sessionFactory = Util.getSessionFactory();

    Transaction transaction = null;

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {


        String createTable = "CREATE TABLE IF NOT EXISTS " + tableName
                + "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) NOT NULL, " +
                "lastName VARCHAR(45) NOT NULL, age MEDIUMINT NOT NULL)";
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
            transaction.commit();
            System.out.println("Создано в Hib");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        String dropTable = "drop table if exists " + tableName;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery(dropTable).executeUpdate();
            transaction.commit();
            System.out.println("Удалено в Hib");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных через Hibernate");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createQuery("DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
                System.out.println("Ошибка в remove Hibernate: " + e);
            }
            e.printStackTrace();
        }
    }



    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            list = session.createQuery("from User").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                System.out.println("Ошибка в getAllUsers: " + e);
            }
        }
        return list;
    }


    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()){
            transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}

