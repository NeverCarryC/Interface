package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {


    public Connection getConnection() throws SQLException {
      
        String url = "jdbc:mysql://127.0.0.1/cita";
        String username = "root";
        String password = "";

       
        return DriverManager.getConnection(url, username, password);
    }


    public void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
