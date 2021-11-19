package com.company.Vistas;

import com.company.Controladores.ControladorProveedor;
import com.company.Proveedor;
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
    int siguiente = 0;

    public ListarProyectos() {

        bPrimero.addActionListener(e -> {
            siguiente = 0;
            Proyecto p = listaProyectos.get(0);
            lbIDProyecto.setText(String.valueOf(p.getIdproyecto()));
            txtCodigo.setText(p.getCodproyecto());
            txtNombre.setText(p.getNombre());
            txtCiudad.setText(p.getCiudad());
            ControladorProveedor cp = new ControladorProveedor();
            Proveedor proveedor = cp.selectProveedor(p.getSupervisor());
            txtSupervisor.setText(proveedor.toString());
            activarbotones(0);

        });
        bAnterior.addActionListener(e -> {

            if (siguiente > 0) {
                siguiente--;
            }


            Proyecto p = listaProyectos.get(siguiente);
            lbIDProyecto.setText(String.valueOf(p.getIdproyecto()));
            txtCodigo.setText(p.getCodproyecto());
            txtNombre.setText(p.getNombre());
            txtCiudad.setText(p.getCiudad());
            ControladorProveedor cp = new ControladorProveedor();
            Proveedor proveedor = cp.selectProveedor(p.getSupervisor());
            txtSupervisor.setText(proveedor.toString());
            activarbotones(siguiente);


        });
        bSiguiente.addActionListener(e -> {
            if (siguiente < listaProyectos.size() - 1) {
                siguiente++;
            }

            Proyecto p = listaProyectos.get(siguiente);
            lbIDProyecto.setText(String.valueOf(p.getIdproyecto()));
            txtCodigo.setText(p.getCodproyecto());
            txtNombre.setText(p.getNombre());
            txtCiudad.setText(p.getCiudad());
            ControladorProveedor cp = new ControladorProveedor();
            Proveedor proveedor = cp.selectProveedor(p.getSupervisor());
            txtSupervisor.setText(proveedor.toString());

            activarbotones(siguiente);

        });
        bUltimo.addActionListener(e -> {
            siguiente = listaProyectos.size() - 1;
            Proyecto p = listaProyectos.get(siguiente);
            lbIDProyecto.setText(String.valueOf(p.getIdproyecto()));
            txtCodigo.setText(p.getCodproyecto());
            txtNombre.setText(p.getNombre());
            txtCiudad.setText(p.getCiudad());
            ControladorProveedor cp = new ControladorProveedor();
            Proveedor proveedor = cp.selectProveedor(p.getSupervisor());
            txtSupervisor.setText(proveedor.toString());
            activarbotones(siguiente);

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
        ControladorProveedor cp = new ControladorProveedor();
        Proveedor proveedor = cp.selectProveedor(p.getSupervisor());
        txtSupervisor.setText(proveedor.toString());

        return JPProyectoListado;
    }

    public void activarbotones(int id) {

        if (id == 0) { // primer registro
            bPrimero.setEnabled(false);
            bAnterior.setEnabled(false);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        } else if (id > 0 && id < listaProyectos.size() - 1) {//  registros del medio
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        } else if (id == listaProyectos.size() - 1) {// ultimo registro
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(false);
            bUltimo.setEnabled(false);
        }


    }

}



