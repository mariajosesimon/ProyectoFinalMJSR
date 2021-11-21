package com.company.Vistas;

import com.company.Controladores.ControladorPieza;
import com.company.Controladores.ControladorProveedor;
import com.company.Controladores.ControladorProyecto;
import com.company.Pieza;
import com.company.Proveedor;
import com.company.Proyecto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Buscar {
    private JPanel JPGeneral, JPBuscar;
    private JLabel lbTitulo;
    private JTextField txtBuscar;
    private JButton bBuscar, bVolver, bLimpiar;
    private JTextArea txtDatos;
    private JComboBox<String> cbEncontrados;
    ControladorPieza controladorPieza = new ControladorPieza();
    ControladorProveedor controladorProveedor = new ControladorProveedor();
    ControladorProyecto controladorProyecto = new ControladorProyecto();
    List listado = new ArrayList<>();
    String consulta;
    String tabla;

    public Buscar() {

        bBuscar.addActionListener(e -> {

            txtDatos.setText("");

            if (tabla.equals("Pieza")) {
                listado = controladorPieza.selectByCodigo(txtBuscar.getText().toUpperCase(), consulta, tabla);
            } else if (tabla.equals("Proveedor")) {
                listado = controladorProveedor.selectByCodigo(txtBuscar.getText().toUpperCase(), consulta, tabla);
            } else if (tabla.equals("Proyecto")) {
                listado = controladorProveedor.selectByCodigo(txtBuscar.getText().toUpperCase(), consulta, tabla);
            }

            if (listado.size() == 0) {
                JOptionPane.showMessageDialog(null, "No se han encontrado datos", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                cbEncontrados.removeAllItems(); //Limpiamos el combobox
                //rellenamos con los datos recibidos.
                cbEncontrados.setModel(new DefaultComboBoxModel(listado.toArray()));
            }
        });

        cbEncontrados.addActionListener(e -> {

            if (tabla.equals("Pieza")) {
                Pieza p = (Pieza) cbEncontrados.getSelectedItem();

                if (p != null) {
                    txtDatos.setText(
                            "Codigo Pieza: " + p.getCodpieza() + "\n" +
                                    "Nombre: " + p.getNombre() + "\n" +
                                    "Precio: " + p.getPrecio() + "\n" +
                                    "Descripcion: " + p.getDescripcion());
                }
            } else if (tabla.equals("Proveedor")) {
                Proveedor p = (Proveedor) cbEncontrados.getSelectedItem();
                txtDatos.setText(
                        "Codigo proveedor: " + p.getCodproveedor() + "\n" +
                                "Nombre: " + p.getNombre() + "\n" +
                                "Apellidos: " + p.getApellidos() + "\n" +
                                "Direccion: " + p.getDireccion());
            } else if (tabla.equals("Proyecto")) {
                Proyecto py = (Proyecto) cbEncontrados.getSelectedItem();
                txtDatos.setText(
                        "Codigo proyecto: " + py.getCodproyecto() + "\n" +
                                "Nombre: " + py.getNombre() + "\n" +
                                "Ciudad: " + py.getCiudad() + "\n" +
                                "Proveedor: " + py.getSupervisor());
            }
        });

        bVolver.addActionListener(e -> autoDestroy());
        bLimpiar.addActionListener(e -> txtBuscar.setText(""));
    }

    public void autoDestroy() {
        JPBuscar.removeAll();
        JPBuscar.repaint();
    }

    public JPanel getJPBuscar() {
        return JPBuscar;
    }


}
