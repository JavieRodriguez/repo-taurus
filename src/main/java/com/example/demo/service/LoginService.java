package com.example.demo.service;

import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.LoginRepository;
import java.util.ArrayList;
import java.util.Collection;
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
public class LoginService implements UserDetailsService, UserDetails {

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

    /*
	private Set getAuthority(User user) {
        Set authorities = new HashSet<>();
		user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
		});
		return authorities;
	}  
*/

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
            System.out.println("roles " + rol.getNombre());
            auths.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return new ArrayList<>(auths);
    }           

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonExpired() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isAccountNonLocked() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
