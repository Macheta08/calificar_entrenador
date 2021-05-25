/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calificarEntrenador;

import com.calificar_entrenador.modelo.Persona;

/**
 *
 * @author Usuario
 */
public class CalificarEntrenador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Persona persona = new Persona("Andres", "100", (byte)23);
        System.out.println(persona.toString());
    }
    
}
