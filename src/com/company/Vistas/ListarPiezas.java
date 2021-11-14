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

            Pieza p = listaPiezas.get(0);
            lbIDPieza.setText(String.valueOf(p.getIdpieza()));
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtDescripcion.setText(p.getDescripcion());
            activarbotones();

        });
        bAnterior.addActionListener(e -> {


            /*Tengo que restar 2 porque en los listados empiezan en 0.
             *Ej: el listado el elemento get(3) es el que tiene idpieza = 4,
             * para que mueste el anterior, es decir el id pieza 3, tengo que restar 2
             *  tengo que buscar el get(2)
             * */

            siguiente = Integer.parseInt(lbIDPieza.getText()) - 2;
            Pieza p = listaPiezas.get(siguiente);
            lbIDPieza.setText(String.valueOf(p.getIdpieza()));
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtDescripcion.setText(p.getDescripcion());
            activarbotones();


        });
        bSiguiente.addActionListener(e -> {
            siguiente = Integer.parseInt(lbIDPieza.getText());

            Pieza p = listaPiezas.get(siguiente);
            lbIDPieza.setText(String.valueOf(p.getIdpieza()));
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtDescripcion.setText(p.getDescripcion());

            activarbotones();

        });
        bUltimo.addActionListener(e -> {

            Pieza p = listaPiezas.get(listaPiezas.size() - 1);
            lbIDPieza.setText(String.valueOf(p.getIdpieza()));
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtDescripcion.setText(p.getDescripcion());
            activarbotones();

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

    public void activarbotones() {

        // primer registro
        if(Integer.parseInt(lbIDPieza.getText()) == 1){
            bPrimero.setEnabled(false);
            bAnterior.setEnabled(false);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        }
        //ultimo registro
        if(Integer.parseInt(lbIDPieza.getText()) == listaPiezas.size()){
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(false);
            bUltimo.setEnabled(false);
        }

        //registros del medio

        if(Integer.parseInt(lbIDPieza.getText())> 1 &&Integer.parseInt(lbIDPieza.getText()) < listaPiezas.size()) {
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        }




    }
}
