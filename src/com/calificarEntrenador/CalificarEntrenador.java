/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calificarEntrenador;

import com.calificar_entrenador.controlador.ControladorEntrenamiento;
import com.calificar_entrenador.controlador.ControladorUsuario;

/**
 *
 * @author Usuario
 */
public class CalificarEntrenador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ControladorEntrenamiento controlEntrenamiento = new ControladorEntrenamiento();
        ControladorUsuario controlUsuario = new ControladorUsuario();

        for (int i = 0; i < controlUsuario.getUsuarios().size(); i++) {
            System.out.println(controlUsuario.getUsuarios().get(i).toString());
        }

    }

}
