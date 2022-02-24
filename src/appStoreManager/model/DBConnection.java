package appStoreManager.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final static String DBNAME = "appstoremanager";

    public Connection getMySqlConnection() {
        final String driver = "com.mysql.jdbc.Driver";
        final String dbUri = "jdbc:mysql://localhost:3306/" + DBNAME + "?autoReconnect=true&useSSL=false";
        final String username = "root";
        final String password = "Morimoto599?";
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(dbUri, username, password);
        } catch (ClassNotFoundException e) {
            new Exception(e.getMessage());
            System.out.println("Error " + e.getMessage());
        } catch (SQLException e) {
            new Exception(e.getMessage());
            System.out.println("Error " + e.getMessage());
        }
        return connection;
    }
}
