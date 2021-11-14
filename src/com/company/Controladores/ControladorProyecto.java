package com.company.Controladores;

import com.company.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ControladorProyecto {
    SessionFactory sesion = HibernateUtil.getSessionFactory();
    Session session = sesion.openSession();


    public ControladorProyecto() {
    }

    public void addProyecto() {


    }

    public void editProyecto() {
    }

    public void deleteProyecto() {
    }
}
