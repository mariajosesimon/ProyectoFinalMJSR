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
    int siguiente = 0;

    public ListarProveedores() {

        bPrimero.addActionListener(e -> {
            siguiente = 0;
            Proveedor p = listaProveedores.get(0);
            lbIDProveedor.setText(String.valueOf(p.getIdproveedor()));
            txtCodigo.setText(p.getCodproveedor());
            txtNombre.setText(p.getNombre());
            txtApellidos.setText(p.getApellidos());
            txtDireccion.setText(p.getDireccion());
            activarbotones(0);

        });
        bAnterior.addActionListener(e -> {

            if (siguiente > 0) {
                siguiente--;
            }
            Proveedor p = listaProveedores.get(siguiente);
            lbIDProveedor.setText(String.valueOf(p.getIdproveedor()));
            txtCodigo.setText(p.getCodproveedor());
            txtNombre.setText(p.getNombre());
            txtApellidos.setText(p.getApellidos());
            txtDireccion.setText(p.getDireccion());
            activarbotones(siguiente);


        });
        bSiguiente.addActionListener(e -> {
            if (siguiente < listaProveedores.size()-1) {
                siguiente++;
            }

            Proveedor p = listaProveedores.get(siguiente);
            lbIDProveedor.setText(String.valueOf(p.getIdproveedor()));
            txtCodigo.setText(p.getCodproveedor());
            txtNombre.setText(p.getNombre());
            txtApellidos.setText(p.getApellidos());
            txtDireccion.setText(p.getDireccion());

            activarbotones(siguiente);

        });
        bUltimo.addActionListener(e -> {
            siguiente = listaProveedores.size()-1;
            Proveedor p = listaProveedores.get(siguiente);
            lbIDProveedor.setText(String.valueOf(p.getIdproveedor()));
            txtCodigo.setText(p.getCodproveedor());
            txtNombre.setText(p.getNombre());
            txtApellidos.setText(p.getApellidos());
            txtDireccion.setText(p.getDireccion());
            activarbotones(siguiente);

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
        txtCodigo.setText(p.getCodproveedor());
        txtNombre.setText(p.getNombre());
        txtApellidos.setText(p.getApellidos());
        txtDireccion.setText(p.getDireccion());

        return JPProveedorListado;
    }

    public void activarbotones(int id) {

        if (id == 0) {
            bPrimero.setEnabled(false);
            bAnterior.setEnabled(false);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        } else if (id > 0 && id < listaProveedores.size()-1) {
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(true);
            bUltimo.setEnabled(true);
        } else if (id == listaProveedores.size()-1) {
            bPrimero.setEnabled(true);
            bAnterior.setEnabled(true);
            bSiguiente.setEnabled(false);
            bUltimo.setEnabled(false);
        }

    }

}
