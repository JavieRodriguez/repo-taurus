/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.entity.Compania;
import com.example.demo.entity.Proceso;
import com.example.demo.entity.Usuario;
import com.example.demo.service.ModalidadService;
import com.example.demo.service.ProcesoService;
import com.example.demo.service.UsuarioService;
import java.security.Principal;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author mac
 */
@RestController
public class ProcesoController {
    @Autowired
    private ProcesoService procesoService;
    @Autowired
    private ModalidadService modalidadService;
    @Autowired
    private UsuarioService usuarioService;    

    @RequestMapping(value = "/proceso", method = RequestMethod.GET)
    public ResponseEntity<List<Proceso>> getAllProceso() {
        //return new ResponseEntity<>(procesoService.getAllProceso(), HttpStatus.OK);
        //Optional<Modalidad> modalidad = modalidadService.getModalidadById(modalidad_id);
        //List<Proceso> procesos = new ArrayList<>(modalidad.get().getProcesos());
        //return new ResponseEntity<>(procesos, HttpStatus.OK);
        return new ResponseEntity<>(procesoService.getAllProceso(), HttpStatus.OK);
    }

    @RequestMapping(value = "/proceso/{id}", method = RequestMethod.GET)
    public ResponseEntity<Proceso> getToDoById(//@PathVariable (value = "modalidad_id") long modalidad_id,
                                               @PathVariable("id") long id) {
        /*
        if(!modalidadService.existsModalidad(modalidad_id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ModalidadId " + modalidad_id + " not found");
        }
        */
        //Optional<Modalidad> modalidad = modalidadService.getModalidadById(modalidad_id);
        //List<Proceso> procesos = new ArrayList<>(modalidad.get().getProcesos());
        //Proceso proceso = modalidadService.getProceso(modalidad_id, id);
        /*
        if (proceso != null) {
            return new ResponseEntity<>(proceso, HttpStatus.OK);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ProcesoId " + id + " not found");
        }
        */
        return new ResponseEntity<>(procesoService.getProcesoById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/proceso", method = RequestMethod.POST)
    public ResponseEntity<Proceso> saveToDo(HttpServletRequest request, 
            @RequestBody @Valid Proceso proceso) {
        
        //System.out.println("request: " + request.getUserPrincipal());
        //System.out.println("request2: " + request.getAttribute("nombre"));
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            if (usuarioService.getUsuariobyCorreo(principal.getName()).get() != null){
                //return new ResponseEntity<>(usuarioService.getUsuariobyCorreo(principal.getName()).get(), HttpStatus.OK);
                Usuario usuario = usuarioService.getUsuariobyCorreo(principal.getName()).get();
                System.out.println("compania del usuario " + usuario.getCompania());
                proceso.setCompania(usuario.getCompania());
            } else {
                throw new  ResponseStatusException(HttpStatus.UNAUTHORIZED);
            }
        } else {
            throw new  ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }          
        
        /*
        if (proceso.getFormadepago() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Formadepago not found");
        }
        if (proceso.getModalidad() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Modalidad not found");
        } 
        if (proceso.getGarantia() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Garantia not found");
        }           
        */
        /*
        if(!modalidadService.existsModalidad(modalidad_id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ModalidadId " + modalidad_id + " not found");
        }*/
        //proceso.setModalidad(modalidadService.getModalidadById(modalidad_id).get());
        //return new ResponseEntity<>(procesoService.saveProceso(proceso), HttpStatus.OK);
        return new ResponseEntity<>(procesoService.saveProceso(proceso), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/proceso", method = RequestMethod.PUT)
    public ResponseEntity<Proceso> updateToDo(@PathVariable (value = "modalidad_id") long modalidad_id,
            @RequestBody Proceso proceso) {
        if(!modalidadService.existsModalidad(modalidad_id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ModalidadId " + modalidad_id + " not found");
        }
        proceso.setModalidad(modalidadService.getModalidadById(modalidad_id).get());
        return new ResponseEntity<>(procesoService.saveProceso(proceso), HttpStatus.OK);
    }

    @RequestMapping(value = "/proceso/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeToDoById(@PathVariable (value = "modalidad_id") long modalidad_id,
            @PathVariable("id") long id) {
        if(!modalidadService.existsModalidad(modalidad_id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ModalidadId " + modalidad_id + " not found");
        }
        //Modalidad modalidad = modalidadService.get
        Proceso proceso = modalidadService.getProceso(modalidad_id, id);
        //Proceso proceso = procesoService.getProcesoById(id).get();
        procesoService.removeProceso(proceso);
        return new ResponseEntity<>("Proceso eliminado", HttpStatus.OK);
    }
}
