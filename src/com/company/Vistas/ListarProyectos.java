package com.company.Vistas;

import com.company.Proyecto;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListarProyectos {
    private JPanel JPGeneral, JPProyectoListado;
    private JLabel lbTitulo, lbCodigo, lbNombre, lbIDProyecto, lbApellidos, lbSupervisor, lbId;
    private JTextField txtNombre, txtCiudad, txtSupervisor, txtCodigo;
    private JButton bAnterior, bUltimo, bVolver, bSiguiente, bPrimero;

    List<Proyecto> listaProyectos = new ArrayList<>();
    int siguiente;

    public ListarProyectos() {

        bPrimero.addActionListener(e -> {

            Proyecto p = listaProyectos.get(0);
            lbIDProyecto.setText(String.valueOf(p.getIdproyecto()));
            txtCodigo.setText(p.getCodproyecto());
            txtNombre.setText(p.getNombre());
            txtCiudad.setText(p.getCiudad());
            txtSupervisor.setText(p.getSupervisor());
            activarbotones();

        });
        bAnterior.addActionListener(e -> {


            /*Tengo que restar 2 porque en los listados emProyecton en 0.
             *Ej: el listado el elemento get(3) es el que tiene idProyecto = 4,
             * para que mueste el anterior, es decir el id Proyecto 3, tengo que restar 2
             *  tengo que buscar el get(2)
             * */

            siguiente = Integer.parseInt(lbIDProyecto.getText()) - 2;
            Proyecto p = listaProyectos.get(siguiente);
            lbIDProyecto.setText(String.valueOf(p.getIdproyecto()));
            txtCodigo.setText(p.getCodproyecto());
            txtNombre.setText(p.getNombre());
            txtCiudad.setText(p.getCiudad());
            txtSupervisor.setText(p.getSupervisor());
            activarbotones();


        });
        bSiguiente.addActionListener(e -> {
            siguiente = Integer.parseInt(lbIDProyecto.getText());

            Proyecto p = listaProyectos.get(siguiente);
            lbIDProyecto.setText(String.valueOf(p.getIdproyecto()));
            txtCodigo.setText(p.getCodproyecto());
            txtNombre.setText(p.getNombre());
            txtCiudad.setText(p.getCiudad());
            txtSupervisor.setText(p.getSupervisor());

            activarbotones();

        });
        bUltimo.addActionListener(e -> {

            Proyecto p = listaProyectos.get(listaProyectos.size() - 1);
            lbIDProyecto.setText(String.valueOf(p.getIdproyecto()));
            txtCodigo.setText(p.getCodproyecto());
            txtNombre.setText(p.getNombre());
            txtCiudad.setText(p.getCiudad());
            txtSupervisor.setText(p.getSupervisor());
            activarbotones();

        });
        bVolver.addActionListener(e -> autoDestroy());
    }

    public void autoDestroy() {
        JPProyectoListado.removeAll();
        JPProyectoListado.repaint();
    }

    public void setListaProyectos(List<Proyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }


    public JPanel getJPProyectoListado() {
        bPrimero.setEnabled(false);
        bAnterior.setEnabled(false);
        Proyecto p = listaProyectos.get(0);
        lbIDProyecto.setText(String.valueOf(p.getIdproyecto()));
        txtCodigo.setText(p.getCodproyecto());
        txtNombre.setText(p.getNombre());
        txtCiudad.setText(p.getCiudad());
        txtSupervisor.setText(p.getSupervisor());

        return JPProyectoListado;
    }

    public void activarbotones() {

        // primer registro
        if (Integer.parseInt(lbIDProyecto.getText()) == 1) {
            bPrimero.setEnabled(false);
            bAnterior.setEnabled(false);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        }
        //ultimo registro
        if (Integer.parseInt(lbIDProyecto.getText()) == listaProyectos.size()) {
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(false);
            bUltimo.setEnabled(false);
        }

        //registros del medio

        if (Integer.parseInt(lbIDProyecto.getText()) > 1 && Integer.parseInt(lbIDProyecto.getText()) < listaProyectos.size()) {
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        }


    }

}



