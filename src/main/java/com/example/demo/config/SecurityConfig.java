/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.config;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author mac
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .cors()
        .and()
        .csrf().disable()
        .authorizeRequests()
            .antMatchers(
                    HttpMethod.POST, "/login").permitAll()
            .antMatchers(
                    //HttpMethod.GET, 
                     "/",
                    "/cerrar-sesion",
                    "/home",
                    "/username",
                    "/reporte*"
                   ,"/reporte/*"
                    ).permitAll()      
            .antMatchers(
                    HttpMethod.POST, 
                    "/usuario"
                    ).permitAll()
            .antMatchers(
                    HttpMethod.GET,
                    "/compania",
                    "/compania/*",
                    "/modalidad*", 
                    "/modalidad/*", 
                    "/garantia*",
                    "/garantia/*",        
                    "/formadepago*",
                    "/formadepago/*",                    
                    "/usuario*",
                    "/usuario/*",
                    "/proceso*",
                    "/proceso/*"               
            ).hasAnyRole("USUARIO", "ADMINISTRADOR")
            .antMatchers(
                    HttpMethod.POST,
                    "/proceso*"             
            ).hasAnyRole("USUARIO")                 
            .antMatchers(
                    "/compania*",
                    "/compania/*",                    
                    "/modalidad*", 
                    "/modalidad/*", 
                    "/garantia*",
                    "/garantia/*",
                    "/formadepago*",
                    "/formadepago/*",                     
                    "/proceso*",
                    "/proceso/*",
                    "/usuario*",
                    "/usuario/*"
            ).hasRole("ADMINISTRADOR")
                /*
            .antMatchers(
                    //"/proceso*", 
                    //"/reporte"
            ).hasRole("REGISTRADO")                
            .antMatchers(
                    "/css/*",
                    "/imgs/*"
            ).permitAll()
*/
           .anyRequest()
                .denyAll()
                //.authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
            ;
        
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());     
    }
    
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
            final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
            return source;
    } 
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
            return new BCryptPasswordEncoder();
    }    
    
}
