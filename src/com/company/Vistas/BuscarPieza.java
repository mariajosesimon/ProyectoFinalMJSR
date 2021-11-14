package com.company.Vistas;

import com.company.Controladores.ControladorPieza;
import com.company.Pieza;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BuscarPieza {
    private JPanel JPGeneral, JPBuscarPieza;
    private JLabel lbTitulo;
    private JTextField txtBuscar;
    private JButton bBuscar, bVolver;
    private JTextArea txtDatos;
    private JComboBox<String> cbEncontrados;
    ControladorPieza controladorPieza = new ControladorPieza();
    List<Pieza> listadoPiezas = new ArrayList<>();
    String consulta;

    public BuscarPieza() {

        bBuscar.addActionListener(e -> {

            txtDatos.setText("");
            listadoPiezas = controladorPieza.selectByCodigo(txtBuscar.getText(), consulta);
            if (listadoPiezas.size()==0) {
                JOptionPane.showMessageDialog(null, "No se han encontrado datos", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            } else {
                cbEncontrados.removeAllItems(); //Limpiamos el combobox
                //rellenamos con los datos recibidos.
                cbEncontrados.setModel(new DefaultComboBoxModel(listadoPiezas.toArray()));

            }

        });

        cbEncontrados.addActionListener(e -> {

            Pieza p = (Pieza) cbEncontrados.getSelectedItem();

            if (p != null) {
                txtDatos.setText(
                        "Codigo Pieza: " + p.getCodpieza() + "\n" +
                                "Nombre: " + p.getNombre() + "\n" +
                                "Precio: " + p.getPrecio() + "\n" +
                                "Descripcion: " + p.getDescripcion());
            }


        });


        bVolver.addActionListener(e -> autoDestroy());
    }

    public void autoDestroy() {
        JPBuscarPieza.removeAll();
        JPBuscarPieza.repaint();
    }

    public JPanel getJPBuscarPieza() {

        return JPBuscarPieza;
    }


}
