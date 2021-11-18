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
        tx = session.beginTransaction();
        session.save(proveedor);
        tx.commit();
        session.close();
    }

    public void editProveedor(Proveedor proveedor, int Idproveedor) {

        Proveedor p;
        tx = session.beginTransaction();
        p = session.load(Proveedor.class, Idproveedor);
        p.setIdproveedor(proveedor.getIdproveedor());
        p.setNombre(proveedor.getNombre());
        p.setApellidos(proveedor.getApellidos());
        p.setCodproveedor(proveedor.getCodproveedor());
        p.setDireccion(proveedor.getDireccion());

        session.update(p);
        tx.commit();
        session.close();


    }

    public void deleteProveedor(Proveedor proveedor, int Idproveedor) {

        Proveedor p;
        tx = session.beginTransaction();
        p = (Proveedor) session.load(Proveedor.class, Idproveedor);
        session.delete(proveedor);
        tx.commit();
        session.close();
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
                    if(e.getIdproveedor() >=0 && e.getIdproveedor()<listado.size()) {
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
        SessionFactory sesion2 = HibernateUtil.getSessionFactory();
        Session session2 = sesion2.openSession();
        Query q = session.createQuery("from Proveedor");

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

        session2.close();
        return enviarListaProveedores;
    }

    public Proveedor selectProveedor(int id) {

        SessionFactory sesion2 = HibernateUtil.getSessionFactory();
        Session session2 = sesion2.openSession();
        Proveedor p = new Proveedor();

        try {
            p = (Proveedor) session.load(Proveedor.class, id);
           // proveedor.setCodproveedor(p.getCodproveedor());
        //    proveedor.setNombre(p.getNombre());
        //    proveedor.setApellidos(p.getApellidos());
         //   proveedor.setDireccion(p.getDireccion());

        } catch (ObjectNotFoundException o) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado nada", "Error", JOptionPane.ERROR_MESSAGE);
        }

        session2.close();
        return p;
    }

    public List selectByCodigo(String recibo, String consultaDe, String tabla) {
        SessionFactory sesion2 = HibernateUtil.getSessionFactory();
        Session session2 = sesion2.openSession();
        // Ejemplo consulta : select * from Proveedor where CODProveedor like '%2a%';

        List<Proveedor> listaEntontrados;


        //He tenido que construir la sentencia de esta forma. No me funcionaba :cod + q.setParameter
        String consulta = "from " + tabla + " where " + consultaDe + " like '%" + recibo + "%'";

        // String consulta = "from Proveedor  where codProveedor like :cod";
        Query q = session.createQuery(consulta);

        //    q.setParameter("cod", "'%recibo%'");
        System.out.println(q.getQueryString());

        listaEntontrados = q.list();
        session2.close();
        return listaEntontrados;
    }

}
