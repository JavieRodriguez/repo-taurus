/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.entity.Parametro;
import com.example.demo.repository.ParametroRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mac
 */
@Service
public class ParametroService {
    
    @Autowired
    private ParametroRepository parametroRepository;
    
    public List<Parametro> getAllParametro(){
        return parametroRepository.findAll();
    }
    
    public Optional<Parametro> getParametroById(long id){
        return parametroRepository.findById(id);
    }
    
    public Parametro saveParametro(Parametro parametro){
        return parametroRepository.save(parametro);
    } 
    
    public void removeParametro(Parametro parametro){
        parametroRepository.delete(parametro);
    }
}
