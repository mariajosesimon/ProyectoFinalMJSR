package com.company.Controladores;

import com.company.Pieza;
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
        SessionFactory sesionAdd = HibernateUtil.getSessionFactory();
        Session sessionAdd = sesionAdd.openSession();
        tx = sessionAdd.beginTransaction();
        sessionAdd.save(proyecto);
        tx.commit();
        sessionAdd.close();

    }

    public void editProyecto(Proyecto proyecto, int Idproyecto) {
        SessionFactory sesionEdit = HibernateUtil.getSessionFactory();
        Session sessionEdit = sesionEdit.openSession();
        Proyecto p;
        tx = sessionEdit.beginTransaction();
        p = sessionEdit.load(Proyecto.class, Idproyecto);
        p.setIdproyecto(proyecto.getIdproyecto());
        p.setNombre(proyecto.getNombre());
        p.setCiudad(proyecto.getCiudad());
        p.setSupervisor(proyecto.getSupervisor());
        sessionEdit.update(p);
        tx.commit();
        sessionEdit.close();
    }

    public void deleteProyecto(Proyecto proyecto, int Idproyecto) {
        SessionFactory sesionDelete = HibernateUtil.getSessionFactory();
        Session sessionDelete = sesionDelete.openSession();
        Proyecto p;
        tx = sessionDelete.beginTransaction();
        p = (Proyecto) sessionDelete.load(Proyecto.class, Idproyecto);
        sessionDelete.delete(proyecto);
        tx.commit();
        sessionDelete.close();
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
                if (p.getIdproyecto() == 0) {
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
        SessionFactory sesionSelectAll = HibernateUtil.getSessionFactory();
        Session sessionSelectAll = sesionSelectAll.openSession();
        Query q = sessionSelectAll.createQuery("from Proyecto");

        List<Proyecto> listaProyectos = q.list();
        Iterator<Proyecto> iter = listaProyectos.iterator();
        List<Proyecto> enviarListaProyectoes = new ArrayList<>();

        while (iter.hasNext()) {
            Proyecto proyecto = (Proyecto) iter.next();
            proyecto.setCodproyecto(proyecto.getCodproyecto());
            proyecto.setNombre(proyecto.getNombre());
            proyecto.setCiudad(proyecto.getCiudad());
            proyecto.setSupervisor(proyecto.getSupervisor());
            enviarListaProyectoes.add(proyecto);

        }

        sessionSelectAll.close();
        return enviarListaProyectoes;
    }

    public Proyecto selectProyecto(int id) {
        SessionFactory sesionSelectId = HibernateUtil.getSessionFactory();
        Session sessionSelectId = sesionSelectId.openSession();
        Proyecto proyecto = new Proyecto();

        try {
            proyecto = (Proyecto) sessionSelectId.load(Proyecto.class, id);
            proyecto.setCodproyecto(proyecto.getCodproyecto());
            proyecto.setNombre(proyecto.getNombre());
            proyecto.setCiudad(proyecto.getCiudad());


        } catch (ObjectNotFoundException o) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado nada", "Error", JOptionPane.ERROR_MESSAGE);
        }

        sessionSelectId.close();
        return proyecto;
    }

    public List selectByCodigo(String recibo, String consultaDe, String tabla) {

        // Ejemplo consulta : select * from Proyecto where CODProyecto like '%2a%';
        SessionFactory sesionByCodigo = HibernateUtil.getSessionFactory();
        Session sessionByCodigo = sesionByCodigo.openSession();
        List<Proyecto> listaEntontrados;


        //He tenido que construir la sentencia de esta forma. No me funcionaba :cod + q.setParameter
        String consulta = "from " + tabla + " where " + consultaDe + " like '%" + recibo + "%'";

        // String consulta = "from Proyecto  where codProyecto like :cod";
        Query q = sessionByCodigo.createQuery(consulta);

        //    q.setParameter("cod", "'%recibo%'");
        System.out.println(q.getQueryString());

        listaEntontrados = q.list();
        sessionByCodigo.close();
        return listaEntontrados;
    }

    public int isInGestion(int idProyecto) {
        SessionFactory sesionInGestion = HibernateUtil.getSessionFactory();
        Session sessionInGestion = sesionInGestion.openSession();
        Proyecto p = new Proyecto();
        int existe = 0;


        //select count(*) from proyecto where supervisor = 4;
        //  SELECT EXISTS(SELECT supervisor FROM proyecto WHERE SUPERVISOR = idProveedor) --> no se puede utilizar.

        String consulta = "select count(*) from Gestionglobal where idProyecto = " + idProyecto;
        Long qexist = (Long) sessionInGestion.createQuery(consulta).uniqueResult();
        existe = qexist.intValue();
        sessionInGestion.close();
        return existe;


    }
}
