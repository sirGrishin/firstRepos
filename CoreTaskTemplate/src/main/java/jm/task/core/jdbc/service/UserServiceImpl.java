package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    private String tableName = "users";
    Connection connection = null;
    Statement statement = null;

    public void createUsersTable() {
        String createTable = "CREATE TABLE IF NOT EXISTS " + tableName
                + "(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(45) NOT NULL, " +
                "lastName VARCHAR(45) NOT NULL, age MEDIUMINT NOT NULL)";
        try {
            connection = Util.connect();
            statement = connection.createStatement();
            statement.execute(createTable);
            System.out.println("Created");
        } catch (Exception e) {
            System.err.println("Not created");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null & statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }


    }

    public void dropUsersTable() {
        String dropTable = "drop table if exists " + tableName;
        try {
            connection = Util.connect();
            statement = connection.createStatement();
            statement.execute(dropTable);
            System.out.println("Deleted");
        } catch (Exception e) {
            System.err.println("Not deleted");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null & statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    public void saveUser(String name, String lastName, byte age) {
        String userAdd = "INSERT INTO " + tableName + " (name,lastname,age) VALUES ('" + name + "', '" + lastName + "', '" + age + "')";

        try {
            connection = Util.connect();
            statement = connection.createStatement();
            statement.execute(userAdd);
            System.out.println("User с именем –" + name + " добавлен в базу данных");
        } catch (Exception e) {
            System.err.println("User not added");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null & statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        String removeUser = "DELETE FROM " + tableName + " WHERE id =" + id;
        try {
            connection = Util.connect();
            statement = connection.createStatement();
            statement.execute(removeUser);
            System.out.println("User removed");
        } catch (Exception e) {
            System.err.println("User not removed");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null & statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    public List<User> getAllUsers() {
        String getUser = "select * from " + tableName;
        List<User> list = new ArrayList();
        try {
            connection = Util.connect();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getUser);
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
        } finally {
            try {
                if (connection != null & statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return list;
    }

    public void cleanUsersTable() {
        String cleanUsers = "truncate table " + tableName;
        try {
            connection = Util.connect();
            statement = connection.createStatement();
            statement.execute(cleanUsers);
            System.out.println("Cleaned");
        } catch (Exception e) {
            System.err.println("Not cleaned");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null & statement != null) {
                    connection.close();
                    statement.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }
}
