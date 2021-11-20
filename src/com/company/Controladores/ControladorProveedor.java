package com.company.Controladores;

import com.company.Proveedor;
import com.company.utils.HibernateUtil;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.swing.*;
import java.util.*;

public class ControladorProveedor {
    SessionFactory sesion = HibernateUtil.getSessionFactory();
    Session session = sesion.openSession();
    private Proveedor proveedor;
    private Transaction tx;

    public ControladorProveedor() {
    }

    public void addProveedor(Proveedor proveedor) {
        SessionFactory sesionAdd = HibernateUtil.getSessionFactory();
        Session sessionAdd = sesionAdd.openSession();
        tx = sessionAdd.beginTransaction();
        session.save(proveedor);
        tx.commit();
        sessionAdd.close();
    }

    public void editProveedor(Proveedor proveedor, int Idproveedor) {
        SessionFactory sesionEdit = HibernateUtil.getSessionFactory();
        Session sessionEdit = sesionEdit.openSession();
        Proveedor p;
        tx = sessionEdit.beginTransaction();
        p = sessionEdit.load(Proveedor.class, Idproveedor);
        p.setIdproveedor(proveedor.getIdproveedor());
        p.setNombre(proveedor.getNombre());
        p.setApellidos(proveedor.getApellidos());
        p.setCodproveedor(proveedor.getCodproveedor());
        p.setDireccion(proveedor.getDireccion());

        sessionEdit.update(p);
        tx.commit();
        sessionEdit.close();


    }

    public void deleteProveedor(Proveedor proveedor, int Idproveedor) {
        SessionFactory sesionDelete = HibernateUtil.getSessionFactory();
        Session sessionDelete = sesionDelete.openSession();
        Proveedor p;
        tx = sessionDelete.beginTransaction();
        p = (Proveedor) sessionDelete.load(Proveedor.class, Idproveedor);
        sessionDelete.delete(proveedor);
        tx.commit();
        sessionDelete.close();
    }

    public boolean validaciones(Proveedor p) {


        Boolean error = true;
        HashMap<String, String> errores = new HashMap<>();

        if (p.getCodproveedor().length() > 6 || p.getCodproveedor().equals("") || p.getCodproveedor() == null) {
            if (p.getCodproveedor().length() > 6) {
                errores.put("Codigo", "El codigo excede en longitud. MAX 6.");
            } else if (p.getNombre().equals("")) {
                errores.put("Codigo", "El codigo no puede estar vacio.");
            } else {
                errores.put("Codigo", "El codigo no puede ser null");
            }
        }

        //Control del codigo no duplicar valores

        List<Proveedor> listado = new ArrayList<>();
        listado = selectAll();
        for (Proveedor e : listado) {
            if (e.getCodproveedor().equals(p.getCodproveedor())) {
                if (e.getIdproveedor() == 0) {
                    errores.put("Codigo", "Codigo duplicado");
                }

            }
        }

        //Datos de tipo String
        if (p.getNombre().length() > 20 || p.getNombre().equals("") || p.getNombre() == null) {
            if (p.getNombre().length() > 20) {
                errores.put("Nombre", "El nombre excede en longitud. MAX 20.");
            } else {
                errores.put("Nombre", "El nombre no puede estar vacio.");
            }
        }

        if (p.getApellidos().length() > 30 || p.getApellidos().equals("") || p.getApellidos() == null) {
            if (p.getApellidos().length() > 30) {
                errores.put("Apellidos", "Los apellidos excede en longitud. MAX 20.");
            } else {
                errores.put("Apellidos", "Los apellidos no puede estar vacio.");
            }
        }
        if (p.getDireccion().length() > 40 || p.getDireccion().equals("") || p.getDireccion() == null) {
            if (p.getNombre().length() > 40) {
                errores.put("Direccion", "La direccion excede en longitud. MAX 20.");
            } else {
                errores.put("Direccion", "La direccion no puede estar vacio.");
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

    public List<Proveedor> selectAll() {
        SessionFactory sesionSelectAll = HibernateUtil.getSessionFactory();
        Session sessionSelectAll = sesionSelectAll.openSession();
        Query q = sessionSelectAll.createQuery("from Proveedor");

        List<Proveedor> listaProveedores = q.list();
        Iterator<Proveedor> iter = listaProveedores.iterator();
        List<Proveedor> enviarListaProveedores = new ArrayList<>();

        while (iter.hasNext()) {
            Proveedor proveedor = (Proveedor) iter.next();
            proveedor.setCodproveedor(proveedor.getCodproveedor());
            proveedor.setNombre(proveedor.getNombre());
            proveedor.setApellidos(proveedor.getApellidos());
            proveedor.setDireccion(proveedor.getDireccion());
            enviarListaProveedores.add(proveedor);

        }

        sessionSelectAll.close();
        return enviarListaProveedores;
    }

    public Proveedor selectProveedor(int id) {

        SessionFactory sesionSelectId = HibernateUtil.getSessionFactory();
        Session sessionSelectId = sesionSelectId.openSession();
        Proveedor p = new Proveedor();

        try {
            p = (Proveedor) sessionSelectId.load(Proveedor.class, id);
            p.setCodproveedor(p.getCodproveedor());
            p.setNombre(p.getNombre());
            p.setApellidos(p.getApellidos());
            p.setDireccion(p.getDireccion());

        } catch (ObjectNotFoundException o) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado nada", "Error", JOptionPane.ERROR_MESSAGE);
        }

        sessionSelectId.close();
        return p;
    }

    public List selectByCodigo(String recibo, String consultaDe, String tabla) {
        SessionFactory sesionByCodigo = HibernateUtil.getSessionFactory();
        Session sessionByCodigo = sesionByCodigo.openSession();
        // Ejemplo consulta : select * from Proveedor where CODProveedor like '%2a%';

        List<Proveedor> listaEntontrados;


        //He tenido que construir la sentencia de esta forma. No me funcionaba :cod + q.setParameter
        String consulta = "from " + tabla + " where " + consultaDe + " like '%" + recibo + "%'";

        // String consulta = "from Proveedor  where codProveedor like :cod";
        Query q = sessionByCodigo.createQuery(consulta);

        //    q.setParameter("cod", "'%recibo%'");
        // System.out.println(q.getQueryString());

        listaEntontrados = q.list();
        sessionByCodigo.close();
        return listaEntontrados;
    }

    public int isInProyect(int idProveedor) {
        SessionFactory sesionInProyecto = HibernateUtil.getSessionFactory();
        Session sessionInProyecto = sesionInProyecto.openSession();
        Proveedor p = new Proveedor();
        int existe = 0;


        //select count(*) from proyecto where supervisor = 4;
        //  SELECT EXISTS(SELECT supervisor FROM proyecto WHERE SUPERVISOR = idProveedor) --> no se puede utilizar.

        String consulta = "select count(*) from Proyecto where supervisor = " + idProveedor;
        Long qexist = (Long) sessionInProyecto.createQuery(consulta).uniqueResult();
        existe = qexist.intValue();
        sessionInProyecto.close();
        return existe;

    }

}
