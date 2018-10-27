/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mac
 */
@Entity
public class Parametro implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;
    @Column(name="codigo")
    private String codigo;
    @Column(name="subcodigo")
    private String subcodigo;
    @NotNull
    @Column(name="nombre")
    private String nombre;
    @Column(name="descripcion")
    private String descripcion;
    
    @ManyToOne
    private Compania compania;

    public Parametro() {
    }
    
    public Parametro(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Parametro(long id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getSubcodigo() {
        return subcodigo;
    }

    public void setSubcodigo(String subcodigo) {
        this.subcodigo = subcodigo;
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

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }
    
    
    
}