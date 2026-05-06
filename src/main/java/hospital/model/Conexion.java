/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Victus
 */
public class Conexion {
    // Datos de conexión coincidentes con tu docker-compose.yml
    private static final String URL = "jdbc:mysql://localhost:3306/hospital_dragon";
    private static final String USER = "user_dragon";
    private static final String PASS = "password123";

    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("✅ Conexión establecida con Hospital Dragón en Docker.");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL");
        } catch (SQLException e) {
            System.err.println("Error de SQL: " + e.getMessage());
        }
        return con;
    }
}
