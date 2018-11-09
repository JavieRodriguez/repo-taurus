package com.example.demo.service;

import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.LoginRepository;
import java.util.ArrayList;
import static java.util.Collections.emptyList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    private LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
            this.loginRepository = loginRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Usuario usuario = loginRepository.findByCorreo(correo);
            if (usuario == null) {
                    throw new UsernameNotFoundException(correo);
            }
        //return new User(usuario.getCorreo(), usuario.getContrasena(), emptyList());
        return buildUser(usuario,buildAuthorities(usuario.getRol()));
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

    private List<GrantedAuthority> buildAuthorities(Set<Rol> roles){
        Set<GrantedAuthority> auths = new HashSet<>();
        for (Rol rol : roles) {
            auths.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return new ArrayList<>(auths);
    }           
}
