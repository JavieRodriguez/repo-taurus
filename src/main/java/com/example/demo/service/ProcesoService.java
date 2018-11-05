/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.entity.Proceso;
import com.example.demo.repository.ProcesoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 *
 * @author mac
 */
@Service
public class ProcesoService {
    
    @Autowired
    private ProcesoRepository procesoRepository;
    
    public List<Proceso> getAllProceso(){
        return procesoRepository.findAll();
    }
    
    public Optional<Proceso> getProcesoById(long id){
        return procesoRepository.findById(id);
    }
    
    public Proceso saveProceso(Proceso proceso){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("auth.getCredentials() " + auth.getCredentials());
        System.out.println("auth.details() " + auth.getDetails());
        System.out.println("auth.principal() " + auth.getPrincipal());
        //proceso.setModalidad(modalidad);
        return procesoRepository.save(proceso);
    } 
    
    public void removeProceso(Proceso proceso){
        procesoRepository.delete(proceso);
    }
}
