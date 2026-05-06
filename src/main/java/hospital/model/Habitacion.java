/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospital.model;

/**
 *
 * @author Victus
 */
public class Habitacion {
    private int idHabitacion;
    private int numeroCama;
    private String idExpediente;
    private int idPiso;
    private int idArea;
    private int idEstado; // 1= Libre 2= Ocupada 3= Mantenimiento
    private String nombreArea;
    public Habitacion() {
        
    }
    
    public Habitacion(int idHabitacion, int numeroCama, String idExpediente, int idPiso, int idArea, int idEstado){
        this.idHabitacion = idHabitacion;
        this.numeroCama = numeroCama;
        this.idExpediente = idExpediente;
        this.idPiso = idPiso;
        this.idArea = idArea;
        this.idEstado = idEstado;
    }
    
    public int getIdHabitacion() {
        return idHabitacion; 
    }
    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion; 
    }

    public int getNumeroCama() { 
        return numeroCama; 
    }
    public void setNumeroCama(int numeroCama) {
        this.numeroCama = numeroCama; 
    }

    public String getIdExpediente() {
        return idExpediente; 
    }
    public void setIdExpediente(String idExpediente) { 
        this.idExpediente = idExpediente; 
    }

    public int getIdEstado() { 
        return idEstado; 
    }
    public void setIdEstado(int idEstado) { 
        this.idEstado = idEstado; 
    }
    
    public String getNombreArea() {
    return nombreArea;
}
    public void setNombreArea(String nombreArea) {
    this.nombreArea = nombreArea;
}
}
