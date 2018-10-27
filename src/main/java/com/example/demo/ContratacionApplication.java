package com.example.demo;

import com.example.demo.model.Compania;
import com.example.demo.model.Formadepago;
import com.example.demo.model.Garantia;
import com.example.demo.model.Modalidad;
import com.example.demo.model.Proceso;
import com.example.demo.repository.CompaniaRepository;
import com.example.demo.repository.FormadepagoRepository;
import com.example.demo.repository.GarantiaRepository;
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
    public CommandLineRunner setup(ModalidadRepository modalidadRepository, 
            ProcesoRepository procesoRepository,
            CompaniaRepository companiaRepository,
            FormadepagoRepository formadepagoRepository,
            GarantiaRepository garantiaRepository) {
        return (arg) -> {   
            Compania compania = new Compania("compania001", "Boyaca");
            companiaRepository.save(compania);
            
            Formadepago formadepago = new Formadepago("Formadepago", "desc formadepago");
            formadepagoRepository.save(formadepago);
            
            Garantia garantia = new Garantia("garantia", "desc garantia");
            garantiaRepository.save(garantia);            
            
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
                //List<Proceso> listaproceso = new ArrayList<>();
                int contProceso = 1;
                for (int j = 1; j <= 2; j++) {
                    Proceso proceso = new Proceso(String.valueOf(contProceso), "Proceso " + contProceso, "Descripcion proceso " + contProceso, modalidad);
                    proceso.setCompania(compania);
                    proceso.setFormadepago(formadepago);
                    proceso.setGarantia(garantia);
                    //listaproceso.add(proceso);
                    procesoRepository.save(proceso);
                    contProceso++;
                }
                //modalidad.setProceso(listaproceso);
                
            }
            
        };
    }
}
