package az.edu.turing.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(
                    System.getenv("DB_URL"),
                    System.getenv("DB_USER"),
                    System.getenv("DB_PASSWORD")
                    );
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to connect to database", e);
        }
    }
}
