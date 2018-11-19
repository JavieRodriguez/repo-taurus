package com.example.demo.config;

import static com.example.demo.config.Constants.HEADER_AUTHORIZACION_KEY;
import static com.example.demo.config.Constants.SUPER_SECRET_KEY;
import static com.example.demo.config.Constants.TOKEN_BEARER_PREFIX;
import static com.example.demo.config.Constants.AUTHORITIES_KEY;
import com.example.demo.entity.Rol;
import com.example.demo.entity.Usuario;
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
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


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
            Claims claims = Jwts.parser()
                    .setSigningKey(SUPER_SECRET_KEY)
                    .parseClaimsJws(token.replace(TOKEN_BEARER_PREFIX, ""))
                    .getBody();
            //.getSubject();

            if (claims.getSubject() != null) {
                Collection<? extends GrantedAuthority> authorities
                        = Arrays.asList(claims.get(AUTHORITIES_KEY).toString().split(",")).stream()
                                .map(authority -> new SimpleGrantedAuthority("ROLE_" + authority))
                                .collect(Collectors.toList());

                User principal = new User(claims.getSubject(), "",
                        authorities);
                System.out.println("user " + claims.getSubject());
                System.out.println("ROLES " + claims.get(AUTHORITIES_KEY).toString());
                return new UsernamePasswordAuthenticationToken(principal, null,
                        authorities
                );

            }
            return null;
        }
        return null;
    }
}
