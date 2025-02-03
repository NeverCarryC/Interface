package database;

import java.sql.*;
import java.util.Vector;

public class Entrada {

 
    public static void agregarCita(String nombre, String apellido, String telefono, Date fecha) {
        Connection connection = null;
        try {
            connection = new DBConnection().getConnection();
            String query = "INSERT INTO cita (nombre, apellido, telefono, fecha) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellido);
            preparedStatement.setString(3, telefono);
            preparedStatement.setDate(4, fecha);
            preparedStatement.executeUpdate(); 
        } catch (SQLException e) {
            System.out.println("La query est¨¢ mal ejecutada");
            e.printStackTrace(); 
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();  
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public static Vector<Vector<Object>> getCitas() {
        Vector<Vector<Object>> citasData = new Vector<>();
        Connection connection = null;

        try {
            connection = new DBConnection().getConnection(); 
            String query = "SELECT * FROM cita";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);  

           
            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                row.add(resultSet.getInt("id"));
                row.add(resultSet.getString("nombre"));
                row.add(resultSet.getString("apellido"));
                row.add(resultSet.getString("telefono"));
                row.add(resultSet.getDate("fecha"));
                citasData.add(row);
            }

            resultSet.close(); 
            statement.close(); 
        } catch (SQLException e) {
            e.printStackTrace();  
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();  
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return citasData;
    }

    public static void main(String[] args) {
     
        String nombre = "Juan";
        String apellido = "P¨¦rez";
        String telefono = "123456789";
        Date fecha = Date.valueOf("2025-02-03");

     
        agregarCita(nombre, apellido, telefono, fecha);
        
    
        Vector<Vector<Object>> citas = getCitas();
        for (Vector<Object> row : citas) {
            System.out.println(row);
        }
    }
}
