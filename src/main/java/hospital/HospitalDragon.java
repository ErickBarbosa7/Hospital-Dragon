/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package hospital;
import hospital.controller.HospitalController;
import hospital.model.Conexion;
import hospital.model.HabitacionManager;
import hospital.view.VentanaPrincipal;
/**
 *
 * @author Victus
 */
public class HospitalDragon {
    public static void main(String[] args) {
    try {
        if (Conexion.getConexion() != null) { 
            System.out.println("Conectado a la BD");
        }
        
        VentanaPrincipal vista = new VentanaPrincipal();
        HabitacionManager modelo = new HabitacionManager();
        
        // Aquí es donde probablemente truena
        HospitalController controlador = new HospitalController(vista, modelo);
    
        vista.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace(); 
    }
}
}
