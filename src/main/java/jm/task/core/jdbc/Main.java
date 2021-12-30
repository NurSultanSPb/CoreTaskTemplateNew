package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Nursultan", "Temirkhanov", (byte) 11);
        userService.saveUser("Elena", "Guruleva", (byte) 21);
        userService.saveUser("Stich", "Elenin", (byte) 121);
        userService.saveUser("Lilo", "Nursultanovna", (byte) 1);
        for (User user: userService.getAllUsers()) {
            System.out.println("User с именем - " + user.getName() + " добавлен в базу данных");
        }
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
