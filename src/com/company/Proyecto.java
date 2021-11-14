package com.company;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Proyecto {
    private int idproyecto;
    private String codproyecto;
    private String nombre;
    private String ciudad;
    private String supervisor;
    private Collection<Gestion> gestionsByIdproyecto;

    @Id
    @Column(name = "idproyecto", nullable = false)
    public int getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(int idproyecto) {
        this.idproyecto = idproyecto;
    }

    @Basic
    @Column(name = "codproyecto", nullable = false, length = 6)
    public String getCodproyecto() {
        return codproyecto;
    }

    public void setCodproyecto(String codproyecto) {
        this.codproyecto = codproyecto;
    }

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 40)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Basic
    @Column(name = "CIUDAD", nullable = false, length = 40)
    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Basic
    @Column(name = "SUPERVISOR", nullable = true, length = 20)
    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proyecto proyecto = (Proyecto) o;
        return idproyecto == proyecto.idproyecto && Objects.equals(codproyecto, proyecto.codproyecto) && Objects.equals(nombre, proyecto.nombre) && Objects.equals(ciudad, proyecto.ciudad) && Objects.equals(supervisor, proyecto.supervisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idproyecto, codproyecto, nombre, ciudad, supervisor);
    }

    @OneToMany(mappedBy = "proyectoByIdproyecto")
    public Collection<Gestion> getGestionsByIdproyecto() {
        return gestionsByIdproyecto;
    }

    public void setGestionsByIdproyecto(Collection<Gestion> gestionsByIdproyecto) {
        this.gestionsByIdproyecto = gestionsByIdproyecto;
    }
}
