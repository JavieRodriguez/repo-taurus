/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Compania;
import com.example.demo.repository.CompaniaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mac
 */
@Service
public class CompaniaService {
    
    @Autowired
    private CompaniaRepository companiaRepository;
    
    public List<Compania> getAllCompania(){
        return companiaRepository.findAll();
    }
    
    public Optional<Compania> getCompaniaById(long id){
        return companiaRepository.findById(id);
    }
    
    public Compania saveCompania(Compania compania){
        return companiaRepository.save(compania);
    } 
    
    public void removeCompania(Compania compania){
        companiaRepository.delete(compania);
    }
}
