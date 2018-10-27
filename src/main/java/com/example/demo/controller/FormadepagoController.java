/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Formadepago;
import com.example.demo.service.FormadepagoService;
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
public class FormadepagoController {

    @Autowired
    private FormadepagoService formadepagoService;

    @RequestMapping(value = "/formadepago", method = RequestMethod.GET)
    public ResponseEntity<List<Formadepago>> getAllFormadepago() {
        return new ResponseEntity<>(formadepagoService.getAllFormadepago(), HttpStatus.OK);
    }

    @RequestMapping(value = "/formadepago/{id}", method = RequestMethod.GET)
    public ResponseEntity<Formadepago> getToDoById(@PathVariable("id") long id) {
        return new ResponseEntity<>(formadepagoService.getFormadepagoById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/formadepago", method = RequestMethod.POST)
    public ResponseEntity<Formadepago> saveToDo(@RequestBody Formadepago formadepago) {
        return new ResponseEntity<>(formadepagoService.saveFormadepago(formadepago), HttpStatus.OK);
    }

    @RequestMapping(value = "/formadepago", method = RequestMethod.PUT)
    public ResponseEntity<Formadepago> updateToDo(@RequestBody Formadepago formadepago) {
        return new ResponseEntity<>(formadepagoService.saveFormadepago(formadepago), HttpStatus.OK);
    }

    @RequestMapping(value = "/formadepago/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeToDoById(@PathVariable("id") long id) {
        Formadepago formadepago = formadepagoService.getFormadepagoById(id).get();
        formadepagoService.removeFormadepago(formadepago);
        return new ResponseEntity<>("Forma de pago eliminada", HttpStatus.OK);
    }
}
