/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.entity.Modalidad;
import com.example.demo.entity.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author mac
 */
public interface ModalidadRepository extends JpaRepository<Modalidad, Long>{
    @Query("select p "
            + "from Proceso p "
            + "where p.modalidad.id = :modalidad_id "
            + "and p.id = :id")
    public Proceso findByProcesoId(@Param("modalidad_id") long modalidad_id, 
            @Param("id") long id);
}
