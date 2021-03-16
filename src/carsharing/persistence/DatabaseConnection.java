package carsharing.persistence;

import carsharing.logic.CommandLineArgs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection conn = null;
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    public static Connection getConnection(String databaseName) {
        if (conn != null) {
            return conn;
        }

        String url = String.format("jdbc:h2:./src/carsharing/db/%s", databaseName);

        try {
            conn = DriverManager.getConnection(url, USERNAME, PASSWORD);
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    public static Connection getConnection() {
        if (conn == null) {
            return getConnection(CommandLineArgs.DEFAULT_DB_FILENAME);
        }

        return conn;
    }

    public static void closeConnection() {
        if (conn == null) {
            return;
        }

        try {
            if (!conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        conn = null;
    }
}
