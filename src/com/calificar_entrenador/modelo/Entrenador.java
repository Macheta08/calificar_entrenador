/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calificar_entrenador.modelo;

import java.io.Serializable;

/**
 *
 * @author Usuario
 */
public class Entrenador extends Persona implements Serializable{
    
    private float calificacion;
    
    public Entrenador (String nombre, String cedula, byte edad) {
        super(nombre, cedula, edad);
    }
    
    
    
}
