package com.company.Vistas;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;


public class TablaProveedor extends AbstractTableModel {
    private String[] columnas = {"COD PROYECTO", "NOMBRE PROYECTO", "PIEZA", "PRECIO", "CANTIDAD"};
    private List<Object[]> tablaT;


    public TablaProveedor(List<Object[]> listado) {
        tablaT = listado;
    }


    @Override
    public int getRowCount() {
        return tablaT.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object[] row = (Object[]) tablaT.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return String.valueOf(row[0]);
            case 1:
                return String.valueOf(row[1]);
            case 2:
                return String.valueOf(row[2]);
            case 3:
                return String.valueOf(row[3]);
            case 4:
                return String.valueOf(row[4]);
        }//fin switch
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnas[column];
    }

}
