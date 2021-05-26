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
    
    public Entrenador (String nombre, String cedula, int edad) {
        super(nombre, cedula, edad);
    }

    /**
     * @return the calificacion
     */
    public float getCalificacion() {
        return calificacion;
    }

    /**
     * @param calificacion the calificacion to set
     */
    public void setCalificacion(float calificacion) {
        this.calificacion=(this.calificacion+calificacion)/2;
    } 
    
}
