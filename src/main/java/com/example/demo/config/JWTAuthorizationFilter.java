package com.example.demo.config;

import static com.example.demo.config.Constants.HEADER_AUTHORIZACION_KEY;
import static com.example.demo.config.Constants.SUPER_SECRET_KEY;
import static com.example.demo.config.Constants.TOKEN_BEARER_PREFIX;
import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;
import io.jsonwebtoken.Claims;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;

//@Controller
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    
    @Autowired
    private UsuarioService usuarioService;

	public JWTAuthorizationFilter(AuthenticationManager authManager) {
		super(authManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(HEADER_AUTHORIZACION_KEY);
		if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(HEADER_AUTHORIZACION_KEY);
		if (token != null) {
			// Se procesa el token y se recupera el usuario.
			Claims user = Jwts.parser()
						.setSigningKey(SUPER_SECRET_KEY)
						.parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, ""))
						.getBody();
						//.getSubject();

			if (user != null) {
                            System.out.println("sofalsgasdgasg " + user);
                            /*
                                Rol rol = new Rol(1, "ROLE_ADMINISTRADOR");
                                Set<Rol> roles = new HashSet<>();                               
                                roles.add(rol);//.add("ADMINISTRADOR");
                            
*/
                            //Collection<? extends GrantedAuthority> roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
                            //usuarioService = new UsuarioService();
                                System.out.println("/////////////////////////323");
                                System.out.println("user " + user);
                                
                                //UsuarioService usuarioService = new UsuarioService();
                                //Optional<Usuario> usuario = usuarioService.getUsuariobyNombre("usuario@hotmail.com");
                                //System.out.println("usuario es " + usuario);
                                
                                //System.out.println("sa g" + usuario.get().getEstado());
                                //System.out.println("sa g" + usuario.get().getCompania());
                                //System.out.println("user   asdfs" + userf);
				return new UsernamePasswordAuthenticationToken(user, null,
                                        Collections.emptyList()
                                );
			}
			return null;
		}
		return null;
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
