/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Credenciales;
import com.example.demo.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mac
 */
public interface LoginRepository extends JpaRepository<Usuario, Long>{
    public abstract Usuario findByCorreo(String correo);
}
