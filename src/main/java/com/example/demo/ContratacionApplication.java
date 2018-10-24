package com.example.demo;

import com.example.demo.model.Modalidad;
import com.example.demo.repository.ModalidadRepository;
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
    public CommandLineRunner setup(ModalidadRepository modalidadRepository) {
        return (arg) -> {
            modalidadRepository.save(new Modalidad(1, "nombre 1", "Descripcion 1"));
            modalidadRepository.save(new Modalidad(2, "nombre 2", "Descripcion 2"));
            modalidadRepository.save(new Modalidad(3, "nombre 3", "Descripcion 3"));
            modalidadRepository.save(new Modalidad(4, "nombre 4", "Descripcion 4"));
        };
    }
}
