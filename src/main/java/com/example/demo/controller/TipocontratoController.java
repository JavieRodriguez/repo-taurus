/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.entity.Tipocontrato;
import com.example.demo.service.TipocontratoService;
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
public class TipocontratoController {

    @Autowired
    private TipocontratoService tipocontratoService;

    @RequestMapping(value = "/tipocontrato", method = RequestMethod.GET)
    public ResponseEntity<List<Tipocontrato>> getAllTipocontrato() {
        return new ResponseEntity<>(tipocontratoService.getAllTipocontrato(), HttpStatus.OK);
    }

    @RequestMapping(value = "/tipocontrato/{id}", method = RequestMethod.GET)
    public ResponseEntity<Tipocontrato> getToDoById(@PathVariable("id") long id) {
        return new ResponseEntity<>(tipocontratoService.getTipocontratoById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/tipocontrato", method = RequestMethod.POST)
    public ResponseEntity<Tipocontrato> saveToDo(@RequestBody Tipocontrato tipocontrato) {
        return new ResponseEntity<>(tipocontratoService.saveTipocontrato(tipocontrato), HttpStatus.OK);
    }

    @RequestMapping(value = "/tipocontrato", method = RequestMethod.PUT)
    public ResponseEntity<Tipocontrato> updateToDo(@RequestBody Tipocontrato tipocontrato) {
        return new ResponseEntity<>(tipocontratoService.saveTipocontrato(tipocontrato), HttpStatus.OK);
    }

    @RequestMapping(value = "/tipocontrato/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeToDoById(@PathVariable("id") long id) {
        Tipocontrato tipocontrato = tipocontratoService.getTipocontratoById(id).get();
        tipocontratoService.removeTipocontrato(tipocontrato);
        return new ResponseEntity<>("Tipocontrato eliminada", HttpStatus.OK);
    }
}
