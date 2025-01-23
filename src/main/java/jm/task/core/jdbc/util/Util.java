package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    final String url = "jdbc:mysql://localhost:3306/";
    final String user = "root";
    final String password = "root";
    public Connection getConnection() {
        try ( Connection connection = DriverManager.getConnection(url,user,password)) {
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
