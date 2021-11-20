package com.company;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Gestionglobal {
    private int idGestionGlobal;
    private int cantidad;
    private int idProveedor;
    private int idPieza;
    private int idProyecto;
    private Proveedor proveedorByIdProveedor;
    private Pieza piezaByIdPieza;
    private Proyecto proyectoByIdProyecto;

    @Id
    @Column(name = "idGestionGlobal", nullable = false)
    public int getIdGestionGlobal() {
        return idGestionGlobal;
    }

    public void setIdGestionGlobal(int idGestionGlobal) {
        this.idGestionGlobal = idGestionGlobal;
    }

    @Basic
    @Column(name = "cantidad", nullable = false)
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

    @Basic
    @Column(name = "idProveedor", nullable = false)
    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    @Basic
    @Column(name = "idPieza", nullable = false)
    public int getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(int idPieza) {
        this.idPieza = idPieza;
    }

    @Basic
    @Column(name = "idProyecto", nullable = false)
    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    @ManyToOne
    @JoinColumn(name = "idProveedor", referencedColumnName = "IDPROVEEDOR", nullable = false)
    public Proveedor getProveedorByIdProveedor() {
        return proveedorByIdProveedor;
    }

    public void setProveedorByIdProveedor(Proveedor proveedorByIdProveedor) {
        this.proveedorByIdProveedor = proveedorByIdProveedor;
    }

    @ManyToOne
    @JoinColumn(name = "idPieza", referencedColumnName = "IDPIEZA", nullable = false)
    public Pieza getPiezaByIdPieza() {
        return piezaByIdPieza;
    }

    public void setPiezaByIdPieza(Pieza piezaByIdPieza) {
        this.piezaByIdPieza = piezaByIdPieza;
    }

    @ManyToOne
    @JoinColumn(name = "idProyecto", referencedColumnName = "idproyecto", nullable = false)
    public Proyecto getProyectoByIdProyecto() {
        return proyectoByIdProyecto;
    }

    public void setProyectoByIdProyecto(Proyecto proyectoByIdProyecto) {
        this.proyectoByIdProyecto = proyectoByIdProyecto;
    }

    @Override
    public String toString() {

        int pieza = piezaByIdPieza.getIdpieza();
        int proveedor = proveedorByIdProveedor.getIdproveedor();
        int proyecto = proyectoByIdProyecto.getIdproyecto();

        return "El proyecto " + proyectoByIdProyecto.getIdproyecto() + "" +
                " lo ejecuta el proveedor " + proveedorByIdProveedor.getIdproveedor() +
                " utilizando " + cantidad + " de " + piezaByIdPieza.getIdpieza();
    }
}
