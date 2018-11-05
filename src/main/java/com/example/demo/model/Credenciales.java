/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.example.demo.entity.Compania;

/**
 *
 * @author mac
 */

public class Credenciales {
    private String username;
    private Compania compania;
    private String password;

    public Credenciales() {
    }

    public Credenciales(String username, Compania compania, String password) {
        this.username = username;
        this.compania = compania;
        this.password = password;
    }

    public String getUsername() {
            return username;
    }

    public void setUsername(String username) {
            this.username = username;
    }

    public Compania getCompania() {
        return compania;
    }

    public void setCompania(Compania compania) {
        this.compania = compania;
    } 
    
    public String getPassword() {
            return password;
    }

    public void setPassword(String password) {
            this.password = password;
    }
}
