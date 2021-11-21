package com.company.Vistas;

import com.company.Pieza;

import javax.swing.*;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

public class ListarPiezas {
    private JPanel JPGeneral, JPPiezaListado;
    private JLabel lbTitulo, lbCodigo, lbNombre, lbPrecio, lbDescripcion, lbId, lbIDPieza;
    private JButton bVolver;
    private JTextField txtNombre, txtPrecio, txtDescripcion, txtCodigo;
    private JButton bAnterior, bUltimo, bSiguiente, bPrimero;
    List<Pieza> listaPiezas = new ArrayList<>();
    int siguiente = 0;

    public ListarPiezas() {

        bPrimero.addActionListener(e -> {
            siguiente = 0;
            Pieza p = listaPiezas.get(0);
            lbIDPieza.setText(String.valueOf(p.getIdpieza()));
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtDescripcion.setText(p.getDescripcion());

            activarbotones(0);

        });
        bAnterior.addActionListener(e -> {

            if (siguiente > 0) {
                siguiente--;
            }
            Pieza p = listaPiezas.get(siguiente);
            lbIDPieza.setText(String.valueOf(p.getIdpieza()));
            txtCodigo.setText(p.getCodpieza());
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtDescripcion.setText(p.getDescripcion());
            activarbotones(siguiente);


        });
        bSiguiente.addActionListener(e -> {

            if (siguiente < listaPiezas.size() - 1) {
                siguiente++;
            }
            Pieza p = listaPiezas.get(siguiente);
            lbIDPieza.setText(String.valueOf(p.getIdpieza()));
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtDescripcion.setText(p.getDescripcion());

            activarbotones(siguiente);

        });
        bUltimo.addActionListener(e -> {
            siguiente = listaPiezas.size() - 1;
            Pieza p = listaPiezas.get(siguiente);
            lbIDPieza.setText(String.valueOf(p.getIdpieza()));
            txtNombre.setText(p.getNombre());
            txtPrecio.setText(String.valueOf(p.getPrecio()));
            txtDescripcion.setText(p.getDescripcion());
            activarbotones(siguiente);

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
        txtCodigo.setText(p.getCodpieza());
        txtNombre.setText(p.getNombre());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtDescripcion.setText(p.getDescripcion());

        return JPPiezaListado;
    }

    public void activarbotones(int id) {

        if (id == 0) {
            bPrimero.setEnabled(false);
            bAnterior.setEnabled(false);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        } else if (id > 0 && id < listaPiezas.size() - 1) {
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        } else if (id == listaPiezas.size() - 1) {
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(false);
            bUltimo.setEnabled(false);
        }

    }
}
