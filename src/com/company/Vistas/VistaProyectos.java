package com.company.Vistas;

import com.company.Controladores.ControladorProyecto;
import com.company.Proyecto;

import java.util.List;
import javax.swing.*;

public class VistaProyectos {
    private JPanel JPGeneral, JPProyecto;
    private JLabel lbTitulo, lbCodigo, lbListaProyectos, lbId, lbIDProyecto, lbSupervisor, lbNombre, lbCiudad;
    private JButton bInsertar, bCancelar;
    private JTextField txtNombre, txtCiudad, txtSupervisor, txtCodigo;
    private JScrollPane spListado;
    private JList<Proyecto> lstProyectos;
    private ControladorProyecto controladorProyecto = new ControladorProyecto();

    public VistaProyectos() {


        bCancelar.addActionListener(e -> autoDestroy());
        bInsertar.addActionListener(e -> {

            boolean ok;  // comprobamos en los controladores si ha pasado todas las validaciones cada objeto y si es true puede continuar

            Proyecto proyecto = new Proyecto();

            if (lbIDProyecto.isVisible()) {
                proyecto.setIdproyecto(Integer.parseInt(String.valueOf(lbIDProyecto.getText())));
            }
            proyecto.setCodproyecto(txtCodigo.getText().toUpperCase());
            proyecto.setNombre(txtNombre.getText().toUpperCase());
            proyecto.setCiudad(txtCiudad.getText().toUpperCase());
            proyecto.setSupervisor(txtSupervisor.getText().toUpperCase());
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
                txtSupervisor.setText(proyecto.getSupervisor());

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

}
