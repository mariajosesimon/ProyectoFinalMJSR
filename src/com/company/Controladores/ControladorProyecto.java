package com.company.Controladores;


import com.company.Pieza;
import com.company.Proveedor;
import com.company.Proyecto;
import com.company.utils.HibernateUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class ControladorProyecto {
    SessionFactory sesion = HibernateUtil.getSessionFactory();
    Session session = sesion.openSession();
    private Transaction tx;
    Proyecto proyecto;

    public ControladorProyecto() {
    }

    public void addProyecto(Proyecto proyecto) {
        tx = session.beginTransaction();
        session.save(proyecto);
        tx.commit();
        session.close();

    }

    public void editProyecto(Proyecto proyecto, int Idproyecto) {

        Proyecto p;
        tx = session.beginTransaction();
        p = session.load(Proyecto.class, Idproyecto);
        p.setIdproyecto(proyecto.getIdproyecto());
        p.setNombre(proyecto.getNombre());
        p.setCiudad(proyecto.getCiudad());
        p.setSupervisor(proyecto.getSupervisor());


        session.update(p);
        tx.commit();
        session.close();
    }

    public void deleteProyecto(Proyecto proyecto, int Idproyecto) {
        Proyecto p;
        tx = session.beginTransaction();
        p = (Proyecto) session.load(Proyecto.class, Idproyecto);
        session.delete(proyecto);
        tx.commit();
        session.close();
    }


    public boolean validaciones(Proyecto p) {


        Boolean error = true;
        HashMap<String, String> errores = new HashMap<>();

        if (p.getCodproyecto().length() > 6 || p.getCodproyecto().equals("") || p.getCodproyecto() == null) {
            if (p.getCodproyecto().length() > 6) {
                errores.put("Codigo", "El codigo excede en longitud. MAX 6.");
            } else if (p.getNombre().equals("")) {
                errores.put("Codigo", "El codigo no puede estar vacio.");
            } else {
                errores.put("Codigo", "El codigo no puede ser null");
            }
        }

        //Control del codigo no duplicar valores

        List<Proyecto> listado = new ArrayList<>();
        listado = selectAll();

        for (Proyecto e : listado) {
            if (e.getCodproyecto().equals(p.getCodproyecto())) {
                if (!(p.getIdproyecto() >= 0 && p.getIdproyecto() < listado.size())) {
                    errores.put("Codigo", "Codigo duplicado");
                }
            }
        }

        //Datos de tipo String
        if (p.getNombre().length() > 40 || p.getNombre().equals("") || p.getNombre() == null) {
            if (p.getNombre().length() > 40) {
                errores.put("Nombre", "El nombre excede en longitud. MAX 40.");
            } else {
                errores.put("Nombre", "El nombre no puede estar vacio.");
            }
        }

        if (p.getCiudad().length() > 40 || p.getCiudad().equals("") || p.getCiudad() == null) {
            if (p.getCiudad().length() > 40) {
                errores.put("Ciudad", "La ciudad excede en longitud. MAX 40.");
            } else {
                errores.put("Ciudad", "Los Ciudad no puede estar vacio.");
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

    public List<Proyecto> selectAll() {
        SessionFactory sesion2 = HibernateUtil.getSessionFactory();
        Session session2 = sesion2.openSession();
        Query q = session.createQuery("from Proyecto");

        List<Proyecto> listaProyectoes = q.list();
        Iterator<Proyecto> iter = listaProyectoes.iterator();
        List<Proyecto> enviarListaProyectoes = new ArrayList<>();

        while (iter.hasNext()) {
            Proyecto proyecto = (Proyecto) iter.next();
            proyecto.setCodproyecto(proyecto.getCodproyecto());
            proyecto.setNombre(proyecto.getNombre());
            proyecto.setCiudad(proyecto.getCiudad());
            //  proyecto.setSupervisor(proyecto.getSupervisor());
            enviarListaProyectoes.add(proyecto);

        }

        session2.close();
        return enviarListaProyectoes;
    }

    public Proyecto selectProyecto(int id) {
        SessionFactory sesion2 = HibernateUtil.getSessionFactory();
        Session session2 = sesion2.openSession();
        Proyecto p = new Proyecto();

        try {
            p = (Proyecto) session.load(Proyecto.class, id);
            proyecto.setCodproyecto(proyecto.getCodproyecto());
            proyecto.setNombre(proyecto.getNombre());
            proyecto.setCiudad(proyecto.getCiudad());




        } catch (ObjectNotFoundException o) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado nada", "Error", JOptionPane.ERROR_MESSAGE);
        }

        session2.close();
        return p;
    }

    public List selectByCodigo(String recibo, String consultaDe, String tabla) {

        // Ejemplo consulta : select * from Proyecto where CODProyecto like '%2a%';
        SessionFactory sesion2 = HibernateUtil.getSessionFactory();
        Session session2 = sesion2.openSession();
        List<Proyecto> listaEntontrados;


        //He tenido que construir la sentencia de esta forma. No me funcionaba :cod + q.setParameter
        String consulta = "from " + tabla + " where " + consultaDe + " like '%" + recibo + "%'";

        // String consulta = "from Proyecto  where codProyecto like :cod";
        Query q = session.createQuery(consulta);

        //    q.setParameter("cod", "'%recibo%'");
        System.out.println(q.getQueryString());

        listaEntontrados = q.list();
        session2.close();
        return listaEntontrados;
    }


}
