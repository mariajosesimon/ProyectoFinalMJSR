package com.company.Controladores;

import com.company.Proveedor;
import com.company.utils.HibernateUtil;
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

    public  void addProveedor(Proveedor proveedor){
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
        p = session.load(Proveedor.class, Idproveedor);
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
        listado=selectAll();
        for (Proveedor e: listado ) {
            if(e.getCodproveedor().equals(p.getCodproveedor())){
                errores.put("Codigo", "Codigo duplicado");
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

        if (p.getApellidos().length() > 30 || p.getApellidos().equals("")|| p.getApellidos() == null) {
            if (p.getApellidos().length() > 30) {
                errores.put("Apellidos", "Los apellidos excede en longitud. MAX 20.");
            } else {
                errores.put("Apellidos", "Los apellidos no puede estar vacio.");
            }
        }
        if (p.getDireccion().length() > 40 || p.getDireccion().equals("")|| p.getDireccion() == null) {
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


        return enviarListaProveedores;
    }


}
