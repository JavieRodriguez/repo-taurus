/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entity;

import com.example.demo.service.CompaniaService;
import com.example.demo.service.FormadepagoService;
import com.example.demo.service.GarantiaService;
import com.example.demo.service.ModalidadService;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mac
 */
@Entity
public class Proceso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @NotNull
    @Column(name = "numero")
    private String numero;
    @NotNull
    @Column(name = "palabraclave")
    private String palabraclave;
    @NotNull
    @Column(name = "objeto")
    @NotNull
    private String objeto;
    @Column(name = "presupuestooficial")
    @NotNull
    private double presupuestooficial;
    @NotNull
    @Column(name = "plazoejecucion")
    @NotNull
    private int plazoejecucion;
    @NotNull
    @Column(name = "fechainicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechainicio;

    @ManyToOne
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)    
    @NotNull
    @JoinColumn
    private Compania compania;
    @NotNull
    @ManyToOne
    //@JsonIgnore
    //@JoinColumn(name="modalidad_id",referencedColumnName="id")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    //@JoinColumn(name = "modalidad_id", referencedColumnName = "id")
    @JoinColumn()
    //@JoinColumn(name = "modalidad_id")
    private Modalidad modalidad;
    @NotNull
    @ManyToOne
    @JoinColumn
    private Formadepago formadepago;
    @NotNull
    @ManyToOne
    @JoinColumn
    private Garantia garantia;    
    

    public Proceso() {
    }
    
    
    public Proceso(String numero, 
            String palabraclave, 
            String objeto,
            double presupuestooficial,
            int plazoejecucion,
            Date fechainicio,
            long compania,
            long modalidad, 
            long formadepago, 
            long garantia) {
        System.out.println("////////////////////////////////////");
        CompaniaService companiaService = new CompaniaService();
        Compania companiaaux = companiaService.getCompaniaById(compania).get();
        ModalidadService modalidadService = new ModalidadService();
        Modalidad modalidadaux = modalidadService.getModalidadById(modalidad).get();
        FormadepagoService formadepagoService = new FormadepagoService();
        Formadepago formadepagoaux = formadepagoService.getFormadepagoById(formadepago).get();
        GarantiaService garantiaService = new GarantiaService();
        Garantia garantiaaux = garantiaService.getGarantiaById(garantia).get();
        this.numero = numero;
        this.palabraclave = palabraclave;
        this.objeto = objeto;
        this.compania = companiaaux;
        this.modalidad = modalidadaux;
        this.formadepago = formadepagoaux;
        this.garantia = garantiaaux;
    }    
    
    
    public Proceso(Compania compania, String numero, String palabraclave, String objeto, 
            int plazoejecucion, double presupuestooficial, Date fechainicio, Modalidad modalidad) {
        this.compania = compania;
        this.numero = numero;
        this.palabraclave = palabraclave;
        this.objeto = objeto;
        this.plazoejecucion = plazoejecucion;
        this.presupuestooficial = presupuestooficial;
        this.fechainicio = fechainicio;
        this.modalidad = modalidad;
        //System.out.println("***************************");
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
