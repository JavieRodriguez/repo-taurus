/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Modalidad;
import com.example.demo.service.ModalidadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mac
 */
@RestController
public class ModalidadController {
    @Autowired
    private ModalidadService modalidadService;

    @RequestMapping(value = "/modalidad", method = RequestMethod.GET)
    public ResponseEntity<List<Modalidad>> getAllModalidad() {
        return new ResponseEntity<>(modalidadService.getAllModalidad(), HttpStatus.OK);
    }

    @RequestMapping(value = "/modalidad/{id}", method = RequestMethod.GET)
    public ResponseEntity<Modalidad> getToDoById(@PathVariable("id") long id) {
        return new ResponseEntity<>(modalidadService.getModalidadById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/modalidad", method = RequestMethod.POST)
    public ResponseEntity<Modalidad> saveToDo(@Valid @RequestBody Modalidad modalidad) {
        return new ResponseEntity<>(modalidadService.saveModalidad(modalidad), HttpStatus.OK);
    }

    @RequestMapping(value = "/modalidad", method = RequestMethod.PUT)
    public ResponseEntity<Modalidad> updateToDo(@RequestBody Modalidad modalidad) {
        return new ResponseEntity<>(modalidadService.saveModalidad(modalidad), HttpStatus.OK);
    }

    @RequestMapping(value = "/modalidad/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeToDoById(@PathVariable("id") long id) {
        Modalidad modalidad = modalidadService.getModalidadById(id).get();
        modalidadService.removeModalidad(modalidad);
        return new ResponseEntity<>("Modalidad eliminada", HttpStatus.OK);
    }
}
