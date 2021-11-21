package com.company.Vistas;

import com.company.Controladores.ControladorGestionGlobal;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Estadisticas {
    private JPanel JPEstadisticas, JPGeneral;
    private JLabel lTitulo, lPiezaCantidad, lProvProyectosCantidad, lPiezaProyecto, lProvCantidad;
    private JButton bProyectos, bVolver, bProveedores;
    private JTextField txtPiezaCantidad, txtPiezaNombre, txtPiezaProyectosCantidad, txtPiezaProyectos,
            txtProvCantidad, txtNombreProveedor, txtProveedorProyectos, txtProvProyectosCantidad;
    private JScrollPane JSTabla;
    private ControladorGestionGlobal cGG = new ControladorGestionGlobal();
    private HashMap<String, String> masPiezas = new HashMap();
    private HashMap<String, String> masPiezasProyectos = new HashMap();
    private HashMap<String, String> masPiezasporProveedor = new HashMap();
    private HashMap<String, String> masProyectos = new HashMap();
    private JTable tbEstadisticas;


    public Estadisticas() {
        bProyectos.addActionListener(e -> {
            masPiezas = cGG.masSuministradaCantidad();
            String k = ""; // nombre pieza
            String v = ""; // camtidad
            for (Map.Entry<String, String> entry : masPiezas.entrySet()) {
                k = entry.getKey();
                v = entry.getValue();

            }
            txtPiezaNombre.setText(k);
            txtPiezaCantidad.setText(v);

            masPiezasProyectos = cGG.piezaMasProyectos();
            String key = ""; // nombre pieza
            String value = ""; // camtidad
            for (Map.Entry<String, String> entry : masPiezasProyectos.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();

            }
            txtPiezaNombre.setText(k);
            txtPiezaCantidad.setText(v);
            txtPiezaProyectosCantidad.setText(value);
            txtPiezaProyectos.setText(key);

        });
        bProveedores.addActionListener(e -> {

            masPiezasporProveedor = cGG.proveedorMasPiezasSuministra();
            String k = ""; // nombre proveedor
            String v = ""; // cantidad
            for (Map.Entry<String, String> entry : masPiezasporProveedor.entrySet()) {
                k = entry.getKey();
                v = entry.getValue();

            }
            txtNombreProveedor.setText(k);
            txtProvCantidad.setText(v);

            masProyectos = cGG.proveedorMasProyectos();
            String key = ""; // nombre proveedor
            String value = ""; // cantidad
            for (Map.Entry<String, String> entry : masProyectos.entrySet()) {
                key = entry.getKey();
                value = entry.getValue();

            }
            txtProveedorProyectos.setText(key);
            txtProvProyectosCantidad.setText(value);


            tbEstadisticas = new JTable();
            JSTabla.setViewportView(tbEstadisticas);
            List<Object[]> resultadoEstadisticas = cGG.tablaEstadisticas();
            tbEstadisticas.setModel(new TablaEstadisticas(resultadoEstadisticas));

        });
        bVolver.addActionListener(e -> {
            autoDestroy();
        });
    }

    public JPanel getJPEstadisticas() {
        return JPEstadisticas;
    }

    public void autoDestroy() {
        JPEstadisticas.removeAll();
        JPEstadisticas.repaint();
    }
}
