package com.company.Vistas;

import com.company.Controladores.ControladorPieza;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarPieza {
    private JPanel JPGeneral,JPBuscarPieza;
    private JLabel lbTitulo;
    private JTextField txtBuscar;
    private JButton bBuscar, bVolver;
    private JTextArea txtDatos;
    private JComboBox cbEncontrados;
    ControladorPieza controladorPieza = new ControladorPieza();


    public BuscarPieza() {

        bBuscar.addActionListener(e -> {

            txtBuscar.getText();

        });

        cbEncontrados.addActionListener(e -> {


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
