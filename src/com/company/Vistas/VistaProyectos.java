package com.company.Vistas;

import com.company.Controladores.ControladorProveedor;
import com.company.Controladores.ControladorProyecto;
import com.company.Proveedor;
import com.company.Proyecto;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class VistaProyectos {
    private JPanel JPGeneral, JPProyecto;
    private JLabel lbTitulo, lbCodigo, lbListaProyectos, lbId, lbIDProyecto, lbSupervisor, lbNombre, lbCiudad;
    private JButton bInsertar, bCancelar;
    private JTextField txtNombre, txtCiudad, txtSupervisor, txtCodigo;
    private JScrollPane spListado;
    private JList<Proyecto> lstProyectos;
    private JComboBox cbSupervisor;
    private ControladorProyecto controladorProyecto = new ControladorProyecto();
    private List<Proveedor> listadoProveedores = new ArrayList<>();



    public VistaProyectos(List<Proveedor> listadoProveedores) {

        if (listadoProveedores.size()==0) {
            JOptionPane.showMessageDialog(null, "No se han encontrado datos", "Resultado", JOptionPane.INFORMATION_MESSAGE);
        } else {
            cbSupervisor.removeAllItems(); //Limpiamos el combobox
            //rellenamos con los datos recibidos.
            cbSupervisor.setModel(new DefaultComboBoxModel(listadoProveedores.toArray()));

        }

        bCancelar.addActionListener(e -> autoDestroy());
        bInsertar.addActionListener(e -> {

            boolean ok;  // comprobamos en los controladores si ha pasado todas las validaciones cada objeto y si es true puede continuar

            Proyecto proyecto = new Proyecto();

            if (lbIDProyecto.isVisible()) {
                proyecto.setIdproyecto(Integer.parseInt(lbIDProyecto.getText()));

            }
            proyecto.setCodproyecto(txtCodigo.getText().toUpperCase());
            proyecto.setNombre(txtNombre.getText().toUpperCase());
            proyecto.setCiudad(txtCiudad.getText().toUpperCase());
            Proveedor p = (Proveedor)cbSupervisor.getSelectedItem();
            proyecto.setSupervisor(p.getIdproveedor());

            if (bInsertar.getText().equals("ELIMINAR")) {
                controladorProyecto.deleteProyecto(proyecto, proyecto.getIdproyecto());
                JOptionPane.showMessageDialog(null, "Se ha eliminado el proyecto.", "Info", JOptionPane.INFORMATION_MESSAGE);
                autoDestroy();

            } else if (bInsertar.getText().equalsIgnoreCase("INSERTAR") ||
                    (bInsertar.getText().equals("MODIFICAR"))) {

                ok = controladorProyecto.validaciones(proyecto);

            if (ok) {
                if (bInsertar.getText().equalsIgnoreCase("INSERTAR")) {
                    controladorProyecto.addProyecto(proyecto);
                    JOptionPane.showMessageDialog(null, "Se ha añadido el proyecto", "Info", JOptionPane.INFORMATION_MESSAGE);
                    autoDestroy();
                } else if (bInsertar.getText().equals("MODIFICAR")) {
                    proyecto.setIdproyecto(Integer.parseInt(lbIDProyecto.getText()));
                    controladorProyecto.editProyecto(proyecto, proyecto.getIdproyecto());
                    JOptionPane.showMessageDialog(null, "Se ha modificado el proyecto", "Info", JOptionPane.INFORMATION_MESSAGE);
                    autoDestroy();
                } else if (bInsertar.getText().equals("BAJA")) {
                    proyecto.setIdproyecto(Integer.parseInt(lbIDProyecto.getText()));
                    //  controladorProyecto.deleteProyecto(proyecto, proyecto.getIdproyecto());
                    JOptionPane.showMessageDialog(null, "Se ha eliminado el proyecto", "Info", JOptionPane.INFORMATION_MESSAGE);
                    autoDestroy();
                }
            }
            } else {
                JOptionPane.showMessageDialog(null, "Se han encontrado errores",
                        "Resultado", JOptionPane.ERROR_MESSAGE
                );
            }
        });

        lstProyectos.addListSelectionListener(e -> {
            Proyecto proyecto = lstProyectos.getSelectedValue();

            if (proyecto != null) {
                lbIDProyecto.setText(String.valueOf(proyecto.getIdproyecto()));
                txtCodigo.setText(proyecto.getCodproyecto());
                txtNombre.setText(proyecto.getNombre());
                txtCiudad.setText(proyecto.getCiudad());
                ControladorProveedor cp = new ControladorProveedor();
                System.out.println("Supervisor" + proyecto.getSupervisor());
                Proveedor proveedor = cp.selectProveedor(proyecto.getSupervisor());
                System.out.println(proveedor.toString());
                cbSupervisor.setSelectedItem(proveedor);
            }

        });


    }


    public JPanel getJPProyecto() {
        return JPProyecto;
    }

    public void autoDestroy() {
        JPProyecto.removeAll();
        JPProyecto.repaint();
    }

    public JScrollPane getSpListado() {
        return spListado;
    }

    public JLabel getLbListaProyectos() {
        return lbListaProyectos;
    }

    public void renombrar(String texto) {
        bInsertar.setText(texto);
    }

    public void mostrarProyectos(List<Proyecto> Proyectos) {
        DefaultListModel<Proyecto> modelo = new DefaultListModel<>();
        for (Proyecto proyecto : Proyectos) {
            modelo.addElement(proyecto);
        }
        lstProyectos.setModel(modelo);
    }

    public JLabel getLbId() {
        return lbId;
    }

    public JLabel getLbIDProyecto() {
        return lbIDProyecto;
    }
    public JComboBox getCbSupervisor() {
        return cbSupervisor;
    }


    public void setCbSupervisor(Proyecto p) {

    }
}
