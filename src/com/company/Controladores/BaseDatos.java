package com.company.Controladores;

import com.company.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class BaseDatos {
    SessionFactory sesion = HibernateUtil.getSessionFactory();
    Session session = sesion.openSession();
    public BaseDatos() {
    }
    public void consultaBD() throws ClassNotFoundException {

/*

        String[] columnNames = sesion.getClassMetadata(Pieza.class).getPropertyNames();
        Type[] columnTypes = sesion.getClassMetadata(Pieza.class).getPropertyTypes();

        org.hibernate.type.Type[] columnTypes = sesion.getSessionFactory().getClassMetadata(Pieza.class).getPropertyTypes();
*/


    }



}
