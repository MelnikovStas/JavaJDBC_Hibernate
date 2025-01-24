package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl extends Util implements UserDao {

    //Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        // SQL-запрос для создания таблицы
        String createSqlTable = "Create table if not exists users (" +
                "id BIGINT auto_increment primary key, " +
                "name varchar(45) not null, lastName varchar(45) not null, " +
                "age TINYINT not null)";

        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(createSqlTable)) {
            preparedStatement.executeUpdate();
            System.out.println("Table created");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void dropUsersTable() {
        final String sql = "DROP TABLE IF EXISTS users";
        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate(sql);
            System.out.println("Table dropped");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void saveUser(String name, String lastName, byte age) {
        if (name==null || lastName==null || age < 0 ) {
            throw new IllegalArgumentException("Input are required");
        }
        String sql = "INSERT INTO users (name,lastName,age) VALUES (?,?,?) ";
        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            User user = new User(name, lastName, age);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setByte(3, user.getAge());
            preparedStatement.executeUpdate();
            System.out.println("User с именем — " + user.getName() + " добавлен в базу данных");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        final String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User " + id + " removed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        List <User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Connection connection = Util.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setAge(rs.getByte("age"));
                user.setLastName(rs.getString("lastName"));
                user.setName(rs.getString("name"));
                user.setId(rs.getLong("id"));
                userList.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String sql = "DELETE FROM users"; // Быстро очищаем таблицу

        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
            System.out.println("table cleaned");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
