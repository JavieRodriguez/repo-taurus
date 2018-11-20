/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.entity.Modalidad;
import com.example.demo.service.ModalidadService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mac
 */
@RestController
//@PreAuthorize("hasRole('ADMINISTRADOR')")
public class ModalidadController {

  
    @Autowired
    private ModalidadService modalidadService;

    @RequestMapping(value = "/reporte/{id}", method = RequestMethod.GET)
    //@ResponseBody
    public void getRpt1(@PathVariable("id") long id, HttpServletRequest request, HttpServletResponse response) throws JRException, IOException, SQLException, ClassNotFoundException {
        System.out.println("proceso+id" + id);
        //InputStream jasperStream = this.getClass().getResourceAsStream("/reports/ejemplo.jasper");
        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/minimacuantia2.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("CIUDADFECHA", "Tunja 01 de Enero de 2019");

        //File file = new File("/src/main/resources/reports/logo.png");
        //System.out.println("gg" + file.getAbsolutePath());
        params.put("SUBREPORTEDIR", "src/main/resources/reports/");
        params.put("IMAGEN", "src/main/resources/reports/logo.png");
        //params.put("IMAGEN", "../../../../src/main/resources/reports/logo.png");
        params.put("SISTEMA", "TAURUS");
        params.put("PROCESO", "1");
        params.put("SECCION", "1");
        params.put("NOMBREDOCUMENTO", "ESTUDIOS PREVIOS MINIMA CUANTIA");
        params.put("STRSQL", "SELECT p.id, "
                + " p.numero, "
                + " p.objeto, "
                + " p.palabraclave,  "
                + " f.nombre formadepago,"
                + " g.nombre garantia,"
                + " p.objeto OBJETO"
                + " FROM PROCESO p,"
                + " formadepago f,"
                + " garantia g"
                + " where p.id = 1 ");
        params.put("SUBSTRSQL1", "SELECT ID, 'ENTIDAD' ENTIDAD, 'NROPROCESO' NROPROCESO, 'MODALIDAD' MODALIDAD,"
                + " 'BIENOSERVICIO' BIENOSERVICIO, 20000000 PRESUPUESTOOFICIAL,"
                + " 9999999 VALORFINAL, 'DIAS' UNIDADDURACION, 12 DURACION FROM USUARIO");
        params.put("SUBSTRSQL2", "SELECT ID FROM USUARIO");
        /*
                + " where p.id = " + id + ""
                        + " and p.formadepago_id = f.id"
                        + " and p.garantia_id = g.id");
        */
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        //JRDataSource jRDataSource = new JRDataSource();
        
        //String dburl = "jdbc:mysql://localhost:3306/contrataciondb";
        //String dbdriver = "com.mysql.jdbc.Driver";
        
      /*
        String dburl = "jdbc:postgresql://localhost:5432/contrataciondb";
        String dbdriver = "org.postgresql.Driver";        
        String dbuser = "contratacionuser";
        String dbpass = "contratacionuser";
        */
        
        
        String dburl = System.getenv("JDBC_DATABASE_URL");
        String dbdriver = "org.postgresql.Driver";        
        String dbuser = System.getenv("JDBC_DATABASE_USERNAME");
        String dbpass = System.getenv("JDBC_DATABASE_PASSWORD");
        
        
        Class.forName(dbdriver);
        
        
        
        //DataSource dataSource = null;
        //dataSource.
        java.sql.Connection connection = DriverManager.getConnection(dburl, dbuser, dbpass);
        //JRDataSource jRDataSource = new JRDataSource();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                params, connection);
        //.fillReport(jasperReport, params, new JREmptyDataSource());

        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=modalidades.pdf");
        //response.setHeader("Content-Disposition", "inline");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

    //@RequestMapping(value = "/modalidad", method = RequestMethod.GET)
    @GetMapping("/modalidad")
    public ResponseEntity<List<Modalidad>> getAllModalidad() {
        return new ResponseEntity<>(modalidadService.getAllModalidad(), HttpStatus.OK);
    }

    @RequestMapping(value = "/modalidad/{id}", method = RequestMethod.GET)
    public ResponseEntity<Modalidad> getToDoById(@PathVariable("id") long id) {
        return new ResponseEntity<>(modalidadService.getModalidadById(id).get(), HttpStatus.OK);
    }

    @RequestMapping(value = "/modalidad", method = RequestMethod.POST)
    public ResponseEntity<Modalidad> saveToDo(@RequestBody Modalidad modalidad) {
        return new ResponseEntity<>(modalidadService.saveModalidad(modalidad), HttpStatus.OK);
    }

    @RequestMapping(value = "/modalidad", method = RequestMethod.PUT)
    public ResponseEntity<Modalidad> updateToDo(@RequestBody Modalidad modalidad) {
        return new ResponseEntity<>(modalidadService.saveModalidad(modalidad), HttpStatus.OK);
    }

    @RequestMapping(value = "/modalidad/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeToDoById(@PathVariable("id") long id) {
        Modalidad modalidad = modalidadService.getModalidadById(id).get();
        modalidadService.removeModalidad(modalidad);
        return new ResponseEntity<>("Modalidad eliminada", HttpStatus.OK);
    }

}
