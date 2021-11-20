package com.company.Controladores;

import com.company.Pieza;
import com.company.Proveedor;
import com.company.utils.HibernateUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.*;

public class ControladorPieza {

    private Pieza pieza;
    private Transaction tx;
    SessionFactory sesion = HibernateUtil.getSessionFactory();
    Session session = sesion.openSession();


    public ControladorPieza(Session session) {
        this.pieza = new Pieza();
        this.session = session;
    }

    public ControladorPieza() {
    }

    public void addPieza(Pieza pieza) {
        SessionFactory sesionAdd = HibernateUtil.getSessionFactory();
        Session sessionAdd = sesionAdd.openSession();
        tx = sessionAdd.beginTransaction();
        sessionAdd.save(pieza);
        tx.commit();
        sessionAdd.close();
    }

    public void editPieza(Pieza pieza, int Idpieza) {
        SessionFactory sesionEdit = HibernateUtil.getSessionFactory();
        Session sessionEdit = sesionEdit.openSession();
        Pieza p;
        tx = sessionEdit.beginTransaction();
        p = sessionEdit.load(Pieza.class, Idpieza);
        p.setIdpieza(pieza.getIdpieza());
        p.setNombre(pieza.getNombre());
        p.setPrecio(pieza.getPrecio());
        p.setCodpieza(pieza.getCodpieza());
        p.setDescripcion(pieza.getDescripcion());

        sessionEdit.update(p);
        tx.commit();
        sessionEdit.close();


    }

    public void deletePieza(Pieza pieza, int Idpieza) {
        SessionFactory sesionDelete = HibernateUtil.getSessionFactory();
        Session sessionDelete = sesionDelete.openSession();
        Pieza p;
        tx = sessionDelete.beginTransaction();
        p = (Pieza) sessionDelete.load(Pieza.class, Idpieza);
        sessionDelete.delete(pieza);
        tx.commit();
        sessionDelete.close();
    }

    public boolean validaciones(Pieza p) {

        /*Necesito revisar que el codigo no exceda de 6 digitos
         * nombre de 20
         * el precio no acepte letras -- he puesto un action listener
         *  al txt para detectar cuando se pulsa una tecla que sea numero
         * */

        Boolean error = true;
        HashMap<String, String> errores = new HashMap<>();

        if (p.getCodpieza().length() > 6 || p.getCodpieza().equals("") || p.getCodpieza() == null) {
            if (p.getCodpieza().length() > 6) {
                errores.put("Codigo", "El codigo excede en longitud. MAX 6.");
            } else {
                errores.put("Codigo", "El codigo no puede estar vacio.");
            }
        }

        //Control del codigo no duplicar valores


        List<Pieza> listado = new ArrayList<>();
        listado = selectAll();

        for (Pieza e : listado) {
            if (e.getCodpieza().equals(p.getCodpieza())) {
                if (p.getIdpieza() == 0) {
                    errores.put("Codigo", "Codigo duplicado");
                }
            }
        }

        //Datos de tipo String
        if (p.getNombre().length() > 20 || p.getNombre().equals("")) {
            if (p.getNombre().length() > 20) {
                errores.put("Nombre", "El nombre excede en longitud. MAX 20.");
            } else {
                errores.put("Nombre", "El nombre no puede estar vacio.");
            }
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

    public List<Pieza> selectAll() {

        SessionFactory sesionSelectAll = HibernateUtil.getSessionFactory();
        Session sessionSelectAll = sesionSelectAll.openSession();
        Query q = null;
        q = sessionSelectAll.createQuery("from Pieza");

        List<Pieza> listaPiezas = new ArrayList<>();
        listaPiezas.clear();
        listaPiezas = q.list();

        Iterator<Pieza> iter = listaPiezas.iterator();
        List<Pieza> enviarListaPiezas = new ArrayList<>();
        enviarListaPiezas.clear();

        while (iter.hasNext()) {
            Pieza pieza = (Pieza) iter.next();
            pieza.setCodpieza(pieza.getCodpieza());
            pieza.setNombre(pieza.getNombre());
            pieza.setPrecio(pieza.getPrecio());
            pieza.setDescripcion(pieza.getDescripcion());
            enviarListaPiezas.add(pieza);

        }

        sessionSelectAll.close();
        return enviarListaPiezas;
    }

    public Pieza selectPieza(int id) {
        SessionFactory sesionSelectId = HibernateUtil.getSessionFactory();
        Session sessionSelectId = sesionSelectId.openSession();
        Pieza p = new Pieza();

        try {
            p = (Pieza) sessionSelectId.load(Pieza.class, id);
            p.setCodpieza(p.getCodpieza());
            p.setNombre(p.getNombre());
            p.setPrecio(p.getPrecio());
            p.setDescripcion(p.getDescripcion());

        } catch (ObjectNotFoundException o) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado nada", "Error", JOptionPane.ERROR_MESSAGE);
        }


        sessionSelectId.close();
        return p;
    }

    public List selectByCodigo(String recibo, String consultaDe, String tabla) {
        SessionFactory sesionByCodigo = HibernateUtil.getSessionFactory();
        Session sessionByCodigo = sesionByCodigo.openSession();
        // Ejemplo consulta : select * from pieza where CODPIEZA like '%2a%';

        List<Pieza> listaEntontrados;


        //He tenido que construir la sentencia de esta forma. No me funcionaba :cod + q.setParameter
        String consulta = "from " + tabla + "  where " + consultaDe + " like '%" + recibo + "%'";

        // String consulta = "from Pieza  where codpieza like :cod";
        Query q = sessionByCodigo.createQuery(consulta);

        //    q.setParameter("cod", "'%recibo%'");
        System.out.println(q.getQueryString());

        listaEntontrados = q.list();
        sessionByCodigo.close();
        return listaEntontrados;
    }

    public int isInGestion(int idPieza) {
        SessionFactory sesionInGestion = HibernateUtil.getSessionFactory();
        Session sessionInGestion = sesionInGestion.openSession();
        Pieza p = new Pieza();
        int existe = 0;


        //select count(*) from proyecto where supervisor = 4;
        //  SELECT EXISTS(SELECT supervisor FROM proyecto WHERE SUPERVISOR = idProveedor) --> no se puede utilizar.

        String consulta = "select count(*) from Gestionglobal where idPieza = " + idPieza;
        Long qexist = (Long) sessionInGestion.createQuery(consulta).uniqueResult();
        existe = qexist.intValue();
        sessionInGestion.close();
        return existe;

    }

}
