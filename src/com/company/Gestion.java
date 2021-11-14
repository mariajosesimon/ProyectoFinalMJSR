package com.company;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Gestion {
    private int idgestion;
    private Double cantidad;

    private Pieza piezaByIdpieza;


    private Proveedor proveedorByIdproveedor;
    private Proyecto proyectoByIdproyecto;

    @ManyToOne
    @JoinColumn(name = "idpieza", referencedColumnName = "IDPIEZA", nullable = false)
    public Pieza getPiezaByIdpieza() {
        return piezaByIdpieza;
    }

    public void setPiezaByIdpieza(Pieza piezaByIdpieza) {
        this.piezaByIdpieza = piezaByIdpieza;
    }

    @Id
    @Column(name = "idgestion", nullable = false)
    public int getIdgestion() {
        return idgestion;
    }

    public void setIdgestion(int idgestion) {
        this.idgestion = idgestion;
    }

    @Basic
    @Column(name = "cantidad", nullable = true, precision = 0)
    public Double getCantidad() {
        return cantidad;
    }

    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gestion gestion = (Gestion) o;
        return idgestion == gestion.idgestion && Objects.equals(cantidad, gestion.cantidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idgestion, cantidad);
    }





    @ManyToOne
    @JoinColumn(name = "idproveedor", referencedColumnName = "IDPROVEEDOR", nullable = false)
    public Proveedor getProveedorByIdproveedor() {
        return proveedorByIdproveedor;
    }

    public void setProveedorByIdproveedor(Proveedor proveedorByIdproveedor) {
        this.proveedorByIdproveedor = proveedorByIdproveedor;
    }

    @ManyToOne
    @JoinColumn(name = "idproyecto", referencedColumnName = "idproyecto", nullable = false)
    public Proyecto getProyectoByIdproyecto() {
        return proyectoByIdproyecto;
    }

    public void setProyectoByIdproyecto(Proyecto proyectoByIdproyecto) {
        this.proyectoByIdproyecto = proyectoByIdproyecto;
    }
}
