package com.company.Vistas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ayuda {
    private JPanel JPGeneral;
    private JPanel JPAyuda;
    private JButton bVolver;
    private JTextArea txtDatos;
    private JLabel lbTitulo;


    public Ayuda() {

        bVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoDestroy();
            }
        });
    }

    public void autoDestroy() {
        JPAyuda.removeAll();
        JPAyuda.repaint();
    }

    public JPanel getJPAyuda() {
        txtDatos.setText("" +
                "AUTORA: MARIA JOSE SIMON RODRIGUEZ" + "\n" +
                "CURSO: 3 DESARROLLO DE APLICACIONES MULTIPLATAFORMA" + "\n" +
                "ASIGNATURA: ACCESO A DATOS");
        return JPAyuda;
    }
}
