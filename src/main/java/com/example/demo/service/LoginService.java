package com.example.demo.service;

import com.example.demo.model.Credenciales;
import com.example.demo.model.Usuario;
import com.example.demo.repository.LoginRepository;
import com.example.demo.repository.UsuarioRepository;
import static java.util.Collections.emptyList;

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
            return new User(usuario.getCorreo(), usuario.getContrasena(), emptyList());
	}
}
