package com.company.Vistas;

import com.company.Controladores.ControladorPieza;
import com.company.Pieza;
import com.company.Proveedor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Buscar {
    private JPanel JPGeneral, JPBuscar;
    private JLabel lbTitulo;
    private JTextField txtBuscar;
    private JButton bBuscar, bVolver;
    private JTextArea txtDatos;
    private JComboBox<String> cbEncontrados;
    private JButton bLimpiar;
    ControladorPieza controladorPieza = new ControladorPieza();
    List<Pieza> listadoPiezas = new ArrayList<>();
    String consulta;
    String tabla;

    public Buscar() {

        bBuscar.addActionListener(e -> {

            txtDatos.setText("");
            listadoPiezas = controladorPieza.selectByCodigo(txtBuscar.getText(), consulta, tabla);
            if (listadoPiezas.size()==0) {
                JOptionPane.showMessageDialog(null, "No se han encontrado datos", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                cbEncontrados.removeAllItems(); //Limpiamos el combobox
                //rellenamos con los datos recibidos.
                cbEncontrados.setModel(new DefaultComboBoxModel(listadoPiezas.toArray()));

            }

        });

        cbEncontrados.addActionListener(e -> {

            if(tabla.equals("Pieza")) {
                Pieza p = (Pieza) cbEncontrados.getSelectedItem();

                if (p != null) {
                    txtDatos.setText(
                            "Codigo Pieza: " + p.getCodpieza() + "\n" +
                                    "Nombre: " + p.getNombre() + "\n" +
                                    "Precio: " + p.getPrecio() + "\n" +
                                    "Descripcion: " + p.getDescripcion());
                }
            }else if(tabla.equals("Proveedor")){
                Proveedor p = (Proveedor) cbEncontrados.getSelectedItem();
                txtDatos.setText(
                        "Codigo proveedor: " + p.getCodproveedor() + "\n" +
                                "Nombre: " + p.getNombre() + "\n" +
                                "Apellidos: " + p.getApellidos() + "\n" +
                                "Direccion: " + p.getDireccion());
            }

        });


        bVolver.addActionListener(e -> autoDestroy());
        bLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtBuscar.setText("");
            }
        });
    }

    public void autoDestroy() {
        JPBuscar.removeAll();
        JPBuscar.repaint();
    }

    public JPanel getJPBuscar() {

        return JPBuscar;
    }



}
