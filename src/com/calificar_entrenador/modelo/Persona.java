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
public class Persona implements Serializable{
    private String nombre;
    private String cedula;
    private byte edad;

    public Persona(String nombre, String cedula, byte edad) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
    }

    
    public String getNombre() {
        return nombre;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getCedula() {
        return cedula;
    }

    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    
    public byte getEdad() {
        return edad;
    }

    
    public void setEdad(byte edad) {
        this.edad = edad;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " Cedula: " + cedula + " Edad: " + edad;
    }
    
    
    
    
}

