package hospital.controller;

import hospital.model.*;
import hospital.view.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Victus
 */
public class HospitalController {
    private VentanaPrincipal vista;
    private HabitacionManager modelo;
    
    public HospitalController(VentanaPrincipal vista, HabitacionManager modelo){
        this.vista = vista;
        this.modelo = modelo;
        
        //cargar los datos iniciales, los de planta baja = piso 1
        cargarPiso(1);
        
        //Escuchar al combobox
        this.vista.getComboPisos().addActionListener(e -> {
            //opbtenemos el indice 0
            int pisoSeleccionado = vista.getComboPisos().getSelectedIndex() + 1;
            cargarPiso(pisoSeleccionado);
        });
    }
    
    private void cargarPiso(int idPiso) {

    // Determino el nombre del piso segun el id que llega
    String nombrePiso = (idPiso == 1) ? "Planta Baja" : "Piso 1";

    // Actualizo el titulo de arriba con una pequena instruccion para el usuario
    vista.getLabelTituloPiso().setText("<html>Mapa del " + nombrePiso + "</html>");

    // Traigo todas las camas (habitaciones) de ese piso desde el modelo
    List<Habitacion> habitaciones = modelo.obtenerCamasPorPiso(idPiso);

    // Recorro cada habitacion para asignarla a su boton correspondiente
    for (int i = 0; i < habitaciones.size(); i++) {

        Habitacion h = habitaciones.get(i);
        javax.swing.JButton btn = vista.listaBotones[i];

        // Le pongo el numero de cama al boton (centrado y con formato)
        btn.setText("<html><center>Cama<br><b>" + h.getNumeroCama() + "</b></center></html>");

        // Cambio el color dependiendo del estado de la cama
        switch (h.getIdEstado()) {
            case 1 -> btn.setBackground(new Color(46, 204, 113)); // Libre (verde)
            case 2 -> btn.setBackground(new Color(231, 76, 60));  // Ocupada (rojo)
            case 3 -> btn.setBackground(new Color(241, 196, 15)); // Mantenimiento (amarillo)
            default -> btn.setBackground(Color.LIGHT_GRAY);       // Por si algo raro pasa
        }

        // Limpio listeners anteriores para evitar que se acumulen
        for (ActionListener al : btn.getActionListeners()) {
            btn.removeActionListener(al);
        }

        // Le asigno la accion al boton segun el estado de la cama
        btn.addActionListener(e -> {

            // Si esta libre, permito asignar paciente
            if (h.getIdEstado() == 1) {

                String idExp = javax.swing.JOptionPane.showInputDialog(vista, "Ingrese ID Expediente Paciente:");

                // Valido que si haya escrito algo
                if (idExp != null && !idExp.trim().isEmpty()) {

                    // Intento ocupar la cama en el modelo
                    if (modelo.ocuparCama(h.getIdHabitacion(), idExp)) {
                        // Recargo la vista para reflejar el cambio
                        cargarPiso(idPiso); 
                    }
                }

            // Si ya esta ocupada, solo muestro quien la tiene
            } else if (h.getIdEstado() == 2) {
                javax.swing.JOptionPane.showMessageDialog(vista, "Cama ocupada por: " + h.getIdExpediente());
                }
            });
        }
    }
}