/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calificar_entrenador.controlador;

import com.calificar_entrenador.excepciones.UsuarioExcepcion;
import com.calificar_entrenador.modelo.Rol;
import com.calificar_entrenador.modelo.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author carloaiza
 */
public class ControladorUsuario {

    private Rol[] roles;
    private List<Usuario> usuarios;

    public ControladorUsuario() {
        inicializarColecciones();
    }

    private void inicializarColecciones() {
        roles = new Rol[2];
        roles[0] = new Rol((byte) 1, "Administrador");
        roles[1] = new Rol((byte) 2, "Consulta");

        usuarios = new ArrayList<>();
        cargarDatosUsuarios();
    }

    private void cargarDatosUsuarios() {
        File archivo;
        FileReader fr = null;
        BufferedReader br;

        try {
            archivo = new File("usuarios.txt");
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            String[] datos;
            while ((linea = br.readLine()) != null) {
                datos = linea.split(":");
                if (validarCorreo(datos[0]) && datos.length == 4) {
                    if (datos[2].equals("1")) {
                        getUsuarios().add(new Usuario(datos[0], datos[1], roles[0], datos[3]));
                    } else if (datos[2].equals("2")) {
                        getUsuarios().add(new Usuario(datos[0], datos[1], roles[1], datos[3]));
                    }
                }
                
            }
//            System.out.println("Tamaño: "+usuarios.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public Usuario verificarUsuario(String correo, String contrasenia)
            throws UsuarioExcepcion {
        if (correo == null || correo.equals("") || contrasenia == null
                || contrasenia.equals("")) {
            throw new UsuarioExcepcion("Debe diligenciar todos los datos");
        }

        if (!validarCorreo(correo)) {
            throw new UsuarioExcepcion("En el campo usuario debe ingrear un "
                    + "correo válido");
        }

        if (contrasenia.length() < 6) {
            throw new UsuarioExcepcion("La contraseña debe tener al menos "
                    + "6 caracteres");
        }

        for (Usuario usu : getUsuarios()) {
            if (usu.getCorreo().equals(correo)
                    && usu.getContrasenia().equals(contrasenia)) {
                return usu;
            }
        }

        throw new UsuarioExcepcion("Los datos ingresados son incorrectos");

    }

    private boolean validarCorreo(String email) {
        boolean espacios = true;

        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(email);

        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == ' ') {
                espacios = false;
                break;
            }
        }

        return matcher.find() && espacios;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        return usuarios;
    }

}
