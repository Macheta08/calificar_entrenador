/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calificar_entrenador.controlador;

import com.calificar_entrenador.modelo.Ciclista;
import com.calificar_entrenador.modelo.Entrenador;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class ControladorEntrenamiento {

    public static final int CANTIDAD_PERSONAS_GRUPO = 4;
    public static final int TIEMPO_JUNIOR = 6000;
    public static final int TIEMPO_PREJUVENIL = 5500;
    public static final int TIEMPO_INFANTIL = 4500;
    private ArrayList<Entrenador> entrenadores;
    private ArrayList<Ciclista> ciclistas;

    public ControladorEntrenamiento() {
        entrenadores = new ArrayList<>();
        ciclistas = new ArrayList<>();
        inicializarEntrenamiento();
    }

    public void inicializarEntrenamiento() {
        File archivo;
        FileReader fr = null;
        BufferedReader br;

        try {
            archivo = new File("personas.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            String nombre;
            String cedula;
            int edad;
            int tiempo;
            int contador = 0;
            Entrenador entrenador = null;

            String linea;
            String[] datos;
            while ((linea = br.readLine()) != null) {
                datos = linea.split(":");
                nombre = datos[0];
                cedula = datos[1];
                edad = Integer.parseInt(datos[2]);
                if (contador == 0) {
                    entrenador = new Entrenador(nombre, cedula, edad);
                    entrenadores.add(entrenador);
                } else {
                    tiempo = Integer.parseInt(datos[3]);
                    Ciclista ciclista = new Ciclista(nombre, cedula, edad, tiempo);
                    ciclista.setEntrenador(entrenador);
                    ciclistas.add(ciclista);
                }

                contador++;

                if (contador == CANTIDAD_PERSONAS_GRUPO) {
                    contador = 0;
                }

            }
        } catch (IOException e) {
            System.out.println("Error al cargar archivos");
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (IOException e2) {
                System.out.println("Error al cerrar el archivo");
            }
        }
    }

    public boolean validarCedula(int edad, String cedula) {
        if (edad > 18) {
            for (int i = 0; i < entrenadores.size(); i++) {
                if (entrenadores.get(i).getCedula().equals(cedula)) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < ciclistas.size(); i++) {
                if (ciclistas.get(i).getCedula().equals(cedula)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void calificarEntrenadores() {
        float calificacion;
        for (Ciclista ciclista : ciclistas) {
            if (ciclista.getEdad() >= 13 && ciclista.getEdad() < 15) {
                calificacion = (TIEMPO_INFANTIL / (float) ciclista.getTiempo());
                calificacion = calificacion * 100;
                ciclista.getEntrenador().setCalificacion(calificacion);
            } else if (ciclista.getEdad() >= 15 && ciclista.getEdad() < 17) {
                calificacion = (TIEMPO_PREJUVENIL / (float) ciclista.getTiempo());
                calificacion = calificacion * 100;
                ciclista.getEntrenador().setCalificacion(calificacion);
            } else {
                calificacion = (TIEMPO_JUNIOR / (float) ciclista.getTiempo()) * 100;
                ciclista.getEntrenador().setCalificacion(calificacion);
            }

        }
    }

    public boolean agregarEntrenador(String nombre, String cedula, int edad) {
        if (nombre.length() > 0 && cedula.length() > 0 && edad > 18) {
            Entrenador nuevoEnt = new Entrenador(nombre, cedula, edad);
            entrenadores.add(nuevoEnt);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se agreg?? el entrenador");
            return false;
        }
    }

    public boolean agregarCiclista(String nombre, String cedula, int edad, int tiempo) {
        if (nombre.length() > 0 && cedula.length() > 0 && edad > 12 && edad <= 18) {
            Ciclista nuevoCic = new Ciclista(nombre, cedula, edad, tiempo);
            ciclistas.add(nuevoCic);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "No se agreg?? el ciclista");
            return false;
        }
    }

    public int transformarTiempoSegundos(int horas, int minutos, int segundos) {
        int tiempo = 0;
        tiempo += (horas * 3600);
        tiempo += (minutos * 60);
        tiempo += segundos;

        return tiempo;
    }

    public void llenarFichero() {
        FileWriter fichero = null;
        PrintWriter pw = null;
        String nombreAgregar = "";
        String cedulaAgregar = "";
        int edadAgregar = 0;
        int tiempoAgregar = 0;
        try {
            fichero = new FileWriter("personas.txt", true);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < CANTIDAD_PERSONAS_GRUPO; i++) {
                if (i == 0) {
                    nombreAgregar = entrenadores.get(entrenadores.size() - 1).getNombre();
                    cedulaAgregar = entrenadores.get(entrenadores.size() - 1).getCedula();
                    edadAgregar = entrenadores.get(entrenadores.size() - 1).getEdad();
                    pw.println(nombreAgregar + ":" + cedulaAgregar + ":" + edadAgregar);
                } else {
                    nombreAgregar = ciclistas.get(ciclistas.size() - i).getNombre();
                    cedulaAgregar = ciclistas.get(ciclistas.size() - i).getCedula();
                    edadAgregar = ciclistas.get(ciclistas.size() - i).getEdad();
                    tiempoAgregar = ciclistas.get(ciclistas.size() - 1).getTiempo();
                    pw.println("\n" + nombreAgregar + ":" + cedulaAgregar + ":" + edadAgregar + ":" + tiempoAgregar);
                }
            }

        } catch (Exception e) {
            System.out.println("Error abriendo el archivo");
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                System.out.println("Error cerrando el archivo");
            }
        }

    }
    
    public Entrenador mejorEntrenador(){
        Entrenador mejorEntrenador=entrenadores.get(0);
        for (int i = 1; i < entrenadores.size(); i++) {
            if (entrenadores.get(i).getCalificacion()>entrenadores.get(i-1).getCalificacion()) {
                mejorEntrenador=entrenadores.get(i);
            }
        }
        return mejorEntrenador;
    }

    public ArrayList<Entrenador> getEntrenadores() {
        return entrenadores;
    }

    public void setEntrenadores(ArrayList<Entrenador> entrenadores) {
        this.entrenadores = entrenadores;
    }

    public ArrayList<Ciclista> getCiclistas() {
        return ciclistas;
    }

    public void setCiclistas(ArrayList<Ciclista> ciclistas) {
        this.ciclistas = ciclistas;
    }
}
