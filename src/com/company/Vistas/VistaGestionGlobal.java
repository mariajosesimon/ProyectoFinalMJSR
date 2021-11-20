package com.company.Vistas;

import com.company.Controladores.ControladorGestionGlobal;
import com.company.Controladores.ControladorPieza;
import com.company.Controladores.ControladorProveedor;
import com.company.Controladores.ControladorProyecto;
import com.company.Gestionglobal;
import com.company.Pieza;
import com.company.Proveedor;
import com.company.Proyecto;

import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class VistaGestionGlobal {
    private JPanel JPGeneral, JPGestionGlobal;
    private JLabel lbTitulo, lbProveedor, lbPieza, lbProyecto, lbCantidad, lbId, lbIDProyecto;
    private JButton bVolver, bInsertar;
    private JComboBox cbProveedor, cbPieza, cbProyecto;
    private JTextField txtCantidad, txtProveedor, txtPieza, txtProyecto, txtIdGG;
    private JList lstGG;

    private List<Proveedor> listadoProveedores = new ArrayList<>();
    private List<Pieza> listadoPiezas = new ArrayList<>();
    private List<Proyecto> listadoProyectos = new ArrayList<>();

    private ControladorGestionGlobal cGG = new ControladorGestionGlobal();

    /*listadoProveedores, listadoPiezas, listadoProyectos --> orden en que recibo datos*/
    public VistaGestionGlobal(List<Proveedor> listadoProveedores, List<Pieza> listadoPiezas, List<Proyecto> listadoProyectos) {
        Gestionglobal gestionGlobal = new Gestionglobal();
        gestionGlobal.setIdProveedor(-1);
        gestionGlobal.setIdPieza(-1);
        gestionGlobal.setIdProyecto(-1);
        gestionGlobal.setCantidad(-1);

        /*Cargar datos en los comboBox*/

        if (listadoProveedores.size() == 0 && listadoPiezas.size() == 0 && listadoProyectos.size() == 0) {
            JOptionPane.showMessageDialog(null, "No se han encontrado datos", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            autoDestroy();
        } else {
            // eliminamos todos los datos del combobox
            cbProveedor.removeAllItems();
            cbPieza.removeAllItems();
            cbProyecto.removeAllItems();
            //rellenamos datos

            for (Proveedor prov : listadoProveedores) {
                cbProveedor.addItem(prov.getCodproveedor());
            }
            for (Pieza pi : listadoPiezas) {
                cbPieza.addItem(pi.getCodpieza());
            }
            for (Proyecto py : listadoProyectos) {
                cbProyecto.addItem(py.getCodproyecto());
            }

        }

        bVolver.addActionListener(e -> autoDestroy());

        bInsertar.addActionListener(e -> {


            if (bInsertar.getText().equals("ELIMINAR")) {
                int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la relacion?", "Alerta!", JOptionPane.YES_NO_OPTION);
                if (resp == 0) {
                    cGG.deleteGestionGlobal(gestionGlobal, gestionGlobal.getIdGestionGlobal());
                    JOptionPane.showMessageDialog(null, "Se ha eliminado la gestión", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
                autoDestroy();
            } else if (bInsertar.getText().equalsIgnoreCase("INSERTAR") ||
                    (bInsertar.getText().equals("MODIFICAR"))) {

                //Comprobamos que cantidad tiene dato y sea mayor de 0
                boolean ok;

                try{
                gestionGlobal.setCantidad(Integer.parseInt(txtCantidad.getText()));}catch (NumberFormatException n){
                    JOptionPane.showMessageDialog(null, "La cantidad no puede ser vacia", "Error de formato", JOptionPane.ERROR_MESSAGE);
                }
                ok = cGG.validaciones(gestionGlobal);

                if (ok) {
                    if (bInsertar.getText().equalsIgnoreCase("INSERTAR")) {
                        cGG.addGestionGlobal(gestionGlobal);
                        JOptionPane.showMessageDialog(null, "Se ha añadido la gestión", "Info", JOptionPane.INFORMATION_MESSAGE);
                        autoDestroy();

                    } else /*es modificar*/{
                        gestionGlobal.setIdGestionGlobal(Integer.parseInt(txtIdGG.getText()));
                        cGG.editGestionGlobal(gestionGlobal, gestionGlobal.getIdGestionGlobal());
                        JOptionPane.showMessageDialog(null, "Se ha modificado la gestión", "Info", JOptionPane.INFORMATION_MESSAGE);
                        autoDestroy();
                    }
                }

              //  autoDestroy();
            } else {
                JOptionPane.showMessageDialog(null, "Se han encontrado errores",
                        "Resultado", JOptionPane.ERROR_MESSAGE
                );
            }

        });


        // Comprobacion si pulsa determinadas teclas el usuario. No permite letras.
        txtCantidad.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                char validar = e.getKeyChar();
                if (validar < '0' || validar > '9') {
                    txtCantidad.setText("");
                    JOptionPane.showMessageDialog(null, "Solo numeros y  punto . ");
                }
            }
        });

        /*Rellenamos los campos de Proveedores, piezas y proyecto con el codigo seleccionado de cada combo box*/
        cbProveedor.addActionListener(e -> {
            String p = (String) cbProveedor.getSelectedItem();
            for (Proveedor proveedor : listadoProveedores) {
                if (proveedor.getCodproveedor().equals(p)) {
                    txtProveedor.setText(proveedor.toString());
                    gestionGlobal.setIdProveedor(proveedor.getIdproveedor());
                }
            }


        });
        cbPieza.addActionListener(e -> {
            String p = (String) cbPieza.getSelectedItem();
            for (Pieza pieza : listadoPiezas) {
                if (pieza.getCodpieza().equals(p)) {
                    txtPieza.setText(pieza.toString());
                    gestionGlobal.setIdPieza(pieza.getIdpieza());
                }
            }
        });
        cbProyecto.addActionListener(e -> {
            String p = (String) cbProyecto.getSelectedItem();
            for (Proyecto py : listadoProyectos) {
                if (py.getCodproyecto().equals(p)) {
                    txtProyecto.setText(py.toString());
                    gestionGlobal.setIdProyecto(py.getIdproyecto());
                }
            }
        });
        lstGG.addListSelectionListener(e-> {
            Gestionglobal gg = (Gestionglobal) lstGG.getSelectedValue();
            if(gg != null){
                txtIdGG.setText(String.valueOf(gg.getIdGestionGlobal()));
                txtCantidad.setText(String.valueOf(gg.getCantidad()));

                ControladorProveedor cproveedor = new ControladorProveedor();
                Proveedor proveedor = cproveedor.selectProveedor(gg.getIdProveedor());
                cbProveedor.setSelectedItem(proveedor.getCodproveedor());
                txtProveedor.setText(proveedor.toString());

                ControladorPieza cpieza = new ControladorPieza();
                Pieza pieza = cpieza.selectPieza(gg.getIdPieza());
                cbPieza.setSelectedItem(pieza.getCodpieza());
                txtPieza.setText(pieza.toString());

                ControladorProyecto cproyecto = new ControladorProyecto();
                Proyecto proyecto = cproyecto.selectProyecto(gg.getIdProyecto());
                cbProyecto.setSelectedItem(proyecto.getCodproyecto());
                txtProyecto.setText(proyecto.toString());


            }
        });
    }


    public JLabel getLbId() {
        return lbId;
    }

    public JTextField getTxtIdGG() {
        return txtIdGG;
    }

    public JPanel getJPGestionGlobal() {
        return JPGestionGlobal;
    }

    public void autoDestroy() {
        JPGestionGlobal.removeAll();
        JPGestionGlobal.repaint();
    }

    public void renombrar(String texto) {
        bInsertar.setText(texto);
    }

    public void mostrarGestiones(List<Gestionglobal> Gestiones) {
        DefaultListModel<Gestionglobal> modelo = new DefaultListModel<>();
        for (Gestionglobal gg : Gestiones) {
            modelo.addElement(gg);
        }
        lstGG.setModel(modelo);
    }


    public JComboBox getCbProveedor() {
        return cbProveedor;
    }

    public JComboBox getCbPieza() {
        return cbPieza;
    }

    public JComboBox getCbProyecto() {
        return cbProyecto;
    }

    public JTextField getTxtCantidad() {
        return txtCantidad;
    }
}
