/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Formadepago;
import com.example.demo.repository.FormadepagoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mac
 */
@Service
public class FormadepagoService {
    
    @Autowired
    private FormadepagoRepository formadepagoRepository;
    
    public List<Formadepago> getAllFormadepago(){
        return formadepagoRepository.findAll();
    }
    
    public Optional<Formadepago> getFormadepagoById(long id){
        return formadepagoRepository.findById(id);
    }
    
    public Formadepago saveFormadepago(Formadepago formadepago){
        return formadepagoRepository.save(formadepago);
    } 
    
    public void removeFormadepago(Formadepago formadepago){
        formadepagoRepository.delete(formadepago);
    }
}
