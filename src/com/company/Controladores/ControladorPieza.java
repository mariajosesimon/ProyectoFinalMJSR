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
        tx = session.beginTransaction();
        session.save(pieza);
        tx.commit();
        session.close();
    }

    public void editPieza(Pieza pieza, int Idpieza) {

        Pieza p;
        tx = session.beginTransaction();
        p = session.load(Pieza.class, Idpieza);
        p.setIdpieza(pieza.getIdpieza());
        p.setNombre(pieza.getNombre());
        p.setPrecio(pieza.getPrecio());
        p.setCodpieza(pieza.getCodpieza());
        p.setDescripcion(pieza.getDescripcion());

        session.update(p);
        tx.commit();
        session.close();


    }

    public void deletePieza(Pieza pieza, int Idpieza) {

        Pieza p;
        tx = session.beginTransaction();
        p = session.load(Pieza.class, Idpieza);
        session.delete(pieza);
        tx.commit();
        session.close();
    }

    public boolean validaciones(Pieza p) {

        /*Necesito revisar que el codigo no exceda de 6 digitos
         * nombre de 20
         * el precio no acepte letras -- he puesto un action listener
         *  al txt para detectar cuando se pulsa una tecla que sea numero
         * */

        System.out.println("Codigo" + p.getCodpieza() + "/");

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
                errores.put("Codigo", "Codigo duplicado");
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

        Query q = session.createQuery("from Pieza");

        List<Pieza> listaPiezas = q.list();
        Iterator<Pieza> iter = listaPiezas.iterator();
        List<Pieza> enviarListaPiezas = new ArrayList<>();

        while (iter.hasNext()) {
            Pieza pieza = (Pieza) iter.next();
            pieza.setCodpieza(pieza.getCodpieza());
            pieza.setNombre(pieza.getNombre());
            pieza.setPrecio(pieza.getPrecio());
            pieza.setDescripcion(pieza.getDescripcion());
            enviarListaPiezas.add(pieza);

        }

        session.close();
        return enviarListaPiezas;
    }

    public Pieza selectPieza(int id) {

        Pieza p = new Pieza();

        try {
            p = (Pieza) session.load(Pieza.class, id);
            pieza.setCodpieza(pieza.getCodpieza());
            pieza.setNombre(pieza.getNombre());
            pieza.setPrecio(pieza.getPrecio());
            pieza.setDescripcion(pieza.getDescripcion());

        } catch (ObjectNotFoundException o) {
            JOptionPane.showMessageDialog(null, "No se ha encontrado nada", "Error", JOptionPane.ERROR_MESSAGE);
        }


        session.close();
        return p;
    }

    public List<Pieza> selectByCodigo(String recibo){
        List<Pieza> listaEntontrados = new ArrayList<>();

      // String consulta = "from  pieza where CODPIEZA  like  %recibo%";

        String consulta = "from pieza where codpieza like :recibo";


        Query q = session.createSQLQuery(consulta);
        Query q1 = session.createQuery(consulta);

        q1.setParameter(recibo, "%recibo%");


        return listaEntontrados;
    }

}
