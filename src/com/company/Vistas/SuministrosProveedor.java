package com.company.Vistas;

import com.company.Controladores.ControladorGestionGlobal;
import com.company.Proveedor;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SuministrosProveedor {
    private JPanel JPSuministrosProveedor;
    private JLabel lProveedor, lPiezas, lProyectos;
    private JComboBox cbProveedor;
    private JTextField txtNombre, txtApellidos, txtDireccion, txtPiezasTotal, txtProyectosTotal;
    private JButton bVer;
    private JButton bVolver;
    private JPanel JPGeneral;
    private JLabel lbTitulo;
    private JScrollPane JSTabla;
    private JLabel lbTituloPiezasSuministradas;
    private List<Proveedor> listaProveedores = new ArrayList<>();
    private JTable tbProveedores;
    private ControladorGestionGlobal cGG = new ControladorGestionGlobal();
    private int idProve = 0;

    public SuministrosProveedor(List<Proveedor> listaProveedores) {

        /*Cargar datos en los comboBox*/

        if (listaProveedores.size() == 0) {
            JOptionPane.showMessageDialog(null, "No se han encontrado datos", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            autoDestroy();
        } else {
            // eliminamos todos los datos del combobox
            cbProveedor.removeAllItems();
            //rellenamos datos

            for (Proveedor prov : listaProveedores) {
                cbProveedor.addItem(prov.getCodproveedor());
            }
        }


        bVer.addActionListener(e -> {

            tbProveedores = new JTable();
            JSTabla.setViewportView(tbProveedores);
            List<Object[]> resultado = cGG.tablaProveedor(idProve);
            tbProveedores.setModel(new TablaProveedor(resultado));


        });

        cbProveedor.addActionListener(e -> {
            String p = (String) cbProveedor.getSelectedItem();
            for (Proveedor proveedor : listaProveedores) {
                if (proveedor.getCodproveedor().equals(p)) {
                    txtNombre.setText(proveedor.getNombre());
                    txtApellidos.setText(proveedor.getApellidos());
                    txtDireccion.setText(proveedor.getDireccion());
                    txtPiezasTotal.setText(String.valueOf(cGG.piezasPorProveedor(proveedor.getIdproveedor())));
                    txtProyectosTotal.setText(String.valueOf(cGG.proyectosPorProveedor(proveedor.getIdproveedor())));
                    idProve = proveedor.getIdproveedor(); // valor que pasamos a la consulta para que nos muestre los datos en la tabla
                }
            }


        });


        bVolver.addActionListener(e -> {
            autoDestroy();
        });
    }


    public void autoDestroy() {
        JPSuministrosProveedor.removeAll();
        JPSuministrosProveedor.repaint();
    }

    public JPanel getJPSuministrosProveedor() {
        return JPSuministrosProveedor;
    }
}
