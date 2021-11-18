package com.company;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Proveedor {
    private int idproveedor;
    private String codproveedor;
    private String nombre;
    private String apellidos;
    private String direccion;
    private Collection<Proyecto> proyectosByIdproveedor;

    @Id
    @Column(name = "IDPROVEEDOR", nullable = false)
    public int getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(int idproveedor) {
        this.idproveedor = idproveedor;
    }

    @Basic
    @Column(name = "CODPROVEEDOR", nullable = false, length = 6)
    public String getCodproveedor() {
        return codproveedor;
    }

    public void setCodproveedor(String codproveedor) {
        this.codproveedor = codproveedor;
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
    @Column(name = "APELLIDOS", nullable = false, length = 30)
    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Basic
    @Column(name = "DIRECCION", nullable = true, length = 40)
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proveedor proveedor = (Proveedor) o;
        return idproveedor == proveedor.idproveedor && Objects.equals(codproveedor, proveedor.codproveedor) && Objects.equals(nombre, proveedor.nombre) && Objects.equals(apellidos, proveedor.apellidos) && Objects.equals(direccion, proveedor.direccion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproveedor, codproveedor, nombre, apellidos, direccion);
    }

    @OneToMany(mappedBy = "proveedorBySupervisor")
    public Collection<Proyecto> getProyectosByIdproveedor() {
        return proyectosByIdproveedor;
    }

    public void setProyectosByIdproveedor(Collection<Proyecto> proyectosByIdproveedor) {
        this.proyectosByIdproveedor = proyectosByIdproveedor;
    }
    @Override
    public String toString() {
        return codproveedor + " --> " + nombre + " - " + apellidos + " - " + direccion;

    }
}
