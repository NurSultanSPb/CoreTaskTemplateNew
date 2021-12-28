package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }


    public void createUsersTable() {

        try (Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()) {
            statement.executeUpdate("create table if not exists User (id int not null auto_increment, " +
                    "name char(100) not null, lastName char(100) not null, age smallint not null, primary key (id))");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void dropUsersTable() {

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("drop table if exists User");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void saveUser(String name, String lastName, byte age) {

        try (Connection connection = Util.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into User (name, lastName, age) values (?, ?, ?) ");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void removeUserById(long id) {

        try (Connection connection = Util.getConnection()) {
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery("select * from User");
            resultSet.absolute((int) id);
            resultSet.deleteRow();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public List<User> getAllUsers() {

        List<User> result = new ArrayList<>();
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from User")) {
            while (resultSet.next()) {
                result.add(new User(resultSet.getString("name"),
                        resultSet.getString("lastName"), resultSet.getByte("age")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }


    public void cleanUsersTable() {

        try (Connection connection = Util.getConnection();
        Statement statement = connection.createStatement()) {
            statement.executeUpdate("truncate table User");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
