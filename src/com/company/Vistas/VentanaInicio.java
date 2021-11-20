package com.company.Vistas;

import com.company.Controladores.*;
import com.company.Pieza;
import com.company.Proveedor;
import com.company.Proyecto;
import com.company.utils.HibernateUtil;

import org.hibernate.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.MarshalledObject;
import java.util.ArrayList;
import java.util.List;


public class VentanaInicio {


    private JPanel JPGeneral;
    private JPanel JPVacio;
    private ControladorPieza cpieza = new ControladorPieza();
    private ControladorProveedor cproveedor = new ControladorProveedor();
    private ControladorProyecto cproyecto = new ControladorProyecto();
    private ControladorGestionGlobal cGG = new ControladorGestionGlobal();
    private List<Proveedor> listadoProveedores = new ArrayList<>();
    private List<Pieza> listadoPiezas = new ArrayList<>();
    private List<Proyecto> listadoProyectos = new ArrayList<>();


    public VentanaInicio(JFrame frame) {

        JMenuBar menuBar = new JMenuBar();

        /********************Menu Base de datos*****************/
        JMenu MenuBaseDatos = new JMenu("Base de Datos");
        JMenuItem mostrarBD = new JMenuItem("Base de datos");
        MenuBaseDatos.add(mostrarBD);
        menuBar.add(MenuBaseDatos);

        /********************Menu Pieza*****************/
        JMenu MenuPiezas = new JMenu("Pieza");

        JMenuItem itemAltaPieza = new JMenuItem("Alta");
        JMenuItem itemModificacionPieza = new JMenuItem("Modificacion");
        JMenuItem itemBajaPieza = new JMenuItem("Eliminar");
        JMenuItem itemListadoPieza = new JMenuItem("Listado");
        JMenu itemConsultaPiezas = new JMenu("Consulta de Pieza");

        JMenuItem itemCodigoPi = new JMenuItem("Por Codigo");
        JMenuItem itemNombrePi = new JMenuItem("Por Nombre");

        MenuPiezas.add(itemAltaPieza);
        MenuPiezas.add(itemModificacionPieza);
        MenuPiezas.add(itemBajaPieza);
        MenuPiezas.add(itemListadoPieza);

        itemConsultaPiezas.add(itemCodigoPi);
        itemConsultaPiezas.add(itemNombrePi);

        MenuPiezas.add(itemConsultaPiezas);
        menuBar.add(MenuPiezas);

        /***********************Menu Proveedor*********************/
        JMenu MenuProveedores = new JMenu("Proveedores");
        JMenuItem itemAltaProveedor = new JMenuItem("Alta");
        JMenuItem itemBajaProveedor = new JMenuItem("Eliminar");
        JMenuItem itemListadoProveedor = new JMenuItem("Listado");
        JMenuItem itemModificacionProveedor = new JMenuItem("Modificacion");
        JMenu itemConsultaProveedores = new JMenu("Consulta de Proveedores");

        JMenuItem itemCodigoProv = new JMenuItem("Por Codigo");
        JMenuItem itemNombreProv = new JMenuItem("Por Nombre");
        JMenuItem itemDireccionProv = new JMenuItem("Por Dirección");

        MenuProveedores.add(itemAltaProveedor);
        MenuProveedores.add(itemModificacionProveedor);
        MenuProveedores.add(itemBajaProveedor);
        MenuProveedores.add(itemListadoProveedor);

        itemConsultaProveedores.add(itemCodigoProv);
        itemConsultaProveedores.add(itemNombreProv);
        itemConsultaProveedores.add(itemDireccionProv);
        MenuProveedores.add(itemConsultaProveedores);
        menuBar.add(MenuProveedores);

        /***********************Menu Proyectos*********************/

        JMenu MenuProyectos = new JMenu("Proyectos");
        JMenuItem itemAltaProyecto = new JMenuItem("Alta");
        JMenuItem itemModificacionProyecto = new JMenuItem("Modificacion");
        JMenuItem itemBajaProyecto = new JMenuItem("Eliminar");
        JMenuItem itemListadoProyecto = new JMenuItem("Listado");
        JMenu itemConsultaProyectos = new JMenu("Consulta de Proyectos");

        JMenuItem itemCodigoProy = new JMenuItem("Por Codigo");
        JMenuItem itemNombreProy = new JMenuItem("Por Nombre");
        JMenuItem itemCiudadProy = new JMenuItem("Por Ciudad");

        MenuProyectos.add(itemAltaProyecto);
        MenuProyectos.add(itemModificacionProyecto);
        MenuProyectos.add(itemBajaProyecto);
        MenuProyectos.add(itemListadoProyecto);

        itemConsultaProyectos.add(itemCodigoProy);
        itemConsultaProyectos.add(itemNombreProy);
        itemConsultaProyectos.add(itemCiudadProy);
        MenuProyectos.add(itemConsultaProyectos);
        menuBar.add(MenuProyectos);

        /**
        /***********************Menu Gestion Global*********************/

        JMenu MenuGestionGlobal = new JMenu("Gestión Global");
        JMenuItem itemAltaGestionGlobal = new JMenuItem("Alta");
        JMenuItem itemModificacionGestionGlobal = new JMenuItem("Modificacion");
        JMenuItem itemBajaGestionGlobal = new JMenuItem("Eliminar");
        JMenuItem itemListadoGestionGlobal = new JMenuItem("Listado");

        MenuGestionGlobal.add(itemAltaGestionGlobal);
        MenuGestionGlobal.add(itemModificacionGestionGlobal);
        MenuGestionGlobal.add(itemBajaGestionGlobal);
        MenuGestionGlobal.add(itemListadoGestionGlobal);

        menuBar.add(MenuGestionGlobal);

        /***************************************************************************/


        JMenu Varios = new JMenu("Varios");

        JMenuItem itemSuministrosProveedor = new JMenuItem("Suministros por Proveedor");
        JMenuItem itemSuministrosPiezas = new JMenuItem("Suministros por Pieza");
        JMenuItem itemEstadisticas = new JMenuItem("Estadísticas");


        Varios.add(itemSuministrosProveedor);
        Varios.add(itemSuministrosPiezas);
        Varios.add(itemEstadisticas);

        menuBar.add(Varios);

        JMenu MenuAyuda = new JMenu("Ayuda");
        JMenuItem mostrarAyuda = new JMenuItem("Ayuda");
        MenuAyuda.add(mostrarAyuda);

        menuBar.add(MenuAyuda);

        frame.add(menuBar); //Añadir el menu bar al frame. Se tiene que añadir al frame principal porque de este se arrastra a todos.
        frame.setJMenuBar(menuBar);

        /***************************** ACCIONES EN LOS MENUS ****************************/


        /**Cada vez que pulsemos en un item nos abrirá el panel inferior nuevo con los campos correspondientes a la tabla*/

        mostrarAyuda.addActionListener(e -> {
            Ayuda ayuda = new Ayuda();
            mostrarPanel(ayuda.getJPAyuda());
        });
        mostrarBD.addActionListener(e -> {
            BaseDatos bd = new BaseDatos();
            mostrarPanel(bd.getJPBaseDatos());
        });
        /**************************** PROVEEDORES ****************************************/


        itemAltaProveedor.addActionListener(e -> {
            VistaProveedores alta = new VistaProveedores();
            alta.getSpListado().setVisible(false);
            alta.getLbListaProveedores().setVisible(false);
            alta.getLbId().setVisible(false);
            alta.getLbIDProveedor().setVisible(false);
            //   cproveedor = new ControladorProveedor();
            mostrarPanel(alta.getJPProveedor());

        });
        itemModificacionProveedor.addActionListener(e -> {
            VistaProveedores modificacion = new VistaProveedores();
            modificacion.renombrar("MODIFICAR");
            //  cproveedor = new ControladorProveedor();
            modificacion.mostrarProveedores(cproveedor.selectAll());
            if (cproveedor.selectAll().size() > 0) {
                mostrarPanel(modificacion.getJPProveedor());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        itemBajaProveedor.addActionListener(e -> {
            VistaProveedores eliminar = new VistaProveedores();
            eliminar.renombrar("ELIMINAR");
            eliminar.mostrarProveedores(cproveedor.selectAll());
            if (cproveedor.selectAll().size() > 0) {
                mostrarPanel(eliminar.getJPProveedor());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        itemListadoProveedor.addActionListener(e -> {
            ListarProveedores listado = new ListarProveedores();
            //   cproveedor = new ControladorProveedor();
            listado.setListaProveedores(cproveedor.selectAll());
            if (cproveedor.selectAll().size() > 0) {
                mostrarPanel(listado.getJPProveedorListado());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);

            }

        });
        itemCodigoProv.addActionListener(e -> {
            Buscar buscarProveedorXCodigo = new Buscar();
            buscarProveedorXCodigo.consulta = "codproveedor";
            buscarProveedorXCodigo.tabla = "Proveedor";
            mostrarPanel(buscarProveedorXCodigo.getJPBuscar());
        });
        itemNombreProv.addActionListener(e -> {
            Buscar buscarProveedorXNombre = new Buscar();
            buscarProveedorXNombre.consulta = "nombre";
            buscarProveedorXNombre.tabla = "Proveedor";
            mostrarPanel(buscarProveedorXNombre.getJPBuscar());
        });
        itemDireccionProv.addActionListener(e -> {
            Buscar buscarProveedorXDireccion = new Buscar();
            buscarProveedorXDireccion.consulta = "direccion";
            buscarProveedorXDireccion.tabla = "Proveedor";
            mostrarPanel(buscarProveedorXDireccion.getJPBuscar());
        });


        /**************************** PIEZAS ****************************************/

        itemAltaPieza.addActionListener(e -> {
            VistaPiezas alta = new VistaPiezas();
            alta.getSpListado().setVisible(false);
            alta.getLbListaPiezas().setVisible(false);
            alta.getLbId().setVisible(false);
            alta.getLbIDPieza().setVisible(false);
            //cpieza = new ControladorPieza();
            mostrarPanel(alta.getJPPieza());
        });
        itemModificacionPieza.addActionListener(e -> {
            /*Necesito recuperar todas las piezas para mostrar en el listado
             * */
            VistaPiezas modificacion = new VistaPiezas();
            modificacion.renombrar("MODIFICAR");
            modificacion.getSpListado().setVisible(true);
            modificacion.getLbListaPiezas().setVisible(true);

            modificacion.mostrarPiezas(cpieza.selectAll());
            if (cpieza.selectAll().size() > 0) {
                mostrarPanel(modificacion.getJPPieza());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);

            }


        });
        itemBajaPieza.addActionListener(e -> {
            VistaPiezas eliminar = new VistaPiezas();
            eliminar.renombrar("ELIMINAR");
            eliminar.mostrarPiezas(cpieza.selectAll());
            if (cpieza.selectAll().size() > 0) {
                mostrarPanel(eliminar.getJPPieza());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        itemListadoPieza.addActionListener(e -> {
            /*Necesito recuperar todas las piezas para mostrar en el listado
             * */
            ListarPiezas listado = new ListarPiezas();
            //cpieza = new ControladorPieza();
            listado.setListaPiezas(cpieza.selectAll());
            if (cpieza.selectAll().size() > 0) {
                mostrarPanel(listado.getJPPiezaListado());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);

            }


        });
        itemCodigoPi.addActionListener(e -> {

            Buscar buscarPiezaXCodigo = new Buscar();
            buscarPiezaXCodigo.tabla = "Pieza";
            buscarPiezaXCodigo.consulta = "codpieza";
            mostrarPanel(buscarPiezaXCodigo.getJPBuscar());

        });
        itemNombrePi.addActionListener(e -> {
            Buscar buscarPiezaXNombre = new Buscar();
            buscarPiezaXNombre.tabla = "Pieza";
            buscarPiezaXNombre.consulta = "nombre";
            mostrarPanel(buscarPiezaXNombre.getJPBuscar());
        });


        /*********************************** PROYECTOS *********************************/
        itemAltaProyecto.addActionListener(e -> {
            listadoProveedores = cproveedor.selectAll();
            VistaProyectos alta = new VistaProyectos(listadoProveedores);
            alta.renombrar("INSERTAR");
            alta.getSpListado().setVisible(false);
            alta.getLbListaProyectos().setVisible(false);
            alta.getLbId().setVisible(false);
            alta.getLbIDProyecto().setVisible(false);
            mostrarPanel(alta.getJPProyecto());

        });
        itemModificacionProyecto.addActionListener(e -> {
            listadoProveedores = cproveedor.selectAll();
            VistaProyectos modificacion = new VistaProyectos(listadoProveedores);
            modificacion.renombrar("MODIFICAR");
            modificacion.mostrarProyectos(cproyecto.selectAll());
            if (cproyecto.selectAll().size() > 0) {
                mostrarPanel(modificacion.getJPProyecto());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);

            }

        });
        itemBajaProyecto.addActionListener(e -> {
            listadoProveedores = cproveedor.selectAll();
            VistaProyectos eliminar = new VistaProyectos(listadoProveedores);
            eliminar.renombrar("ELIMINAR");
            eliminar.mostrarProyectos(cproyecto.selectAll());
            if (cproyecto.selectAll().size() > 0) {
                mostrarPanel(eliminar.getJPProyecto());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);
            }

        });
        itemListadoProyecto.addActionListener(e -> {
            ListarProyectos listado = new ListarProyectos();
            listado.setListaProyectos(cproyecto.selectAll());

            if (cproyecto.selectAll().size() > 0) {
                mostrarPanel(listado.getJPProyectoListado());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);

            }

        });
        itemCodigoProy.addActionListener(e -> {
            Buscar buscarProyectoXCodigo = new Buscar();
            buscarProyectoXCodigo.consulta = "codproyecto";
            buscarProyectoXCodigo.tabla = "Proyecto";
            mostrarPanel(buscarProyectoXCodigo.getJPBuscar());
        });
        itemNombreProy.addActionListener(e -> {
            Buscar buscarProyectoXNombre = new Buscar();
            buscarProyectoXNombre.consulta = "Nombre";
            buscarProyectoXNombre.tabla = "Proyecto";
            mostrarPanel(buscarProyectoXNombre.getJPBuscar());
        });
        itemCiudadProy.addActionListener(e -> {
            Buscar buscarProyectoXCiudad = new Buscar();
            buscarProyectoXCiudad.consulta = "Ciudad";
            buscarProyectoXCiudad.tabla = "Proyecto";
            mostrarPanel(buscarProyectoXCiudad.getJPBuscar());
        });


        /******************************* Menu Gestion Global  ********************************/

        itemAltaGestionGlobal.addActionListener(e -> {
            listadoProveedores = cproveedor.selectAll();
            listadoPiezas = cpieza.selectAll();
            listadoProyectos = cproyecto.selectAll();
            VistaGestionGlobal alta = new VistaGestionGlobal(listadoProveedores, listadoPiezas, listadoProyectos);
            alta.renombrar("INSERTAR");
            alta.getTxtIdGG().setVisible(false);
            alta.getLbId().setVisible(false);
            mostrarPanel(alta.getJPGestionGlobal());

        });
        itemModificacionGestionGlobal.addActionListener(e -> {
            listadoProveedores = cproveedor.selectAll();
            listadoPiezas = cpieza.selectAll();
            listadoProyectos = cproyecto.selectAll();
            VistaGestionGlobal modificacion = new VistaGestionGlobal(listadoProveedores, listadoPiezas, listadoProyectos);
            modificacion.mostrarGestiones(cGG.selectAll());
            modificacion.renombrar("MODIFICAR");
            modificacion.getCbPieza().setEnabled(false);
            modificacion.getCbProveedor().setEnabled(false);
            modificacion.getCbProyecto().setEnabled(false);
            if (cGG.selectAll().size() > 0) {
                                mostrarPanel(modificacion.getJPGestionGlobal());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);

            }

        });
        itemBajaGestionGlobal.addActionListener(e -> {
            listadoProveedores = cproveedor.selectAll();
            listadoPiezas = cpieza.selectAll();
            listadoProyectos = cproyecto.selectAll();
            VistaGestionGlobal eliminar = new VistaGestionGlobal(listadoProveedores, listadoPiezas, listadoProyectos);
            eliminar.renombrar("ELIMINAR");
            eliminar.getCbPieza().setEnabled(false);
            eliminar.getCbProveedor().setEnabled(false);
            eliminar.getCbProyecto().setEnabled(false);
            eliminar.getTxtCantidad().setEnabled(false);

            eliminar.mostrarGestiones(cGG.selectAll());
            if (cGG.selectAll().size() > 0) {
                mostrarPanel(eliminar.getJPGestionGlobal());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);
            }

        });
        itemListadoGestionGlobal.addActionListener(e -> {
           /* ListarGestionGlobal listado = new ListarGestionGlobal();
            listado.setListaGestionGlobal(cGestionGlobal.selectAll());

            if (cGestionGlobal.selectAll().size() > 0) {
                mostrarPanel(listado.getJPGestionGlobalListado());
            } else {
                JOptionPane.showMessageDialog(null, "No hay datos que mostrar", "OH!", JOptionPane.INFORMATION_MESSAGE);

            }*/

        });

        /********************************* VARIOS *********************************************/


        itemSuministrosProveedor.addActionListener(e -> {
            listadoProveedores = cproveedor.selectAll();
            SuministrosProveedor sproveedor = new SuministrosProveedor(listadoProveedores);
            mostrarPanel(sproveedor.getJPSuministrosProveedor());

        });

        itemSuministrosPiezas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {


            }
        });

        itemEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


    }

    /**
     * Esta funcion nos permite reutilizar el frame y solo cambiar la parte inferior donde aparecen las pantallas de cliente
     * empleado, espectaculo....
     */
    public void mostrarPanel(JPanel panel) {

        JPVacio.removeAll();
        JPVacio.add(panel);
        JPVacio.repaint();
        JPVacio.revalidate();

    }


    public JPanel getJPGeneral() {
        return JPGeneral;
    }

    public JPanel getJPVacio() {
        return JPVacio;
    }

    public static void main(String[] args) {
        /**
         * Arrancamos la aplicacion desde este punto.
         * */
        SessionFactory sesion = HibernateUtil.getSessionFactory();
        Session session = sesion.openSession();

        JFrame frame = new JFrame("El taller de Mª Jose");


        /*Añadimos un listener al frame principal para que cierre la conexion de
         * la base de datos que esté siendo usada.
         */

        //desde el frame estoy arrancado el menu para elegir la base de datos = parque.
        frame.setContentPane(new VentanaInicio(frame).JPGeneral);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


}


