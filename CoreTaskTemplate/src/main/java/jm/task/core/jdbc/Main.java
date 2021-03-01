package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Artour", "Babai", (byte) 23);
        userService.saveUser("222r", "Gridi", (byte) 25);
        userService.saveUser("Ardfdr", "Babai", (byte) 23);
        // userService.dropUsersTable();
        // userService.cleanUsersTable();
//        userService.removeUserById(3);
        userService.getAllUsers();
//        userService.dropUsersTable();
        userService.cleanUsersTable();
        userService.getAllUsers();
        userService.dropUsersTable();
    }
}
