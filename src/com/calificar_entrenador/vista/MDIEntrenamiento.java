/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.calificar_entrenador.vista;

import com.calificar_entrenador.controlador.ControladorEntrenamiento;
import com.calificar_entrenador.controlador.ControladorUsuario;
import com.calificar_entrenador.excepciones.UsuarioExcepcion;
import com.calificar_entrenador.modelo.Ciclista;
import com.calificar_entrenador.modelo.Entrenador;
import com.calificar_entrenador.modelo.Persona;
import com.calificar_entrenador.modelo.Usuario;
import java.awt.Container;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Usuario
 */
public class MDIEntrenamiento extends javax.swing.JFrame {

    private final ControladorUsuario controlUsuarios;
    private Usuario usuarioAutenticado;
    private final ControladorEntrenamiento controlEntrenamiento;
    private int ciclistasAgregados;

    public MDIEntrenamiento() {
        initComponents();
        controlarEstadoMenus();
        controlUsuarios = new ControladorUsuario();
        controlEntrenamiento = new ControladorEntrenamiento();
        txtCorreo.setText("andres@umanizales.edu.co");
        txtContrasenia.setText("123456");
        eliminarFlecha();
        llenarEntrenadores();
        llenarCiclistas();
    }

    private void controlarEstadoMenus() {
        mnuListarEntrenadores.setEnabled(false);
        mnuAñadirGrupo.setEnabled(false);
        mnuMejorEntrenador.setEnabled(false);
        mnuInsertar.setEnabled(false);
        mnuCiclistas.setEnabled(false);

        mnuListarEntrenadores.setVisible(false);
        mnuAñadirGrupo.setEnabled(false);
        mnuMejorEntrenador.setEnabled(false);
        mnuInsertar.setVisible(false);
        mnuCiclistas.setVisible(false);

        if (usuarioAutenticado != null) {
            switch (usuarioAutenticado.getRol().getCodigo()) {
                case 1:
                    mnuListarEntrenadores.setEnabled(true);
                    mnuAñadirGrupo.setEnabled(true);
                    mnuMejorEntrenador.setEnabled(true);
                    mnuInsertar.setEnabled(true);
                    mnuCiclistas.setEnabled(true);

                    mnuListarEntrenadores.setVisible(true);
                    mnuAñadirGrupo.setVisible(true);
                    mnuMejorEntrenador.setVisible(true);
                    mnuInsertar.setVisible(true);
                    mnuCiclistas.setVisible(true);
                    break;
                case 2:
                    mnuListarEntrenadores.setEnabled(true);
                    mnuListarEntrenadores.setVisible(true);
                    mnuCiclistas.setEnabled(true);
                    mnuCiclistas.setVisible(true);
                    break;
            }
        }

    }

    private void eliminarFlecha() {
        jifIngresar.setFrameIcon(null);
        BasicInternalFrameUI ui = (BasicInternalFrameUI) jifIngresar.getUI();
        Container north = (Container) ui.getNorthPane();
        north.remove(0);
        north.validate();
        north.repaint();
    }

    private void llenarEntrenadores() {

        DefaultTableModel model = (DefaultTableModel) tblEntrenadores.getModel();
        model.getDataVector().removeAllElements();

        for (Entrenador ent : controlEntrenamiento.getEntrenadores()) {
            if (ent != null) {
                Object[] fila = {ent.getNombre(), ent.getCedula(),
                    ent.getEdad()};
                model.addRow(fila);
            }
        }

        tblEntrenadores.repaint();

    }

    private void llenarCiclistas() {

        DefaultTableModel model = (DefaultTableModel) tblCiclistas.getModel();
        model.getDataVector().removeAllElements();

        for (Ciclista cic : controlEntrenamiento.getCiclistas()) {
            if (cic != null) {
                Object[] fila = {cic.getNombre(), cic.getCedula(),
                    cic.getEdad(), cic.getTiempo()};
                model.addRow(fila);
            }
        }

        tblEntrenadores.repaint();

    }

    public void bloquearMenus() {
        mnuArchivo.setEnabled(false);
        mnuInsertar.setEnabled(false);
    }

    public void desbloquearMenus() {
        mnuArchivo.setEnabled(true);
        mnuInsertar.setEnabled(true);
    }

    public void diagramaCategorias() {
        int cantidadCiclistasJunior=0;
        int cantidadCiclistasPrejuvenil=0;
        int cantidadCiclistasInfantil=0;
        
        for (int i = 0; i < controlEntrenamiento.getCiclistas().size(); i++) {
            if (controlEntrenamiento.getCiclistas().get(i).getEdad() >= 13 && controlEntrenamiento.getCiclistas().get(i).getEdad() < 15) {
                cantidadCiclistasInfantil++;
            } else if (controlEntrenamiento.getCiclistas().get(i).getEdad() >= 15 && controlEntrenamiento.getCiclistas().get(i).getEdad() < 17) {
                cantidadCiclistasPrejuvenil++;
            } else {
                cantidadCiclistasJunior++;
            }
        }
        
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Infantil", new Integer(cantidadCiclistasInfantil));
        dataset.setValue("Prejuvenil", new Integer(cantidadCiclistasPrejuvenil));
        dataset.setValue("Junior", new Integer(cantidadCiclistasJunior));
        
        JFreeChart diagramaCat = ChartFactory.createPieChart("Categorías Ciclismo", dataset, true, true, false);
        
        ChartPanel panelDiagrama = new ChartPanel(diagramaCat);
        
        JFrame jfDiagrama = new JFrame("");
        jfDiagrama.setSize(800, 600);
        jfDiagrama.setVisible(true);
        jfDiagrama.add(panelDiagrama);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        jifIngresar = new javax.swing.JInternalFrame();
        lblCorreo = new javax.swing.JLabel();
        lblContrasenia = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtContrasenia = new javax.swing.JPasswordField();
        btnIngresar = new javax.swing.JButton();
        jifListarEntrenadores = new javax.swing.JInternalFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEntrenadores = new javax.swing.JTable();
        jifListarCiclistas = new javax.swing.JInternalFrame();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCiclistas = new javax.swing.JTable();
        jifAgregarEntrenador = new javax.swing.JInternalFrame();
        lblNombreEntrenador = new javax.swing.JLabel();
        txtNombreEntrenador = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        lblEdad = new javax.swing.JLabel();
        spnEdad = new javax.swing.JSpinner();
        btnAgregar = new javax.swing.JButton();
        jifAgregarCiclista = new javax.swing.JInternalFrame();
        lblNombreCiclista = new javax.swing.JLabel();
        txtNombreCiclista = new javax.swing.JTextField();
        lblDocumentoId = new javax.swing.JLabel();
        txtDocumentoId = new javax.swing.JTextField();
        lblEdadCic = new javax.swing.JLabel();
        spnEdadCic = new javax.swing.JSpinner();
        btnAgregarCiclista = new javax.swing.JButton();
        lblTiempo = new javax.swing.JLabel();
        spnHoras = new javax.swing.JSpinner();
        spnMinutos = new javax.swing.JSpinner();
        spnSegundos = new javax.swing.JSpinner();
        lblHrs = new javax.swing.JLabel();
        lblMin = new javax.swing.JLabel();
        lblSeg = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        mnuArchivo = new javax.swing.JMenu();
        mnuListarEntrenadores = new javax.swing.JMenuItem();
        mnuCiclistas = new javax.swing.JMenuItem();
        mnuAñadirGrupo = new javax.swing.JMenuItem();
        mnuMejorEntrenador = new javax.swing.JMenuItem();
        mnuSalir = new javax.swing.JMenuItem();
        mnuInsertar = new javax.swing.JMenu();
        mnuCategorias = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1800, 1020));

        jifIngresar.setTitle("Ingresar");
        jifIngresar.setVisible(true);

        lblCorreo.setText("Correo");

        lblContrasenia.setText("Contraseña");

        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jifIngresarLayout = new javax.swing.GroupLayout(jifIngresar.getContentPane());
        jifIngresar.getContentPane().setLayout(jifIngresarLayout);
        jifIngresarLayout.setHorizontalGroup(
            jifIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jifIngresarLayout.createSequentialGroup()
                .addContainerGap(143, Short.MAX_VALUE)
                .addComponent(btnIngresar)
                .addGap(130, 130, 130))
            .addGroup(jifIngresarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jifIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblContrasenia)
                    .addComponent(lblCorreo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jifIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                    .addComponent(txtContrasenia))
                .addContainerGap())
        );
        jifIngresarLayout.setVerticalGroup(
            jifIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifIngresarLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jifIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCorreo)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jifIngresarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContrasenia)
                    .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnIngresar)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        desktopPane.add(jifIngresar);
        jifIngresar.setBounds(610, 250, 370, 190);

        jifListarEntrenadores.setClosable(true);
        jifListarEntrenadores.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        jifListarEntrenadores.setIconifiable(true);
        jifListarEntrenadores.setMaximizable(true);
        jifListarEntrenadores.setResizable(true);
        jifListarEntrenadores.setTitle("Listado Entrenadores");
        jifListarEntrenadores.setVisible(false);

        tblEntrenadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cédula", "Edad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Byte.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblEntrenadores);
        if (tblEntrenadores.getColumnModel().getColumnCount() > 0) {
            tblEntrenadores.getColumnModel().getColumn(0).setResizable(false);
            tblEntrenadores.getColumnModel().getColumn(1).setResizable(false);
            tblEntrenadores.getColumnModel().getColumn(2).setResizable(false);
            tblEntrenadores.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        javax.swing.GroupLayout jifListarEntrenadoresLayout = new javax.swing.GroupLayout(jifListarEntrenadores.getContentPane());
        jifListarEntrenadores.getContentPane().setLayout(jifListarEntrenadoresLayout);
        jifListarEntrenadoresLayout.setHorizontalGroup(
            jifListarEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifListarEntrenadoresLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jifListarEntrenadoresLayout.setVerticalGroup(
            jifListarEntrenadoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifListarEntrenadoresLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        desktopPane.add(jifListarEntrenadores);
        jifListarEntrenadores.setBounds(580, 150, 800, 600);

        jifListarCiclistas.setClosable(true);
        jifListarCiclistas.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        jifListarCiclistas.setIconifiable(true);
        jifListarCiclistas.setMaximizable(true);
        jifListarCiclistas.setResizable(true);
        jifListarCiclistas.setVisible(false);

        tblCiclistas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Nombre", "Documento", "Edad", "Tiempo (s)"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Byte.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblCiclistas);
        if (tblCiclistas.getColumnModel().getColumnCount() > 0) {
            tblCiclistas.getColumnModel().getColumn(0).setResizable(false);
            tblCiclistas.getColumnModel().getColumn(1).setResizable(false);
            tblCiclistas.getColumnModel().getColumn(2).setResizable(false);
            tblCiclistas.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout jifListarCiclistasLayout = new javax.swing.GroupLayout(jifListarCiclistas.getContentPane());
        jifListarCiclistas.getContentPane().setLayout(jifListarCiclistasLayout);
        jifListarCiclistasLayout.setHorizontalGroup(
            jifListarCiclistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifListarCiclistasLayout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(106, Short.MAX_VALUE))
        );
        jifListarCiclistasLayout.setVerticalGroup(
            jifListarCiclistasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jifListarCiclistasLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        desktopPane.add(jifListarCiclistas);
        jifListarCiclistas.setBounds(140, 40, 650, 440);

        jifAgregarEntrenador.setClosable(true);
        jifAgregarEntrenador.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        jifAgregarEntrenador.setIconifiable(true);
        jifAgregarEntrenador.setMaximizable(true);
        jifAgregarEntrenador.setTitle("Agregar Entrenador");
        jifAgregarEntrenador.setToolTipText("");
        jifAgregarEntrenador.setMinimumSize(new java.awt.Dimension(20, 20));
        jifAgregarEntrenador.setVisible(false);

        lblNombreEntrenador.setText("Nombre Entrenador");

        lblCedula.setText("Cédula");

        lblEdad.setText("Edad");

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jifAgregarEntrenadorLayout = new javax.swing.GroupLayout(jifAgregarEntrenador.getContentPane());
        jifAgregarEntrenador.getContentPane().setLayout(jifAgregarEntrenadorLayout);
        jifAgregarEntrenadorLayout.setHorizontalGroup(
            jifAgregarEntrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifAgregarEntrenadorLayout.createSequentialGroup()
                .addGroup(jifAgregarEntrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jifAgregarEntrenadorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jifAgregarEntrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblNombreEntrenador)
                            .addComponent(lblCedula)
                            .addComponent(lblEdad))
                        .addGap(31, 31, 31)
                        .addGroup(jifAgregarEntrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNombreEntrenador)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(spnEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jifAgregarEntrenadorLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(btnAgregar)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jifAgregarEntrenadorLayout.setVerticalGroup(
            jifAgregarEntrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifAgregarEntrenadorLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jifAgregarEntrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreEntrenador)
                    .addComponent(lblNombreEntrenador, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jifAgregarEntrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jifAgregarEntrenadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spnEdad)
                    .addComponent(lblEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnAgregar)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        desktopPane.add(jifAgregarEntrenador);
        jifAgregarEntrenador.setBounds(380, 110, 300, 280);

        jifAgregarCiclista.setClosable(true);
        jifAgregarCiclista.setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
        jifAgregarCiclista.setIconifiable(true);
        jifAgregarCiclista.setMaximizable(true);
        jifAgregarCiclista.setTitle("Agregar Ciclista");
        jifAgregarCiclista.setToolTipText("");
        jifAgregarCiclista.setMinimumSize(new java.awt.Dimension(20, 20));
        jifAgregarCiclista.setVisible(false);

        lblNombreCiclista.setText("Nombre Ciclista");

        lblDocumentoId.setText("Documento de Id.");

        lblEdadCic.setText("Edad");

        btnAgregarCiclista.setText("Agregar");
        btnAgregarCiclista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCiclistaActionPerformed(evt);
            }
        });

        lblTiempo.setText("Tiempo");

        lblHrs.setText("Hrs");

        lblMin.setText("Min");

        lblSeg.setText("Seg");

        javax.swing.GroupLayout jifAgregarCiclistaLayout = new javax.swing.GroupLayout(jifAgregarCiclista.getContentPane());
        jifAgregarCiclista.getContentPane().setLayout(jifAgregarCiclistaLayout);
        jifAgregarCiclistaLayout.setHorizontalGroup(
            jifAgregarCiclistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifAgregarCiclistaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jifAgregarCiclistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombreCiclista)
                    .addComponent(lblDocumentoId)
                    .addComponent(lblEdadCic)
                    .addComponent(lblTiempo))
                .addGap(31, 31, 31)
                .addGroup(jifAgregarCiclistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregarCiclista)
                    .addComponent(spnEdadCic, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jifAgregarCiclistaLayout.createSequentialGroup()
                        .addComponent(spnHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHrs)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spnSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSeg))
                    .addComponent(txtNombreCiclista)
                    .addComponent(txtDocumentoId))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jifAgregarCiclistaLayout.setVerticalGroup(
            jifAgregarCiclistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jifAgregarCiclistaLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jifAgregarCiclistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreCiclista)
                    .addComponent(lblNombreCiclista, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jifAgregarCiclistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtDocumentoId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDocumentoId, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jifAgregarCiclistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(spnEdadCic)
                    .addComponent(lblEdadCic, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jifAgregarCiclistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHrs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jifAgregarCiclistaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTiempo)
                        .addComponent(spnHoras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnSegundos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblMin, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(spnMinutos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblSeg, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(btnAgregarCiclista)
                .addGap(39, 39, 39))
        );

        desktopPane.add(jifAgregarCiclista);
        jifAgregarCiclista.setBounds(720, 110, 400, 280);

        mnuArchivo.setMnemonic('f');
        mnuArchivo.setText("Archivo");

        mnuListarEntrenadores.setMnemonic('o');
        mnuListarEntrenadores.setText("Listar Entrenadores");
        mnuListarEntrenadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuListarEntrenadoresActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuListarEntrenadores);

        mnuCiclistas.setText("Listar Ciclistas");
        mnuCiclistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCiclistasActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuCiclistas);

        mnuAñadirGrupo.setMnemonic('s');
        mnuAñadirGrupo.setText("Añadir Grupo");
        mnuAñadirGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuAñadirGrupoActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuAñadirGrupo);

        mnuMejorEntrenador.setMnemonic('a');
        mnuMejorEntrenador.setText("Mejor entrenador");
        mnuMejorEntrenador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuMejorEntrenadorActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuMejorEntrenador);

        mnuSalir.setMnemonic('x');
        mnuSalir.setText("Salir");
        mnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuSalirActionPerformed(evt);
            }
        });
        mnuArchivo.add(mnuSalir);

        menuBar.add(mnuArchivo);

        mnuInsertar.setMnemonic('e');
        mnuInsertar.setText("Insertar");

        mnuCategorias.setMnemonic('t');
        mnuCategorias.setText("Diagrama categorías");
        mnuCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnuCategoriasActionPerformed(evt);
            }
        });
        mnuInsertar.add(mnuCategorias);

        menuBar.add(mnuInsertar);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1920, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1080, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuSalirActionPerformed
        JOptionPane.showMessageDialog(rootPane, "¡Hasta pronto!");
        System.exit(0);
    }//GEN-LAST:event_mnuSalirActionPerformed

    private void mnuListarEntrenadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuListarEntrenadoresActionPerformed
        if (jifListarEntrenadores.isIcon()) {
            try {
                jifListarEntrenadores.setIcon(false);
            } catch (PropertyVetoException ex) {
                System.out.println("No se pudo restaurar el formulario listar entrenadores");
            }
        }

        jifListarEntrenadores.show();
    }//GEN-LAST:event_mnuListarEntrenadoresActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        try {
            usuarioAutenticado = controlUsuarios.verificarUsuario(txtCorreo.getText(), txtContrasenia.getText());
            controlarEstadoMenus();
            jifIngresar.hide();

            JOptionPane.showMessageDialog(rootPane, "Bienvenido "
                    + usuarioAutenticado.getNombre(), "Ingreso Existoso", 1);
        } catch (UsuarioExcepcion ex) {
            JOptionPane.showMessageDialog(rootPane, ex.getMessage(), "Error", 0);
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void mnuAñadirGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuAñadirGrupoActionPerformed
        if (jifAgregarEntrenador.isIcon()) {
            try {
                jifAgregarEntrenador.setIcon(false);
            } catch (PropertyVetoException ex) {
                System.out.println("No se pudo restaurar el formulario agregar grupo");
            }
        }
        jifAgregarEntrenador.show();
    }//GEN-LAST:event_mnuAñadirGrupoActionPerformed

    private void mnuCiclistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCiclistasActionPerformed
        if (jifListarCiclistas.isIcon()) {
            try {
                jifListarCiclistas.setIcon(false);
            } catch (PropertyVetoException ex) {
                System.out.println("No se pudo restaurar el formulario listar ciclistas");
            }
        }

        jifListarCiclistas.show();
    }//GEN-LAST:event_mnuCiclistasActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed

        if (controlEntrenamiento.agregarEntrenador(txtNombreEntrenador.getText(), txtCedula.getText(), (int) spnEdad.getValue()));
        {
            if (jifAgregarCiclista.isIcon()) {
                try {
                    jifAgregarCiclista.setIcon(false);
                } catch (PropertyVetoException ex) {
                    System.out.println("No se pudo restaurar el formulario listar ciclistas");
                }
            }
            jifAgregarEntrenador.doDefaultCloseAction();
            bloquearMenus();
            jifAgregarCiclista.show();
        }

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnAgregarCiclistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCiclistaActionPerformed
        ciclistasAgregados++;
        controlEntrenamiento.agregarCiclista(txtNombreCiclista.getText(), txtDocumentoId.getText(), (int) spnEdadCic.getValue(),
                controlEntrenamiento.transformarTiempoSegundos((int) spnHoras.getValue(), (int) spnMinutos.getValue(), (int) spnSegundos.getValue()));
        if (ciclistasAgregados < 3) {
            JOptionPane.showMessageDialog(null, "Ingresado ciclista #" + (ciclistasAgregados));

        } else {
            JOptionPane.showMessageDialog(null, "Ingresado ciclista #" + (ciclistasAgregados));
            desbloquearMenus();
            llenarCiclistas();
            llenarEntrenadores();
            jifAgregarCiclista.doDefaultCloseAction();
            controlEntrenamiento.llenarFichero();
        }
    }//GEN-LAST:event_btnAgregarCiclistaActionPerformed

    private void mnuMejorEntrenadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuMejorEntrenadorActionPerformed
        Entrenador mejorEntrenador;
        controlEntrenamiento.calificarEntrenadores();
        mejorEntrenador = controlEntrenamiento.mejorEntrenador();
        JOptionPane.showMessageDialog(null, "El Mejor entrenador del plantel es: " + mejorEntrenador.getNombre() + ". \nCon una calificación de: " + mejorEntrenador.getCalificacion());
    }//GEN-LAST:event_mnuMejorEntrenadorActionPerformed

    private void mnuCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnuCategoriasActionPerformed
        diagramaCategorias();
    }//GEN-LAST:event_mnuCategoriasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MDIEntrenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MDIEntrenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MDIEntrenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MDIEntrenamiento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MDIEntrenamiento().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarCiclista;
    private javax.swing.JButton btnIngresar;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JInternalFrame jifAgregarCiclista;
    private javax.swing.JInternalFrame jifAgregarEntrenador;
    private javax.swing.JInternalFrame jifIngresar;
    private javax.swing.JInternalFrame jifListarCiclistas;
    private javax.swing.JInternalFrame jifListarEntrenadores;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblContrasenia;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDocumentoId;
    private javax.swing.JLabel lblEdad;
    private javax.swing.JLabel lblEdadCic;
    private javax.swing.JLabel lblHrs;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblNombreCiclista;
    private javax.swing.JLabel lblNombreEntrenador;
    private javax.swing.JLabel lblSeg;
    private javax.swing.JLabel lblTiempo;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuArchivo;
    private javax.swing.JMenuItem mnuAñadirGrupo;
    private javax.swing.JMenuItem mnuCategorias;
    private javax.swing.JMenuItem mnuCiclistas;
    private javax.swing.JMenu mnuInsertar;
    private javax.swing.JMenuItem mnuListarEntrenadores;
    private javax.swing.JMenuItem mnuMejorEntrenador;
    private javax.swing.JMenuItem mnuSalir;
    private javax.swing.JSpinner spnEdad;
    private javax.swing.JSpinner spnEdadCic;
    private javax.swing.JSpinner spnHoras;
    private javax.swing.JSpinner spnMinutos;
    private javax.swing.JSpinner spnSegundos;
    private javax.swing.JTable tblCiclistas;
    private javax.swing.JTable tblEntrenadores;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDocumentoId;
    private javax.swing.JTextField txtNombreCiclista;
    private javax.swing.JTextField txtNombreEntrenador;
    // End of variables declaration//GEN-END:variables

}
