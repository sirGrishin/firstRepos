package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final String tableName = "users_hib";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {


        String createTable = "CREATE TABLE IF NOT EXISTS " + tableName
                + "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) NOT NULL, " +
                "lastName VARCHAR(45) NOT NULL, age MEDIUMINT NOT NULL)";
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(createTable).executeUpdate();
            transaction.commit();
            System.out.println("Создано в Hib");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {

        String dropTable = "drop table if exists " + tableName;
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(dropTable).executeUpdate();
            transaction.commit();
            System.out.println("Удалено в Hib");
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        if (!transaction.wasCommitted()) {
            transaction.commit();
        }
        System.out.println("User с именем – " + name + " добавлен в базу данных через Hibernate");

    }

    @Override
    public void removeUserById(long id) {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createQuery("DELETE User WHERE id = :id").setParameter("id", id).executeUpdate();
            transaction.commit();
            session.close();

        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println("Ошибка в remove Hibernate: " + exception);
        }
    }


    //    @Override
//    public List<User> getAllUsers() {
//        Session session = Util.getSessionFactory().openSession();
//        Query query = session.createQuery("From User");
//        List<User> list = (List<User>) query.list();
//        session.close();
//        return list;
//    }
    @Override
    public List<User> getAllUsers() {
        List<User> list = null;
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            list = session.createQuery("from User").list();
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Ошибка в getAllUsers: " + e);
            session.getTransaction().rollback();
        }
        return list;
    }


    @Override
    public void cleanUsersTable() {
        try {
            Session session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
