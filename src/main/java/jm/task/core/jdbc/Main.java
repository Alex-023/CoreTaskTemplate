package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.createUsersTable(); //Delete
        userService.saveUser("Jon", "Week", (byte)31);
        userService.saveUser("Lex", "Luter", (byte)32);
        userService.saveUser("Bob", "Jonson", (byte)33);
        userService.saveUser("Jack", "Bucher", (byte)34);
        System.out.println(userService.getAllUsers());
        userService.dropUsersTable(); //Delete
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
