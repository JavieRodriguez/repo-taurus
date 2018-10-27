/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Usuario;
import com.example.demo.service.UsuarioService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mac
 */
@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;   

    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public ResponseEntity<List<Usuario>> getAllUsuario() {
        return new ResponseEntity<>(usuarioService.getAllUsuario(), HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.GET)
    public ResponseEntity<Usuario> getToDoById(@PathVariable("id") long id) {
        return new ResponseEntity<>(usuarioService.getUsuarioById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.POST)
    public ResponseEntity<Usuario> saveToDo(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario", method = RequestMethod.PUT)
    public ResponseEntity<Usuario> updateToDo(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.saveUsuario(usuario), HttpStatus.OK);
    }

    @RequestMapping(value = "/usuario/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeToDoById(@PathVariable("id") long id) {
        Usuario usuario = usuarioService.getUsuarioById(id).get();
        usuarioService.removeUsuario(usuario);
        return new ResponseEntity<>("Usuario eliminada", HttpStatus.OK);
    }
}
