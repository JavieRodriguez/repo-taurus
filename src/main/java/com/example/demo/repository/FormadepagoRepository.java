/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Formadepago;
import com.example.demo.entity.Modalidad;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mac
 */
public interface FormadepagoRepository extends JpaRepository<Formadepago, Long>{
    
}
