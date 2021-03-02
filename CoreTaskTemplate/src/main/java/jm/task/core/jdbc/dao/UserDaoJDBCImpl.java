package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String tableName = "users";
    Connection connection;
    public UserDaoJDBCImpl() {
        connection = Util.connect();
    }

    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS " + tableName
                + "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) NOT NULL, " +
                "lastName VARCHAR(45) NOT NULL, age MEDIUMINT NOT NULL)";
        try {
            connection.createStatement().execute(createTable);
            System.out.println("Created");
        } catch (Exception e) {
            System.err.println("Not created");
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String dropTable = "drop table if exists " + tableName;
        try {
            connection.createStatement().execute(dropTable);
            System.out.println("Deleted");
        } catch (Exception e) {
            System.err.println("Not deleted");
            e.printStackTrace();
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем –" + name + " добавлен в базу данных");
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            System.err.println("User not added");
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User removed");
            connection.commit();
            connection.setAutoCommit(true);
        } catch (Exception e) {
            System.err.println("User not removed");
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String getUser = "select * from " + tableName;
        List<User> list = new ArrayList();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery(getUser);
            while (resultSet.next()) {
                long id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                User user = new User(id, name, lastName, age);
                list.add(user);
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String cleanUsers = "truncate table " + tableName;
        try {
            connection.createStatement().execute(cleanUsers);
            System.out.println("Cleaned");
        } catch (Exception e) {
            System.err.println("Not cleaned");
            e.printStackTrace();
        }
    }
}



