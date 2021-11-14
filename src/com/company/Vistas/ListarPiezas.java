package com.company.Vistas;

import com.company.Controladores.ControladorPieza;
import com.company.Pieza;

import javax.swing.*;
import javax.swing.plaf.PanelUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ListarPiezas {
    private JPanel JPGeneral, JPPiezaListado;
    private JLabel lbTitulo, lbCodigo, lbNombre, lbPrecio, lbDescripcion, lbId, lbIDPieza;
    private JButton bVolver;
    private JTextField txtNombre, txtPrecio, txtDescripcion, txtCodigo;
    private JButton bAnterior, bUltimo, bSiguiente, bPrimero;
    List<Pieza> listaPiezas = new ArrayList<>();
    int siguiente;

    public ListarPiezas() {

        bPrimero.addActionListener(e -> {
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
            bAnterior.setEnabled(false);
            bPrimero.setEnabled(false);

            Pieza p = listaPiezas.get(0);
            lbIDPieza.setText(String.valueOf(p.getIdpieza()));
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtDescripcion.setText(p.getDescripcion());

        });
        bAnterior.addActionListener(e -> {


            /*Tengo que restar 2 porque en los listados empiezan en 0.
            *Ej: el listado el elemento get(3) es el que tiene idpieza = 4,
            * para que mueste el anterior, es decir el id pieza 3, tengo que restar 2
            *  tengo que buscar el get(2)
            * */


            siguiente = Integer.parseInt(lbIDPieza.getText()) - 2;
            if(Integer.parseInt(lbIDPieza.getText())>2){
                bAnterior.setEnabled(true);
            }
            if (siguiente >=0 ) {
                Pieza p = listaPiezas.get(siguiente);
                lbIDPieza.setText(String.valueOf(p.getIdpieza()));
                txtNombre.setText(p.getNombre());
                txtPrecio.setText(String.valueOf(p.getPrecio()));
                txtDescripcion.setText(p.getDescripcion());
            } else {
                bPrimero.setEnabled(false);
                bAnterior.setEnabled(false);
                bUltimo.setEnabled(true);
                bSiguiente.setEnabled(true);
            }


        });
        bSiguiente.addActionListener(e -> {
            siguiente = Integer.parseInt(lbIDPieza.getText());

            System.out.println(listaPiezas.size());

            if (siguiente == 1) {
                bPrimero.setEnabled(false);
                bAnterior.setEnabled(false);
            } else {
                bPrimero.setEnabled(true);
                bAnterior.setEnabled(true);
            }

            if (siguiente > 0 && siguiente < listaPiezas.size()) {
                Pieza p = listaPiezas.get(siguiente);
                lbIDPieza.setText(String.valueOf(p.getIdpieza()));
                txtNombre.setText(p.getNombre());
                txtPrecio.setText(String.valueOf(p.getPrecio()));
                txtDescripcion.setText(p.getDescripcion());
            } else if (Integer.parseInt(lbIDPieza.getText()) == listaPiezas.size() - 1) {
                bUltimo.setEnabled(false);
                bSiguiente.setEnabled(false);
            }


        });
        bUltimo.addActionListener(e -> {

            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(false);
            bUltimo.setEnabled(false);

            Pieza p = listaPiezas.get(listaPiezas.size() - 1);
            lbIDPieza.setText(String.valueOf(p.getIdpieza()));
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtDescripcion.setText(p.getDescripcion());


        });
        bVolver.addActionListener(e -> autoDestroy());
    }

    public void autoDestroy() {
        JPPiezaListado.removeAll();
        JPPiezaListado.repaint();
    }

    public void setListaPiezas(List<Pieza> listaPiezas) {
        this.listaPiezas = listaPiezas;
    }


    public JPanel getJPPiezaListado() {
        bPrimero.setEnabled(false);
        bAnterior.setEnabled(false);
        Pieza p = listaPiezas.get(0);
        lbIDPieza.setText(String.valueOf(p.getIdpieza()));
        txtNombre.setText(p.getNombre());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtDescripcion.setText(p.getDescripcion());

        return JPPiezaListado;
    }
}
