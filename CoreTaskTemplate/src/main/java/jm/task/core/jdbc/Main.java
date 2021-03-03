package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Artour", "Babai", (byte) 23);
        userService.saveUser("222r", "Gridi", (byte) 25);
        userService.saveUser("Ardfdr", "Babai", (byte) 23);
        userService.getAllUsers();
//        userService.cleanUsersTable();
//        userService.dropUsersTable();

//        userService.saveUser("Arny", "Schwartz", (byte) 65);
//        userService.saveUser("Bob", "Schwartz", (byte) 13);
//        userService.saveUser("Tom", "Cruz", (byte) 25);
////        userService.removeUserById(3);

////        userService.dropUsersTable();
//        userService.cleanUsersTable();
////        userService.getAllUsers();
//        userService.dropUsersTable();
//        userService.createUsersTable();


    }
}
