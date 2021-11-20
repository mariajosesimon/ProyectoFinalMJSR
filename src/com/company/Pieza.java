package com.company;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Pieza {
    private int idpieza;
    private String codpieza;
    private String nombre;
    private double precio;
    private String descripcion;
    private Collection<Gestionglobal> gestionglobalsByIdpieza;

    @Id
    @Column(name = "IDPIEZA", nullable = false)
    public int getIdpieza() {
        return idpieza;
    }

    public void setIdpieza(int idpieza) {
        this.idpieza = idpieza;
    }

    @Basic
    @Column(name = "CODPIEZA", nullable = false, length = 6)
    public String getCodpieza() {
        return codpieza;
    }

    public void setCodpieza(String codpieza) {
        this.codpieza = codpieza;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 20)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "PRECIO", nullable = false, precision = 0)
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Basic
    @Column(name = "DESCRIPCION", nullable = true, length = 150)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pieza pieza = (Pieza) o;
        return idpieza == pieza.idpieza && Double.compare(pieza.precio, precio) == 0 && Objects.equals(codpieza, pieza.codpieza) && Objects.equals(nombre, pieza.nombre) && Objects.equals(descripcion, pieza.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idpieza, codpieza, nombre, precio, descripcion);
    }

    @Override
    public String toString() {
        return codpieza + " --> " + nombre + " - " + precio + "€ - " + descripcion;
    }

    @OneToMany(mappedBy = "piezaByIdPieza")
    public Collection<Gestionglobal> getGestionglobalsByIdpieza() {
        return gestionglobalsByIdpieza;
    }

    public void setGestionglobalsByIdpieza(Collection<Gestionglobal> gestionglobalsByIdpieza) {
        this.gestionglobalsByIdpieza = gestionglobalsByIdpieza;
    }



}
