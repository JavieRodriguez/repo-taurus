/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.entity.Proceso;
import com.example.demo.service.ProcesoService;
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
public class ProcesoController {
    @Autowired
    private ProcesoService procesoService;

    @RequestMapping(value = "/proceso", method = RequestMethod.GET)
    public ResponseEntity<List<Proceso>> getAllProceso() {
        return new ResponseEntity<>(procesoService.getAllProceso(), HttpStatus.OK);
    }

    @RequestMapping(value = "/proceso/{id}", method = RequestMethod.GET)
    public ResponseEntity<Proceso> getToDoById(@PathVariable("id") long id) {
        return new ResponseEntity<>(procesoService.getProcesoById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/proceso", method = RequestMethod.POST)
    public ResponseEntity<Proceso> saveToDo(@RequestBody Proceso proceso) {
        return new ResponseEntity<>(procesoService.saveProceso(proceso), HttpStatus.OK);
    }

    @RequestMapping(value = "/proceso", method = RequestMethod.PUT)
    public ResponseEntity<Proceso> updateToDo(@RequestBody Proceso proceso) {
        return new ResponseEntity<>(procesoService.saveProceso(proceso), HttpStatus.OK);
    }

    @RequestMapping(value = "/proceso/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeToDoById(@PathVariable("id") long id) {
        Proceso proceso = procesoService.getProcesoById(id).get();
        procesoService.removeProceso(proceso);
        return new ResponseEntity<>("Proceso eliminado", HttpStatus.OK);
    }
}
