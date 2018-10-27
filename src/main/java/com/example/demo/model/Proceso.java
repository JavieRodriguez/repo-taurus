/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
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
public class Proceso implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;
    @NotNull
    @Column(name = "numero")
    private String numero;
    @Column(name = "palabraclave")
    private String palabraclave;
    @Column(name = "objeto")
    private String objeto;
    @Column(name = "presupuestooficial")
    private double presupuestooficial;
    @Column(name = "plazoejecucion")
    private int plazoejecucion;
    @Column(name = "fechainicio")
    private Date fechainicio;

    @ManyToOne
    private Compania compania;
    @ManyToOne
    private Modalidad modalidad;
    @ManyToOne
    private Formadepago formadepago;
    @ManyToOne
    private Garantia garantia;    
    

    public Proceso() {
    }
    
    public Proceso(String numero, String palabraclave, String objeto, Modalidad modalidad) {
        this.numero = numero;
        this.palabraclave = palabraclave;
        this.objeto = objeto;
        this.modalidad = modalidad;
    }
        
    public Proceso(long id, String numero, String palabraclave, String objeto, Modalidad modalidad) {
        this.id = id;
        this.numero = numero;
        this.palabraclave = palabraclave;
        this.objeto = objeto;
        this.modalidad = modalidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
    public String getPalabraclave() {
        return palabraclave;
    }

    public void setPalabraclave(String palabraclave) {
        this.palabraclave = palabraclave;
    }    

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public double getPresupuestooficial() {
        return presupuestooficial;
    }

    public void setPresupuestooficial(double presupuestooficial) {
        this.presupuestooficial = presupuestooficial;
    }
    
    public int getPlazoejecucion() {
        return plazoejecucion;
    }

    public void setPlazoejecucion(int plazoejecucion) {
        this.plazoejecucion = plazoejecucion;
    }    
    
    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    }
    

    public Modalidad getModalidad() {
        return modalidad;
    }

    public void setModalidad(Modalidad modalidad) {
        this.modalidad = modalidad;
    }

    public Formadepago getFormadepago() {
        return formadepago;
    }

    public void setFormadepago(Formadepago formadepago) {
        this.formadepago = formadepago;
    }

    public Garantia getGarantia() {
        return garantia;
    }

    public void setGarantia(Garantia garantia) {
        this.garantia = garantia;
    }

}
