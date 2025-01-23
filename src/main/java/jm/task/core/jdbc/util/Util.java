package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соединения с БД
    static final String url = "jdbc:mysql://localhost:3306/";
    static final String user = "root";
    static final String password = "root";
    public static Connection getConnection() {
        try ( Connection connection = DriverManager.getConnection(url,user,password)) {
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
