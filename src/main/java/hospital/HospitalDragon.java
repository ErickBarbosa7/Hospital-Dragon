/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hospital;
import hospital.model.Conexion;
/**
 *
 * @author Victus
 */
public class HospitalDragon {
    public static void main(String[] args) {
        if (Conexion.getConexion() != null) { 
            System.out.println("Conectado");
        }
    }
}
