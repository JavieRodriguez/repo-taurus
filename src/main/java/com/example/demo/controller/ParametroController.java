/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Parametro;
import com.example.demo.service.ParametroService;
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
public class ParametroController {

    @Autowired
    private ParametroService parametroService;

    @RequestMapping(value = "/parametro", method = RequestMethod.GET)
    public ResponseEntity<List<Parametro>> getAllParametro() {
        return new ResponseEntity<>(parametroService.getAllParametro(), HttpStatus.OK);
    }

    @RequestMapping(value = "/parametro/{id}", method = RequestMethod.GET)
    public ResponseEntity<Parametro> getToDoById(@PathVariable("id") long id) {
        return new ResponseEntity<>(parametroService.getParametroById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/parametro", method = RequestMethod.POST)
    public ResponseEntity<Parametro> saveToDo(@RequestBody Parametro parametro) {
        return new ResponseEntity<>(parametroService.saveParametro(parametro), HttpStatus.OK);
    }

    @RequestMapping(value = "/parametro", method = RequestMethod.PUT)
    public ResponseEntity<Parametro> updateToDo(@RequestBody Parametro parametro) {
        return new ResponseEntity<>(parametroService.saveParametro(parametro), HttpStatus.OK);
    }

    @RequestMapping(value = "/parametro/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeToDoById(@PathVariable("id") long id) {
        Parametro parametro = parametroService.getParametroById(id).get();
        parametroService.removeParametro(parametro);
        return new ResponseEntity<>("Parametro eliminada", HttpStatus.OK);
    }
}
