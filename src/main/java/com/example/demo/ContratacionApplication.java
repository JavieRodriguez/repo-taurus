package com.example.demo;

import com.example.demo.model.Modalidad;
import com.example.demo.model.Proceso;
import com.example.demo.repository.ModalidadRepository;
import com.example.demo.repository.ProcesoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ContratacionApplication {

    private List<Modalidad> listaModalidad;
    private List<Proceso> listaProceso;

    public static void main(String[] args) {
        SpringApplication.run(ContratacionApplication.class, args);
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE");;
            }
        };
    }

    @Bean
    public CommandLineRunner setup(ModalidadRepository modalidadRepository, ProcesoRepository procesoRepository) {
        return (arg) -> {
            listaModalidad = new ArrayList<>();
            listaProceso = new ArrayList<>();
      
            ArrayList<Modalidad> listamodalidad = new ArrayList<>();
            listamodalidad.add(new Modalidad("MINIMA CUANTÍA", "Descripcion modalidad MINIMA CUANTÍA"));
            listamodalidad.add(new Modalidad("CONTRATACIÓN DIRECTA", "Descripcion modalidad CONTRATACIÓN DIRECTA"));
            listamodalidad.add(new Modalidad("SELECCIÓN ABREVIADA", "Descripcion modalidad SELECCIÓN ABREVIADA"));
            listamodalidad.add(new Modalidad("CONCURSO DE MÉRITOS", "Descripcion modalidad CONCURSO DE MÉRITOS"));
            listamodalidad.add(new Modalidad("LICITACIÓN PÚBLICA", "Descripcion modalidad LICITACIÓN PÚBLICA"));
            listamodalidad.add(new Modalidad("REGIMEN ESPECIAL", "Descripcion modalidad REGIMEN ESPECIAL"));
            
            for (int i = 0; i < listamodalidad.size(); i++) {
                Modalidad modalidad = listamodalidad.get(i);
                modalidadRepository.save(modalidad);
                int contProceso = 1;
                for (int j = 1; j <= 5; j++) {
                    Proceso proceso = new Proceso(String.valueOf(contProceso), "Proceso " + contProceso, "Descripcion proceso " + contProceso, modalidad);
                    listaProceso.add(proceso);
                    procesoRepository.save(proceso);
                    contProceso++;
                }
            }
            
        };
    }
}
