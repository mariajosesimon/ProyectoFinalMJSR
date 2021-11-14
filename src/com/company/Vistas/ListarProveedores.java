package com.company.Vistas;

import com.company.Proveedor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ListarProveedores {
    Proveedor proveedor;
    
    private JPanel JPGeneral, JPProveedorListado;
    private JLabel lbTitulo, lbCodigo, lbNombre, lbApellidos, lbDireccion, lbId, lbIDProveedor;
    private JTextField txtNombre, txtDireccion, txtApellidos, txtCodigo;
    private JButton bUltimo, bAnterior, bPrimero, bSiguiente, bVolver;
    List<Proveedor> listaProveedores = new ArrayList<>();
    int siguiente;

    public ListarProveedores() {

        bPrimero.addActionListener(e -> {

            Proveedor p = listaProveedores.get(0);
            lbIDProveedor.setText(String.valueOf(p.getIdproveedor()));
            txtCodigo.setText(p.getCodproveedor());
            txtNombre.setText(p.getNombre());
            txtApellidos.setText(p.getApellidos());
            txtDireccion.setText(p.getDireccion());
            activarbotones();

        });
        bAnterior.addActionListener(e -> {


            /*Tengo que restar 2 porque en los listados emProveedorn en 0.
             *Ej: el listado el elemento get(3) es el que tiene idProveedor = 4,
             * para que mueste el anterior, es decir el id Proveedor 3, tengo que restar 2
             *  tengo que buscar el get(2)
             * */

            siguiente = Integer.parseInt(lbIDProveedor.getText()) - 2;
            Proveedor p = listaProveedores.get(siguiente);
            lbIDProveedor.setText(String.valueOf(p.getIdproveedor()));
            txtCodigo.setText(p.getCodproveedor());
            txtNombre.setText(p.getNombre());
            txtApellidos.setText(p.getApellidos());
            txtDireccion.setText(p.getDireccion());
            activarbotones();


        });
        bSiguiente.addActionListener(e -> {
            siguiente = Integer.parseInt(lbIDProveedor.getText());

            Proveedor p = listaProveedores.get(siguiente);
            lbIDProveedor.setText(String.valueOf(p.getIdproveedor()));
            txtCodigo.setText(p.getCodproveedor());
            txtNombre.setText(p.getNombre());
            txtApellidos.setText(p.getApellidos());
            txtDireccion.setText(p.getDireccion());

            activarbotones();

        });
        bUltimo.addActionListener(e -> {

            Proveedor p = listaProveedores.get(listaProveedores.size() - 1);
            lbIDProveedor.setText(String.valueOf(p.getIdproveedor()));
            txtCodigo.setText(p.getCodproveedor());
            txtNombre.setText(p.getNombre());
            txtApellidos.setText(p.getApellidos());
            txtDireccion.setText(p.getDireccion());
            activarbotones();

        });
        bVolver.addActionListener(e -> autoDestroy());
    }

    public void autoDestroy() {
        JPProveedorListado.removeAll();
        JPProveedorListado.repaint();
    }

    public void setListaProveedores(List<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }


    public JPanel getJPProveedorListado() {
        bPrimero.setEnabled(false);
        bAnterior.setEnabled(false);
        Proveedor p = listaProveedores.get(0);
        lbIDProveedor.setText(String.valueOf(p.getIdproveedor()));
        txtNombre.setText(p.getNombre());
        txtApellidos.setText(p.getApellidos());
        txtDireccion.setText(p.getDireccion());

        return JPProveedorListado;
    }

    public void activarbotones() {

        // primer registro
        if (Integer.parseInt(lbIDProveedor.getText()) == 1) {
            bPrimero.setEnabled(false);
            bAnterior.setEnabled(false);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        }
        //ultimo registro
        if (Integer.parseInt(lbIDProveedor.getText()) == listaProveedores.size()) {
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(false);
            bUltimo.setEnabled(false);
        }

        //registros del medio

        if (Integer.parseInt(lbIDProveedor.getText()) > 1 && Integer.parseInt(lbIDProveedor.getText()) < listaProveedores.size()) {
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        }


    }

}
