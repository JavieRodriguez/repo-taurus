/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.entity.Garantia;
import com.example.demo.repository.GarantiaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mac
 */
@Service
public class GarantiaService {
    
    @Autowired
    private GarantiaRepository garantiaRepository;
    
    public List<Garantia> getAllGarantia(){
        return garantiaRepository.findAll();
    }
    
    public Optional<Garantia> getGarantiaById(long id){
        return garantiaRepository.findById(id);
    }
    
    public Garantia saveGarantia(Garantia garantia){
        return garantiaRepository.save(garantia);
    } 
    
    public void removeGarantia(Garantia garantia){
        garantiaRepository.delete(garantia);
    }
}
