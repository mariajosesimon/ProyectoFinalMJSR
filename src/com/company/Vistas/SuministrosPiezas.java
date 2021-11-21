package com.company.Vistas;

import com.company.Controladores.ControladorGestionGlobal;
import com.company.Pieza;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SuministrosPiezas {
    private JPanel JPSuministrosPiezas, JPGeneral;
    private JComboBox cbPieza;
    private JLabel lProyectos, lPieza, lCantidad, lProveedores, lbTituloPiezasSumnistradas;
    private JTextField txtNombre, txtProyectos, txtProveedores, txtCantidad, txtPrecio;
    private JTextArea txtDescripcion;
    private JButton bVer, bVolver;
    private JScrollPane JSPiezas;
    private List<Pieza> listaPiezas = new ArrayList<>();
    private JTable tbPiezas;
    private ControladorGestionGlobal cGG = new ControladorGestionGlobal();
    private int idPieza = 0;


    public SuministrosPiezas(List<Pieza> listaPiezas) {

        /*Cargar datos en los comboBox*/

        if (listaPiezas.size() == 0) {
            JOptionPane.showMessageDialog(null, "No se han encontrado datos", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            autoDestroy();
        } else {
            // eliminamos todos los datos del combobox
            cbPieza.removeAllItems();
            //rellenamos datos

            for (Pieza pieza : listaPiezas) {
                cbPieza.addItem(pieza.getCodpieza());
            }
        }

        bVer.addActionListener(e -> {

            tbPiezas = new JTable();
            JSPiezas.setViewportView(tbPiezas);
            List<Object[]> resultadoPiezas = cGG.tablaPiezas(idPieza);
            tbPiezas.setModel(new TablaPiezas(resultadoPiezas));


        });

        cbPieza.addActionListener(e -> {
            String p = (String) cbPieza.getSelectedItem();
            for (Pieza pieza : listaPiezas) {
                if (pieza.getCodpieza().equals(p)) {
                    txtNombre.setText(pieza.getNombre());
                    txtDescripcion.setText(pieza.getDescripcion());
                    txtPrecio.setText(String.valueOf(pieza.getPrecio()));

                    ControladorGestionGlobal cGG = new ControladorGestionGlobal();
                    txtProveedores.setText(String.valueOf(cGG.proveedoresPorPiezas(pieza.getIdpieza())));
                    txtProyectos.setText(String.valueOf(cGG.proyectosPorPieza(pieza.getIdpieza())));
                    txtCantidad.setText(String.valueOf(cGG.totalPieza(pieza.getIdpieza())));

                    idPieza = pieza.getIdpieza(); // Este valor es el que pasamos a la consulta y que nos muestre las piezas.
                }
            }


        });

        bVolver.addActionListener(e -> {
            autoDestroy();
        });
    }

    public void autoDestroy() {
        JPSuministrosPiezas.removeAll();
        JPSuministrosPiezas.repaint();
    }

    public JPanel getJPPiezas() {
        return JPSuministrosPiezas;
    }
}
