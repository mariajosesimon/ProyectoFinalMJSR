package com.company.Controladores;

import com.company.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ControladorGestion {

    SessionFactory sesion = HibernateUtil.getSessionFactory();
    Session session = sesion.openSession();
    public ControladorGestion() {
    }

    public void addPieza() {
    }

    public void editPieza() {
    }

    public void deletePieza() {
    }
}
