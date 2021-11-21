package com.company.Controladores;

import com.company.Gestionglobal;
import com.company.Pieza;
import com.company.Proveedor;
import com.company.utils.HibernateUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.sql.ResultSet;
import java.util.*;

public class ControladorGestionGlobal {

    private Transaction tx;


    public ControladorGestionGlobal() {
    }

    public void addGestionGlobal(Gestionglobal gg) {
        SessionFactory sesionAdd = HibernateUtil.getSessionFactory();
        Session sessionAdd = sesionAdd.openSession();
        tx = sessionAdd.beginTransaction();
        sessionAdd.save(gg);
        tx.commit();
        sessionAdd.close();

    }

    public void editGestionGlobal(Gestionglobal gg, int idGG) {
        SessionFactory sesionEdit = HibernateUtil.getSessionFactory();
        Session sessionEdit = sesionEdit.openSession();
        Gestionglobal gestionGlobal;
        tx = sessionEdit.beginTransaction();
        gestionGlobal = sessionEdit.load(Gestionglobal.class, idGG);
        gestionGlobal.setIdGestionGlobal(gg.getIdGestionGlobal());
        gestionGlobal.setIdProveedor(gg.getIdProveedor());
        gestionGlobal.setIdPieza(gg.getIdPieza());
        gestionGlobal.setIdProyecto(gg.getIdProyecto());
        gestionGlobal.setCantidad(gg.getCantidad());


        sessionEdit.update(gestionGlobal);
        tx.commit();
        sessionEdit.close();
    }

    public void deleteGestionGlobal(Gestionglobal gg, int idGG) {
        SessionFactory sesionDelete = HibernateUtil.getSessionFactory();
        Session sessionDelete = sesionDelete.openSession();
        Gestionglobal gestionGlobal;
        tx = sessionDelete.beginTransaction();
        gestionGlobal = (Gestionglobal) sessionDelete.load(Gestionglobal.class, idGG);
        sessionDelete.delete(gg);
        tx.commit();
        sessionDelete.close();
    }

    public List<Gestionglobal> selectAll() {
        SessionFactory sesionSelectAll = HibernateUtil.getSessionFactory();
        Session sessionSelectAll = sesionSelectAll.openSession();
        Query q = sessionSelectAll.createQuery("from Gestionglobal ");

        List<Gestionglobal> listaGestiones = q.list();
        Iterator<Gestionglobal> iter = listaGestiones.iterator();
        List<Gestionglobal> enviarListaGestiones = new ArrayList<>();

        while (iter.hasNext()) {
            Gestionglobal gg = (Gestionglobal) iter.next();
            gg.setIdGestionGlobal(gg.getIdGestionGlobal());
            gg.setIdProveedor(gg.getIdProveedor());
            gg.setIdPieza(gg.getIdPieza());
            gg.setIdProyecto(gg.getIdProyecto());
            gg.setCantidad(gg.getCantidad());
            enviarListaGestiones.add(gg);

        }

        sessionSelectAll.close();
        return enviarListaGestiones;
    }

    public Gestionglobal selectGestionGlobal(int id) {
        SessionFactory sesionSelectId = HibernateUtil.getSessionFactory();
        Session sessionSelectId = sesionSelectId.openSession();
        Gestionglobal gg = new Gestionglobal();

        try {
            gg = (Gestionglobal) sessionSelectId.load(Gestionglobal.class, id);
            gg.setIdGestionGlobal(gg.getIdGestionGlobal());
            gg.setIdProveedor(gg.getIdProveedor());
            gg.setIdPieza(gg.getIdPieza());
            gg.setIdProyecto(gg.getIdProyecto());
            gg.setCantidad(gg.getCantidad());


        } catch (ObjectNotFoundException o) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado nada", "Error", JOptionPane.ERROR_MESSAGE);
        }

        sessionSelectId.close();
        return gg;
    }

    public boolean validaciones(Gestionglobal gg) {

        Boolean error = true;
        HashMap<String, String> errores = new HashMap<>();

        if (gg.getIdProveedor() < 0) {
            errores.put("Proveedor", "Hay que elegir un proveedor");
        }
        if (gg.getIdPieza() < 0) {
            errores.put("Pieza", "Hay que elegir una pieza");
        }
        if (gg.getIdProyecto() < 0) {
            errores.put("Proyecto", "Hay que elegir un proyecto");
        }
        if (gg.getCantidad() < 0) {
            errores.put("Cantidad", "La cantidad debe ser mayor de 0");
        }

        StringBuilder texto = new StringBuilder();
        if (errores.size() > 0) {
            for (Map.Entry<String, String> entry : errores.entrySet()) {
                String k = entry.getKey();
                String v = entry.getValue();
                texto.append(v + "\n");
            }

            JOptionPane.showMessageDialog(null, texto, "Errores", JOptionPane.ERROR_MESSAGE);
            return error = false;
        }
        return error;


    }

    public int piezasPorProveedor(int proveedor) {

        int totalPiezas = 0;

        SessionFactory sesionpiezasPorProveedor = HibernateUtil.getSessionFactory();
        Session sessionpiezasPorProveedor = sesionpiezasPorProveedor.openSession();

        String consultaporpiezas = "select sum(cantidad) from Gestionglobal where idProveedor = " + proveedor;

        Query q = sessionpiezasPorProveedor.createSQLQuery(consultaporpiezas);

        if (q.list().get(0) != null) {
            totalPiezas = Integer.parseInt(q.list().get(0).toString());
        }

        q.list().clear();
        sessionpiezasPorProveedor.close();


        return totalPiezas;
    }

    public int proyectosPorProveedor(int proveedor) {

        int totalproyectos = 0;

        SessionFactory sesionproyectosPorProveedor = HibernateUtil.getSessionFactory();
        Session sessionproyectosPorProveedor = sesionproyectosPorProveedor.openSession();

        String consultaproyectosPorProveedor = "select count(idProyecto) from Gestionglobal where idProveedor = " + proveedor;

        Query queryproyectosPorProveedor = sessionproyectosPorProveedor.createSQLQuery(consultaproyectosPorProveedor);

        if (queryproyectosPorProveedor.list().get(0) != null) {
            totalproyectos = Integer.parseInt(queryproyectosPorProveedor.list().get(0).toString());

        }
        queryproyectosPorProveedor.list().clear();
        sessionproyectosPorProveedor.close();

        return totalproyectos;
    }

    public int proveedoresPorPiezas(int pieza) {

        int totalproveedores = 0;

        SessionFactory sesionproveedoresPorPiezas = HibernateUtil.getSessionFactory();
        Session sessionproveedoresPorPiezas = sesionproveedoresPorPiezas.openSession();

        String consultaporpiezas = "select count(idProveedor) from Gestionglobal where idPieza = " + pieza;

        Query q = sessionproveedoresPorPiezas.createSQLQuery(consultaporpiezas);

        if (q.list().get(0) != null) {
            totalproveedores = Integer.parseInt(q.list().get(0).toString());
        }

        q.list().clear();
        sessionproveedoresPorPiezas.close();


        return totalproveedores;
    }

    public int proyectosPorPieza(int pieza) {

        int totalproyectos = 0;

        SessionFactory sesionproyectosPorPieza = HibernateUtil.getSessionFactory();
        Session sessionproyectosPorPieza = sesionproyectosPorPieza.openSession();

        String consultaproyectosPorPieza = "select count(idProyecto) from Gestionglobal where idPieza = " + pieza;

        Query queryproyectosPorPieza = sessionproyectosPorPieza.createSQLQuery(consultaproyectosPorPieza);

        if (queryproyectosPorPieza.list().get(0) != null) {
            totalproyectos = Integer.parseInt(queryproyectosPorPieza.list().get(0).toString());

        }
        queryproyectosPorPieza.list().clear();
        sessionproyectosPorPieza.close();

        return totalproyectos;
    }

    public int totalPieza(int pieza) {

        int totalPiezas = 0;

        SessionFactory sesionpiezas = HibernateUtil.getSessionFactory();
        Session sessionpiezas = sesionpiezas.openSession();

        String consultapiezas = "select sum(cantidad) from Gestionglobal where idPieza = " + pieza;

        Query q = sessionpiezas.createSQLQuery(consultapiezas);

        if (q.list().get(0) != null) {
            totalPiezas = Integer.parseInt(q.list().get(0).toString());
        }

        q.list().clear();
        sessionpiezas.close();


        return totalPiezas;

    }


    /*funciones que muestran datos en las tablas de piezas y proveedores*/

    public List<Object[]> tablaProveedor(int proveedor) {
        SessionFactory sesiontablaProveedor = HibernateUtil.getSessionFactory();
        Session sessiontablaProveedor = sesiontablaProveedor.openSession();

        String consultaTablaProveedores = "select Proyecto.codproyecto, proyecto.NOMBRE as NOMBREPROYECTO, pieza.NOMBRE AS NOMBREPIEZA, pieza.PRECIO, " +
                "Gestionglobal.cantidad from pieza, proyecto, proveedor, Gestionglobal" +
                " WHERE Gestionglobal.idPieza = pieza.IDPIEZA and " +
                " Gestionglobal.idProyecto = proyecto.idproyecto and " +
                "Gestionglobal.idProveedor = proveedor.IDPROVEEDOR and  Gestionglobal.idProveedor = " + proveedor;

        Query consultatabla = sessiontablaProveedor.createSQLQuery(consultaTablaProveedores);
        List<Object[]> listado = consultatabla.list();

        sessiontablaProveedor.close();

        return listado;

    }

    public List<Object[]> tablaPiezas(int pieza) {
        SessionFactory sesiontablaPieza = HibernateUtil.getSessionFactory();
        Session sessiontablaPieza = sesiontablaPieza.openSession();

        String consultaTablaPieza = "select Proyecto.codproyecto, proyecto.NOMBRE as NOMBREPROYECTO, proveedor.NOMBRE as NOMBREPROVEEDOR, " +
                "Gestionglobal.cantidad from pieza, proyecto, proveedor, Gestionglobal" +
                " WHERE Gestionglobal.idPieza = pieza.IDPIEZA and " +
                " Gestionglobal.idProyecto = proyecto.idproyecto and " +
                "Gestionglobal.idProveedor = proveedor.IDPROVEEDOR and  Gestionglobal.idPieza = " + pieza;

        Query consultatablaPiezas = sessiontablaPieza.createSQLQuery(consultaTablaPieza);
        List<Object[]> listadoPiezas = consultatablaPiezas.list();

        sessiontablaPieza.close();

        return listadoPiezas;

    }

    public List<Object[]> tablaEstadisticas() {
        SessionFactory sesionEstadisticas = HibernateUtil.getSessionFactory();
        Session sessionEstadisticas = sesionEstadisticas.openSession();

        String consultaEstadisticas = "select proveedor.codproveedor, proveedor.nombre, proveedor.apellidos, sum(gestionglobal.cantidad)," +
                " count(proyecto.idproyecto)from gestionglobal, proyecto,proveedor where gestionglobal.idProyecto=proyecto.idproyecto " +
                "and    gestionglobal.idProveedor=proveedor.IDPROVEEDOR group by CODPROVEEDOR";

        Query qconsultaEstadisticas = sessionEstadisticas.createSQLQuery(consultaEstadisticas);
        List<Object[]> listadoEstadisticas = qconsultaEstadisticas.list();

        sessionEstadisticas.close();

        return listadoEstadisticas;

    }

    /*funciones para las estadisticas*/

    public HashMap masSuministradaCantidad() {

        HashMap<String, String> resultado = new HashMap();
        SessionFactory sesionPiezaMasSuministrada = HibernateUtil.getSessionFactory();
        Session sessiontPiezaMasSuministrada = sesionPiezaMasSuministrada.openSession();


        String piezaMasSuministrada = "select  distinct(pieza.nombre) as nombrepieza, sum(gestionglobal.cantidad) as total from gestionglobal, pieza, proyecto where gestionglobal.idProveedor=pieza.IDPIEZA and gestionglobal.idProyecto=proyecto.idproyecto group by gestionglobal.idProyecto  order by total desc limit 1";

        Query consultapiezaMasSuministrada = sessiontPiezaMasSuministrada.createSQLQuery(piezaMasSuministrada);

        List<Object[]> result = consultapiezaMasSuministrada.list();
        Object[] row = (Object[]) result.get(0);
        String key = String.valueOf(row[0]);
        String value = String.valueOf(row[1]);

        resultado.put(key, value);

        sessiontPiezaMasSuministrada.close();

        return resultado;

    }

    public HashMap piezaMasProyectos() {
        HashMap<String, String> resultadopiezaMasProyectos = new HashMap();
        SessionFactory sesionpiezaMasProyectos = HibernateUtil.getSessionFactory();
        Session sessionpiezaMasProyectos = sesionpiezaMasProyectos.openSession();

        String piezaMasProyectos = "select count(*), proyecto.nombre as nombreproyecto, sum(cantidad) from gestionglobal, proyecto where gestionglobal.idProyecto=proyecto.idproyecto group by proyecto.nombre order by count(*) desc limit 1";

        Query qpiezaMasProyectos = sessionpiezaMasProyectos.createSQLQuery(piezaMasProyectos);

        List<Object[]> results = qpiezaMasProyectos.list();
        Object[] rows = (Object[]) results.get(0);
        String keys = String.valueOf(rows[1]);
        String values = String.valueOf(rows[2]);

        resultadopiezaMasProyectos.put(keys, values);

        sessionpiezaMasProyectos.close();

        return resultadopiezaMasProyectos;


    }

    public HashMap proveedorMasPiezasSuministra() {
        HashMap<String, String> resultadoMaspiezasSuministra = new HashMap();
        SessionFactory sesionproveedorMasPiezasSuministra = HibernateUtil.getSessionFactory();
        Session sessionproveedorMasPiezasSuministra = sesionproveedorMasPiezasSuministra.openSession();

        String proveedorMasPiezasSuministra = "select proveedor.nombre as nombreProveedor,sum(gestionglobal.cantidad) as total  from gestionglobal, proveedor where gestionglobal.idProveedor=proveedor.IDPROVEEDOR        group by gestionglobal.idProveedor        order by total desc limit 1";

        Query qproveedorMasPiezasSuministra = sessionproveedorMasPiezasSuministra.createSQLQuery(proveedorMasPiezasSuministra);

        List<Object[]> result = qproveedorMasPiezasSuministra.list();
        Object[] row = (Object[]) result.get(0);
        String key = String.valueOf(row[0]);
        String value = String.valueOf(row[1]);

        resultadoMaspiezasSuministra.put(key, value);

        sessionproveedorMasPiezasSuministra.close();

        return resultadoMaspiezasSuministra;


    }

    public HashMap proveedorMasProyectos() {
        HashMap<String, String> resultadoMasProyectos = new HashMap();
        SessionFactory sesionMasProyectos = HibernateUtil.getSessionFactory();
        Session sessionMasProyectos = sesionMasProyectos.openSession();

        String MasProyectos = "select  proveedor.nombre,\n" +
                "        count(proyecto.idproyecto)\n" +
                "from gestionglobal, proyecto,proveedor\n" +
                "where gestionglobal.idProyecto=proyecto.idproyecto and\n" +
                "      gestionglobal.idProveedor=proveedor.IDPROVEEDOR\n" +
                "group by CODPROVEEDOR order by count(proyecto.idproyecto) desc limit 1";

        Query qMasProyectos = sessionMasProyectos.createSQLQuery(MasProyectos);

        List<Object[]> result = qMasProyectos.list();
        Object[] row = (Object[]) result.get(0);
        String key = String.valueOf(row[0]);
        String value = String.valueOf(row[1]);

        resultadoMasProyectos.put(key, value);

        sessionMasProyectos.close();

        return resultadoMasProyectos;


    }

}
