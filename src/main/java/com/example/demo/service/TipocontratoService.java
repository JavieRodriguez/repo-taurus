/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Tipocontrato;
import com.example.demo.repository.TipocontratoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mac
 */
@Service
public class TipocontratoService {
    
    @Autowired
    private TipocontratoRepository tipocontratoRepository;
    
    public List<Tipocontrato> getAllTipocontrato(){
        return tipocontratoRepository.findAll();
    }
    
    public Optional<Tipocontrato> getTipocontratoById(long id){
        return tipocontratoRepository.findById(id);
    }
    
    public Tipocontrato saveTipocontrato(Tipocontrato tipocontrato){
        return tipocontratoRepository.save(tipocontrato);
    } 
    
    public void removeTipocontrato(Tipocontrato tipocontrato){
        tipocontratoRepository.delete(tipocontrato);
    }
}
