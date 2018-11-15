/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.entity.Modalidad;
import com.example.demo.entity.Proceso;
import com.example.demo.repository.ModalidadRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mac
 */
@Service
public class ModalidadService {
    
    @Autowired
    private ModalidadRepository modalidadRepository;
    
    public List<Modalidad> getAllModalidad(){
        return modalidadRepository.findAll();
    }
    
    public Optional<Modalidad> getModalidadById(long id){
        return modalidadRepository.findById(id);
    }
    
    public Modalidad saveModalidad(Modalidad modalidad){
        return modalidadRepository.save(modalidad);
    } 
    
    public void removeModalidad(Modalidad modalidad){
        modalidadRepository.delete(modalidad);
    }
    
    public boolean existsModalidad(long id){
        return modalidadRepository.existsById(id);
    }
    
    public Proceso getProceso(long modalidad_id, long proceso_id){
        return modalidadRepository.findByProcesoId(modalidad_id, proceso_id);
    }
}
