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

@SpringBootApplication
public class ContratacionApplication {

    private List<Modalidad> listaModalidad;
    private List<Proceso> listaProceso;

    public static void main(String[] args) {
        SpringApplication.run(ContratacionApplication.class, args);
    }

    @Bean
    public CommandLineRunner setup(ModalidadRepository modalidadRepository, ProcesoRepository procesoRepository) {
        return (arg) -> {
            listaModalidad = new ArrayList<>();
            listaProceso = new ArrayList<>();
            int contModalidad = 1;
            int contProceso = 1;
            for (int i = 1; i <= 10; i++) {
                Modalidad modalidad = new Modalidad("Modalidad " + contModalidad, "Descripcion modalidad " + contModalidad);
                listaModalidad.add(modalidad);
                modalidadRepository.save(modalidad);
                for (int j = 1; j <= 5; j++) {
                    Proceso proceso = new Proceso("Proceso " + contProceso, "Descripcion proceso " + contProceso, modalidad);
                    listaProceso.add(proceso);
                    procesoRepository.save(proceso);
                    contProceso++;
                }
                contModalidad++;
            }

        };
    }
}
