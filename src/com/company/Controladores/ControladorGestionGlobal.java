package com.company.Controladores;

import com.company.Gestionglobal;
import com.company.Proveedor;
import com.company.utils.HibernateUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
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

        Query queryproyectosPorProveedor = sessionproyectosPorProveedor.createQuery(consultaproyectosPorProveedor);
       // System.out.println(queryproyectosPorProveedor.getQueryString());

      //  System.out.println("Query: " + queryproyectosPorProveedor.list().get(0));

        if (queryproyectosPorProveedor.list().get(0) != null) {
            totalproyectos = Integer.parseInt(queryproyectosPorProveedor.list().get(0).toString());

        }
        queryproyectosPorProveedor.list().clear();
        sessionproyectosPorProveedor.close();

        return totalproyectos;
    }


}
