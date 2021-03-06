package com.example.demo.config;

import static com.example.demo.config.Constants.HEADER_AUTHORIZACION_KEY;
import static com.example.demo.config.Constants.ISSUER_INFO;
import static com.example.demo.config.Constants.SUPER_SECRET_KEY;
import static com.example.demo.config.Constants.TOKEN_BEARER_PREFIX;
import static com.example.demo.config.Constants.TOKEN_EXPIRATION_TIME;
import static com.example.demo.config.Constants.AUTHORITIES_KEY;
import com.example.demo.model.Credenciales;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Collections;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
                    throws AuthenticationException {
            try {
                    Credenciales credenciales = new ObjectMapper().readValue(request.getInputStream(), Credenciales.class);
                    return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                    credenciales.getUsername(), credenciales.getPassword(), Collections.emptyList()));
            } catch (IOException e) {
                    throw new RuntimeException(e);
            }
    }     

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                    Authentication authentication) throws IOException, ServletException {

        final String authorities = authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(","));

        String token = Jwts.builder().setIssuedAt(new Date()).setIssuer(ISSUER_INFO)
                        .setSubject(((User)authentication.getPrincipal()).getUsername())
                        .claim(AUTHORITIES_KEY, authorities)
                        .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))                        
                        .signWith(SignatureAlgorithm.HS512, SUPER_SECRET_KEY).compact();
        response.addHeader(HEADER_AUTHORIZACION_KEY, TOKEN_BEARER_PREFIX + " " + token);
        response.addHeader("access-control-expose-headers", "Authorization");                        
    }
}
