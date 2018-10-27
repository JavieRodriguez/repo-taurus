/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mac
 */
@Entity
public class Modalidad implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;
    @NotNull
    @Column(name="nombre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "modalidad", cascade = CascadeType.ALL)
    private final Set<Proceso> procesos;

    public Modalidad() {
        this.procesos = new HashSet<>();
    }
    
    public Modalidad(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.procesos = new HashSet<>();
    }

    public Modalidad(long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.procesos = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<Proceso> getProcesos() {
        return Collections.unmodifiableSet(this.procesos);
    }

    public void addProceso(Proceso proceso) {
        proceso.setModalidad(this);
        this.procesos.add(proceso);
    }
    
    
}
