/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.entity.Compania;
import com.example.demo.service.CompaniaService;
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
public class CompaniaController {

    @Autowired
    private CompaniaService companiaService;

    @RequestMapping(value = "/compania", method = RequestMethod.GET)
    public ResponseEntity<List<Compania>> getAllCompania() {
        return new ResponseEntity<>(companiaService.getAllCompania(), HttpStatus.OK);
    }

    @RequestMapping(value = "/compania/{id}", method = RequestMethod.GET)
    public ResponseEntity<Compania> getToDoById(@PathVariable("id") long id) {
        return new ResponseEntity<>(companiaService.getCompaniaById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/compania", method = RequestMethod.POST)
    public ResponseEntity<Compania> saveToDo(@RequestBody Compania compania) {
        return new ResponseEntity<>(companiaService.saveCompania(compania), HttpStatus.OK);
    }

    @RequestMapping(value = "/compania", method = RequestMethod.PUT)
    public ResponseEntity<Compania> updateToDo(@RequestBody Compania compania) {
        return new ResponseEntity<>(companiaService.saveCompania(compania), HttpStatus.OK);
    }

    @RequestMapping(value = "/compania/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeToDoById(@PathVariable("id") long id) {
        Compania compania = companiaService.getCompaniaById(id).get();
        companiaService.removeCompania(compania);
        return new ResponseEntity<>("Compania eliminada", HttpStatus.OK);
    }
}
