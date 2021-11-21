package com.company.Vistas;

import com.company.Controladores.ControladorPieza;
import com.company.Pieza;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

public class VistaPiezas {
    private JPanel JPPieza, JPGeneral;
    private JLabel lbIDPieza, lbId, lbListaPiezas, lbTitulo, lbCodigo, lbNombre, lbPrecio, lbDescripcion;
    private JButton bInsertar, bCancelar;
    private JTextField txtNombre, txtPrecio, txtDescripcion, txtCodigo;
    private JList<Pieza> lstPiezas;
    private JScrollPane spListado;

    private final ControladorPieza controladorPieza = new ControladorPieza();

    public VistaPiezas() {


        bCancelar.addActionListener(e -> autoDestroy());
        bInsertar.addActionListener(e -> {

            boolean ok;  // comprobamos en los controladores si ha pasado todas las validaciones cada objeto y si es true puede continuar

            Pieza pieza = new Pieza();

            if (!(lbIDPieza.getText().equals(""))) {
                pieza.setIdpieza(Integer.parseInt(String.valueOf(lbIDPieza.getText())));
            }
            pieza.setCodpieza(txtCodigo.getText().toUpperCase());
            pieza.setNombre(txtNombre.getText().toUpperCase());
            pieza.setPrecio(Float.parseFloat(txtPrecio.getText()));
            pieza.setDescripcion(txtDescripcion.getText().toUpperCase());

            if (bInsertar.getText().equals("ELIMINAR")) {

                int puedoEliminarGestion = controladorPieza.isInGestion(pieza.getIdpieza());

                if (puedoEliminarGestion == 0) {
                    int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar la pieza?", "Alerta!", JOptionPane.YES_NO_OPTION);

                    if (resp == 0) {
                        controladorPieza.deletePieza(pieza, pieza.getIdpieza());
                        JOptionPane.showMessageDialog(null, "Se ha eliminado la pieza", "Info", JOptionPane.INFORMATION_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede eliminar la pieza", "Info", JOptionPane.INFORMATION_MESSAGE);

                }
                autoDestroy();
            } else if (bInsertar.getText().equalsIgnoreCase("INSERTAR") ||
                    (bInsertar.getText().equals("MODIFICAR"))) {
                ok = controladorPieza.validaciones(pieza);

                if (ok) {
                    if (bInsertar.getText().equalsIgnoreCase("INSERTAR")) {
                        controladorPieza.addPieza(pieza);
                        JOptionPane.showMessageDialog(null, "Se ha añadido la pieza", "Info", JOptionPane.INFORMATION_MESSAGE);
                        autoDestroy();
                    } else if (bInsertar.getText().equals("MODIFICAR")) {
                        pieza.setIdpieza(Integer.parseInt(lbIDPieza.getText()));
                        controladorPieza.editPieza(pieza, pieza.getIdpieza());
                        JOptionPane.showMessageDialog(null, "Se ha modificado la pieza", "Info", JOptionPane.INFORMATION_MESSAGE);
                        autoDestroy();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Se han encontrado errores",
                        "Resultado", JOptionPane.ERROR_MESSAGE
                );
            }
        });

        lstPiezas.addListSelectionListener(e -> {
            Pieza pieza = lstPiezas.getSelectedValue();
            if (pieza != null) {
                lbIDPieza.setText(String.valueOf(pieza.getIdpieza()));
                txtCodigo.setText(pieza.getCodpieza());
                txtNombre.setText(pieza.getNombre());
                txtPrecio.setText(String.valueOf(pieza.getPrecio()));
                txtDescripcion.setText(pieza.getDescripcion());
            }

        });

        // Comprobacion si pulsa determinadas teclas el usuario. No permite letras.
        txtPrecio.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                char validar = e.getKeyChar();
                if ((validar < '0' || validar > '9') && (validar < '.' || validar > '.')) {
                    txtPrecio.setText("");
                    JOptionPane.showMessageDialog(null, "Solo numeros y  punto . ");
                }
            }


        });


    }


    public JPanel getJPPieza() {
        txtPrecio.setText(String.valueOf(0.0));
        return JPPieza;
    }

    public void autoDestroy() {
        JPPieza.removeAll();
        JPPieza.repaint();
    }

    public JScrollPane getSpListado() {
        return spListado;
    }

    public JLabel getLbListaPiezas() {
        return lbListaPiezas;
    }

    public void renombrar(String texto) {
        bInsertar.setText(texto);
    }

    public void mostrarPiezas(List<Pieza> piezas) {
        DefaultListModel<Pieza> modelo = new DefaultListModel<>();
        for (Pieza pieza : piezas) {
            modelo.addElement(pieza);
        }
        lstPiezas.setModel(modelo);
    }

    public JLabel getLbId() {
        return lbId;
    }

    public JLabel getLbIDPieza() {
        return lbIDPieza;
    }
}
