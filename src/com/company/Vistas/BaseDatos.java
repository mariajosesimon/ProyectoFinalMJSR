package com.company.Vistas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BaseDatos {
    private JPanel JPGeneral;
    private JPanel JPBaseDatos;
    private JButton bVolver;
    private JLabel lbTitulo;
    private JTextArea txtDatos;


    public BaseDatos(){

        bVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoDestroy();
            }
        });
    }
    public void autoDestroy() {
        JPBaseDatos.removeAll();
        JPBaseDatos.repaint();
    }

    public JPanel getJPBaseDatos() {
        txtDatos.setText("" +
                "AUTORA: MARIA JOSE SIMON RODRIGUEZ" +"\n"+
                "CURSO: 3 DESARROLLO DE APLICACIONES MULTIPLATAFORMA" +"\n"+
                "ASIGNATURA: ACCESO A DATOS");
        return JPBaseDatos;
    }
}
