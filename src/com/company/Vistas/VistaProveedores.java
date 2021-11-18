package com.company.Vistas;

import com.company.Controladores.ControladorProveedor;
import com.company.Proveedor;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class VistaProveedores {
    private JPanel JPGeneral, JPProveedor;
    private JLabel lbId, lbListaProveedores, lbIDProveedor, lbTitulo, lbCodigo, lbNombre, lbApellidos, lbDireccion;
    private JButton bInsertar, bCancelar;
    private JTextField txtNombre, txtApellidos, txtDireccion, txtCodigo;
    private JList<Proveedor> lstProveedores;
    private JScrollPane spListado;

    private ControladorProveedor controladorProveedor = new ControladorProveedor();


    public VistaProveedores() {


        bCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                autoDestroy();
            }
        });
        bInsertar.addActionListener(e -> {

            boolean ok = false;  // comprobamos en los controladores si ha pasado todas las validaciones cada objeto y si es true puede continuar
            Proveedor proveedor = new Proveedor();
            if ((!lbIDProveedor.getText().equals(""))) {
                proveedor.setIdproveedor(Integer.parseInt(lbIDProveedor.getText()));
            }
            proveedor.setCodproveedor(txtCodigo.getText().toUpperCase());
            proveedor.setNombre(txtNombre.getText().toUpperCase());
            proveedor.setApellidos(txtApellidos.getText().toUpperCase());
            proveedor.setDireccion(txtDireccion.getText().toUpperCase());


            if (bInsertar.getText().equals("ELIMINAR")) {
                controladorProveedor.deleteProveedor(proveedor, proveedor.getIdproveedor());
                JOptionPane.showMessageDialog(null, "Se ha eliminado la Proveedor", "Info", JOptionPane.INFORMATION_MESSAGE);
                autoDestroy();

            } else if (bInsertar.getText().equalsIgnoreCase("INSERTAR") ||
                    (bInsertar.getText().equals("MODIFICAR"))) {

                ok = controladorProveedor.validaciones(proveedor);

                if (ok) {
                    if (bInsertar.getText().equalsIgnoreCase("INSERTAR")) {
                        controladorProveedor.addProveedor(proveedor);
                        JOptionPane.showMessageDialog(null, "Se ha añadido la Proveedor", "Info", JOptionPane.INFORMATION_MESSAGE);
                        autoDestroy();
                    } else if (bInsertar.getText().equals("MODIFICAR")) {
                        proveedor.setIdproveedor(Integer.parseInt(lbIDProveedor.getText()));
                        controladorProveedor.editProveedor(proveedor, proveedor.getIdproveedor());
                        JOptionPane.showMessageDialog(null, "Se ha modificado la Proveedor", "Info", JOptionPane.INFORMATION_MESSAGE);
                        autoDestroy();
                    }

                }
            } else {
                JOptionPane.showMessageDialog(null, "Se han encontrado errores",
                        "Resultado", JOptionPane.ERROR_MESSAGE
                );
            }
        });


        lstProveedores.addListSelectionListener(e -> {
            Proveedor Proveedor = lstProveedores.getSelectedValue();

            if (Proveedor != null) {
                lbIDProveedor.setText(String.valueOf(Proveedor.getIdproveedor()));
                txtCodigo.setText(Proveedor.getCodproveedor());
                txtNombre.setText(Proveedor.getNombre());
                txtApellidos.setText(String.valueOf(Proveedor.getApellidos()));
                txtDireccion.setText(Proveedor.getDireccion());

            }

        });

    }


    public JPanel getJPProveedor() {
        return JPProveedor;
    }

    public void autoDestroy() {
        JPProveedor.removeAll();
        JPProveedor.repaint();
    }

    public JScrollPane getSpListado() {
        return spListado;
    }

    public JLabel getLbListaProveedores() {
        return lbListaProveedores;
    }

    public JList<Proveedor> getLstProveedores() {
        return lstProveedores;
    }

    public void renombrar(String texto) {
        bInsertar.setText(texto);
    }

    public void mostrarProveedores(List<Proveedor> Proveedores) {
        DefaultListModel<Proveedor> modelo = new DefaultListModel<>();
        for (Proveedor Proveedor : Proveedores) {
            modelo.addElement(Proveedor);
        }
        lstProveedores.setModel(modelo);
    }

    public JLabel getLbId() {
        return lbId;
    }

    public JLabel getLbIDProveedor() {
        return lbIDProveedor;
    }
}
