package com.example.demo;

import com.example.demo.entity.Compania;
import com.example.demo.entity.Formadepago;
import com.example.demo.entity.Garantia;
import com.example.demo.entity.Modalidad;
import com.example.demo.entity.Proceso;
import com.example.demo.repository.CompaniaRepository;
import com.example.demo.repository.FormadepagoRepository;
import com.example.demo.repository.GarantiaRepository;
import com.example.demo.repository.ModalidadRepository;
import com.example.demo.repository.ProcesoRepository;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ContratacionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContratacionApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup(ModalidadRepository modalidadRepository, 
            ProcesoRepository procesoRepository,
            CompaniaRepository companiaRepository,
            FormadepagoRepository formadepagoRepository,
            GarantiaRepository garantiaRepository) {
        return (String[] arg) -> {   
            
            
            Compania compania = new Compania("001", "compania001", "Tunja", "Boyacá");
            companiaRepository.save(compania);
            
            Formadepago formadepago = new Formadepago("Formadepago1", "desc formadepago1");
            formadepagoRepository.save(formadepago);
            Formadepago formadepago1 = new Formadepago("Formadepago2", "desc formadepago2");
            formadepagoRepository.save(formadepago1);      
            Formadepago formadepago2 = new Formadepago("Formadepago3", "desc formadepago3");
            formadepagoRepository.save(formadepago2);             
            
            Garantia garantia = new Garantia("Poliza", "garantia poliza");
            garantiaRepository.save(garantia);         
            Garantia garantia1 = new Garantia("Garantia bancaria", "garantia bancaria");
            garantiaRepository.save(garantia1);            
            Garantia garantia2 = new Garantia("Fiducia", "garantia fiducia");
            garantiaRepository.save(garantia2);                        
            
            
            ArrayList<Modalidad> listamodalidad = new ArrayList<>();
            listamodalidad.add(new Modalidad("MINIMA CUANTÍA", "Descripcion modalidad MINIMA CUANTÍA"));
            listamodalidad.add(new Modalidad("CONTRATACIÓN DIRECTA", "Descripcion modalidad CONTRATACIÓN DIRECTA"));
            listamodalidad.add(new Modalidad("SELECCIÓN ABREVIADA", "Descripcion modalidad SELECCIÓN ABREVIADA"));
            listamodalidad.add(new Modalidad("CONCURSO DE MÉRITOS", "Descripcion modalidad CONCURSO DE MÉRITOS"));
            listamodalidad.add(new Modalidad("LICITACIÓN PÚBLICA", "Descripcion modalidad LICITACIÓN PÚBLICA"));
            listamodalidad.add(new Modalidad("REGIMEN ESPECIAL", "Descripcion modalidad REGIMEN ESPECIAL"));
            
            
            int contProceso = 1;
            for (int i = 0; i < listamodalidad.size(); i++) {
                Modalidad modalidad = listamodalidad.get(i);
                modalidadRepository.save(modalidad);
                
               
                //List<Proceso> listaproceso = new ArrayList<>();
                
                for (int j = 1; j <= 2; j++) {
                    Proceso proceso = new Proceso(compania, String.valueOf(contProceso), "Proceso " + contProceso,
                            "objeto del contrato", 3, 9000000, new SimpleDateFormat("dd/mm/yyyy").parse("01/01/2019"), modalidad
                    , formadepago, garantia);
                    proceso.setCompania(compania);
                    proceso.setFormadepago(formadepago);
                    proceso.setGarantia(garantia);
                    //listaproceso.add(proceso);
                    procesoRepository.save(proceso);
                    contProceso++;
                }
                for (int j = 1; j <= 2; j++) {
                    Proceso proceso = new Proceso(compania, String.valueOf(contProceso), "Proceso " + contProceso,
                            "objeto22 del contrato", 3, 9000000, new SimpleDateFormat("dd/mm/yyyy").parse("01/01/2019"), modalidad
                    , formadepago, garantia);
                    proceso.setCompania(compania);
                    proceso.setFormadepago(formadepago1);
                    proceso.setGarantia(garantia1);
                    //listaproceso.add(proceso);
                    procesoRepository.save(proceso);
                    contProceso++;
                }  
                for (int j = 1; j <= 2; j++) {
                    Proceso proceso = new Proceso(compania, String.valueOf(contProceso), "Proceso " + contProceso,
                            "objeto22 del contrato", 3, 9000000, new SimpleDateFormat("dd/mm/yyyy").parse("01/01/2019"), modalidad
                    , formadepago, garantia);
                    proceso.setCompania(compania);
                    proceso.setFormadepago(formadepago2);
                    proceso.setGarantia(garantia2);
                    //listaproceso.add(proceso);
                    procesoRepository.save(proceso);
                    contProceso++;
                }                    
                //modalidad.setProceso(listaproceso);
                
            }
            
        };
    }
}
