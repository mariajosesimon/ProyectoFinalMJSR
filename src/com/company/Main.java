package com.company;

import com.company.Vistas.VentanaInicio;

import javax.swing.*;

public class Main {




    public static void main(String[] args) {
        // write your code here

       /*
        Pieza pieza = new Pieza();
        pieza.setCodpieza("02adc");
        pieza.setPrecio(0.35);
        pieza.setNombre("Clavo");
        pieza.setDescripcion("Clavos de 12mm");

        System.out.println(pieza.getNombre());
        Transaction tx = session.beginTransaction();
        try{
            System.out.println("guardando pieza");
            session.save(pieza);
            tx.commit();
        }catch (HibernateError e){
            System.out.println("error de hibernate");
        }
*/
        JFrame frame = new JFrame("El Talle de MJSR");
        frame.setContentPane(new VentanaInicio(frame).getJPGeneral());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
