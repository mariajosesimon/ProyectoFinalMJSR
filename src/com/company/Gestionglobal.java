package com.company;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Gestionglobal {
    private int idGestionGlobal;

    @Id
    @javax.persistence.Column(name = "idGestionGlobal", nullable = false)
    public int getIdGestionGlobal() {
        return idGestionGlobal;
    }

    public void setIdGestionGlobal(int idGestionGlobal) {
        this.idGestionGlobal = idGestionGlobal;
    }

    private int cantidad;

    @Basic
    @javax.persistence.Column(name = "cantidad", nullable = false)
    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gestionglobal that = (Gestionglobal) o;
        return idGestionGlobal == that.idGestionGlobal && cantidad == that.cantidad;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idGestionGlobal, cantidad);
    }
}
