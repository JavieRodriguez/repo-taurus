/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mac
 */
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> getAllUsuario(){
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> getUsuarioById(long id){
        return usuarioRepository.findById(id);
    }
    
    public Optional<Usuario> getUsuariobyCorreo(String correo){
        return usuarioRepository.findByCorreo(correo);
    }
    
    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    } 
    
    public void removeUsuario(Usuario usuario){
        usuarioRepository.delete(usuario);
    }

}
