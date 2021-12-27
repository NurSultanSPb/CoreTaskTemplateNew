package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alex", "Oleksenko", (byte) 11);
        userService.saveUser("Sun", "Oleksenko", (byte) 21);
        userService.saveUser("Melo", "Oleksenko", (byte) 121);
        userService.saveUser("Pair", "Oleksenko", (byte) 1);
        userService.removeUserById(2);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
