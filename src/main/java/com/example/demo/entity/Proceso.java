/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
    @Column(name = "objeto")
    private String objeto;    
    @NotNull
    @Column(name = "palabraclave")
    private String palabraclave;
    @Column(name = "presupuestooficial")
    @NotNull
    private double presupuestooficial;
    @NotNull
    @Column(name = "plazoejecucion")
    @NotNull
    private int plazoejecucion;
   
    @Column(name = "fechainicio")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechainicio;

    @ManyToOne
    @NotNull
    @JoinColumn
    //@JsonIdentityInfo(scope = Compania.class,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)    
    private Compania compania;
    @NotNull
    @ManyToOne
    //@JsonIdentityInfo(scope = Modalidad.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)
    @JoinColumn
    private Modalidad modalidad;
    @NotNull
    @ManyToOne
    @JoinColumn
    //@JsonIdentityInfo(scope = Formadepago.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)    
    private Formadepago formadepago;
    @NotNull
    @ManyToOne
    @JoinColumn
    //@JsonIdentityInfo(scope = Garantia.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)    
    private Garantia garantia; 
    
    //@NotNull
    //@ManyToOne
    //@JoinColumn
    //@JsonIdentityInfo(scope = Garantia.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)    
    //private Tipocontrato tipocontrato;     
    
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "proceso")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    //@JsonIdentityReference(alwaysAsId = true)
    private Set<Producto> producto;    
    

    public Proceso() {
    }
    
    /*
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
*/
    
    
    public Proceso(Compania compania, String numero, String palabraclave, String objeto, 
            int plazoejecucion, double presupuestooficial, Date fechainicio, Modalidad modalidad,
            Formadepago formadepago,
            Garantia garantia) {
        this.compania = compania;
        this.numero = numero;
        this.palabraclave = palabraclave;
        this.objeto = objeto;
        this.plazoejecucion = plazoejecucion;
        this.presupuestooficial = presupuestooficial;
        this.fechainicio = fechainicio;
        this.modalidad = modalidad;
        this.formadepago = formadepago;
        this.garantia = garantia;
        //System.out.println("***************************");
    }    
    
/*    
    public Proceso(long id, String numero, String palabraclave, String objeto, Modalidad modalidad) {
        this.id = id;
        this.numero = numero;
        this.palabraclave = palabraclave;
        this.objeto = objeto;
        this.modalidad = modalidad;
    }
*/

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

    /*
    public Tipocontrato getTipocontrato() {
        return tipocontrato;
    }

    public void setTipocontrato(Tipocontrato tipocontrato) {
        this.tipocontrato = tipocontrato;
    }
    
    */

    public Set<Producto> getProducto() {
        return producto;
    }

    public void setProducto(Set<Producto> producto) {
        this.producto = producto;
    }

    
}
