/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.entity.Garantia;
import com.example.demo.service.GarantiaService;
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
public class GarantiaController {

    @Autowired
    private GarantiaService garantiaService;

    @RequestMapping(value = "/garantia", method = RequestMethod.GET)
    public ResponseEntity<List<Garantia>> getAllGarantia() {
        return new ResponseEntity<>(garantiaService.getAllGarantia(), HttpStatus.OK);
    }

    @RequestMapping(value = "/garantia/{id}", method = RequestMethod.GET)
    public ResponseEntity<Garantia> getToDoById(@PathVariable("id") long id) {
        return new ResponseEntity<>(garantiaService.getGarantiaById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/garantia", method = RequestMethod.POST)
    public ResponseEntity<Garantia> saveToDo(@RequestBody Garantia garantia) {
        return new ResponseEntity<>(garantiaService.saveGarantia(garantia), HttpStatus.OK);
    }

    @RequestMapping(value = "/garantia", method = RequestMethod.PUT)
    public ResponseEntity<Garantia> updateToDo(@RequestBody Garantia garantia) {
        return new ResponseEntity<>(garantiaService.saveGarantia(garantia), HttpStatus.OK);
    }

    @RequestMapping(value = "/garantia/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeToDoById(@PathVariable("id") long id) {
        Garantia garantia = garantiaService.getGarantiaById(id).get();
        garantiaService.removeGarantia(garantia);
        return new ResponseEntity<>("Garantia eliminada", HttpStatus.OK);
    }
}
