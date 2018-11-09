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
import org.exolab.castor.types.Date;
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
                    Proceso proceso = new Proceso(compania, String.valueOf(contProceso), "Proceso " + contProceso,
                            "objeto", 3, 9000000, new SimpleDateFormat("dd/mm/yyyy").parse("01/01/2019"), modalidad);
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
