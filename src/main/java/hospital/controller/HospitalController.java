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
        
        // Cargar los pisos de la BD al ComboBox
        configurarComboBoxPisos();
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
        // Limpiar los 16 botones para que no se queden con colores o datos del piso anterior
        for (javax.swing.JButton btn : vista.listaBotones) {
            btn.setBackground(Color.LIGHT_GRAY); 
            btn.setText(""); 
            btn.setEnabled(false); 

            // Quitar cualquier click anterior para que no se amontonen las acciones
            for (ActionListener al : btn.getActionListeners()) {
                btn.removeActionListener(al);
            }
        }

        // Inserta el nombre del piso en el label de arriba 
        String nombrePiso = vista.getComboPisos().getSelectedItem().toString();
        vista.getLabelTituloPiso().setText("<html>Mapa del <b>" + nombrePiso + "</b></html>");

        // Traer la lista de camas de la base de datos
        List<Habitacion> habitaciones = modelo.obtenerCamasPorPiso(idPiso);

        // Activa los botones que sí tienen una cama registrada en la BD
        for (int i = 0; i < habitaciones.size(); i++) {
            Habitacion h = habitaciones.get(i);
            javax.swing.JButton btn = vista.listaBotones[i];

            btn.setEnabled(true); // Activo el boton porque aquí sí hay una cama
         
            btn.setText("<html><center><font size='2'>" + h.getNombreArea().toUpperCase() + "</font><br>"
                    + "<b>C-" + h.getNumeroCama() + "</b></center></html>");

            // 1=Verde, 2=Rojo, 3=Amarillo
            switch (h.getIdEstado()) {
                case 1 -> btn.setBackground(new Color(46, 204, 113)); 
                case 2 -> btn.setBackground(new Color(231, 76, 60));  
                case 3 -> btn.setBackground(new Color(241, 196, 15)); 
                default -> btn.setBackground(Color.LIGHT_GRAY);
            }

            btn.addActionListener(e -> {
                if (h.getIdEstado() == 1) {
                    // Si esta libre abre la ventana de DialogoPaciente
                    String areaActual = h.getNombreArea();
                    DialogoPaciente diag = new DialogoPaciente(vista, true, h.getNumeroCama(), areaActual);
                    diag.setVisible(true);

                    // Si el usuario le dio a Aceptar y puso un expediente
                    if (diag.isAceptado()) {
                        String idExp = diag.getExpediente();
                        // Guarda en la BD y recargo el mapa
                        if (modelo.ocuparCama(h.getIdHabitacion(), idExp)) {
                            cargarPiso(idPiso); 
                        }
                    }
                } else if (h.getIdEstado() == 2) {
                    // Si ya esta ocupada solo avisa que paciente esta ahí
                    javax.swing.JOptionPane.showMessageDialog(vista, 
                        "Esta cama la tiene el paciente: " + h.getIdExpediente(), 
                        "Cama Ocupada", 1);
                }
            });
        }
    }
    
    private void configurarComboBoxPisos() {
    // Limpiamos los items 
    vista.getComboPisos().removeAllItems();
    
    // Traemos la lista de la base
    List<String> listaPisos = modelo.obtenerListaPisos();
    
    // Los agregamos al componente
    for (String nombrePiso : listaPisos) {
        vista.getComboPisos().addItem(nombrePiso);
    }
}
    
}