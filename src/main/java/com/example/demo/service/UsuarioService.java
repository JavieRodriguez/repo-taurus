/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.service;

import com.example.demo.model.Rol;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mac
 */
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    public List<Usuario> getAllUsuario(){
        return usuarioRepository.findAll();
    }
    
    public Optional<Usuario> getUsuarioById(long id){
        return usuarioRepository.findById(id);
    }
    
    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    } 
    
    public void removeUsuario(Usuario usuario){
        usuarioRepository.delete(usuario);
    }
/*
    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        List<GrantedAuthority> authorities = buildAuthorities(usuario.getRol());
        return buildUser(usuario, authorities);
    }
    
    private List<GrantedAuthority> buildAuthorities(Set<Rol> rol){
        Set<GrantedAuthority> auths = new HashSet<>();
        for (Rol userrol : rol) {
            auths.add(new SimpleGrantedAuthority(userrol.getNombre()));
        }
        return new ArrayList<>(auths);
    }
    

    private User buildUser(Usuario usuario, List<GrantedAuthority> authorities){
        return new User(usuario.getCorreo(), 
                usuario.getContrasena(), 
                true, 
                true, 
                true, 
                true, 
                authorities);
        
    }    
*/
}
