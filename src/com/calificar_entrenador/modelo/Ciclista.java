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
public class Ciclista extends Persona implements Serializable{
    
    private double tiempo;

    public Ciclista(double tiempo , String nombre, String cedula, byte edad) {
        super(nombre, cedula, edad);
        this.tiempo = tiempo;
    }

    
    public double getTiempo() {
        return tiempo;
    }

    
    public void setTiempo(double tiempo) {
        this.tiempo = tiempo;
    }
    
    
}
