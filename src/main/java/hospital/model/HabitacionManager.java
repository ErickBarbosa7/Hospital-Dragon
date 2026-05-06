/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Victus
 */
public class HabitacionManager {
    /**
     * Obtiene las 16 habitaciones de un piso específico.
     */
    public List<Habitacion> obtenerCamasPorPiso(int idPiso) {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM tbl_habitaciones WHERE id_piso = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, idPiso);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Habitacion h = new Habitacion();
                h.setIdHabitacion(rs.getInt("id_habitacion"));
                h.setNumeroCama(rs.getInt("numero_cama"));
                h.setIdExpediente(rs.getString("id_expediente_paciente"));
                h.setIdEstado(rs.getInt("id_estado"));
                lista.add(h);
            }
        } catch (SQLException e) {
            System.err.println("Error al consultar camas: " + e.getMessage());
        }
        return lista;
    }

    /**
     * Actualiza el estado de una cama y asigna un paciente.
     */
    public boolean ocuparCama(int idHabitacion, String idExpediente) {
        // Estado 2 = Ocupada (Rojo) 
        String sql = "UPDATE tbl_habitaciones SET id_expediente_paciente = ?, id_estado = 2 WHERE id_habitacion = ?";

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, idExpediente);
            ps.setInt(2, idHabitacion);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al ocupar cama: " + e.getMessage());
            return false;
        }
    }
}
