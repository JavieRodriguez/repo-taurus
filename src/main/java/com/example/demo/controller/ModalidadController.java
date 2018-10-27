/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.model.Modalidad;
import com.example.demo.service.ModalidadService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ModalidadController {

    @Autowired
    private ModalidadService modalidadService;

    @Autowired
    private ApplicationContext applicationContext;

    @RequestMapping(value = "/reporte", method = RequestMethod.GET)
    @ResponseBody
    public void getRpt1(HttpServletResponse response) throws JRException, IOException, SQLException, ClassNotFoundException {
        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/ejemplo.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("STRSQL", "SELECT id, nombre, descripcion FROM MODALIDAD");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        //JRDataSource jRDataSource = new JRDataSource();
        
        String dburl = "jdbc:mysql://localhost:3306/contrataciondb";
        String dbdriver = "com.mysql.jdbc.Driver";
        String dbuser = "contratacionuser";
        String dbpass = "contratacionuser";
        
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

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }

    @RequestMapping(value = "/modalidad", method = RequestMethod.GET)
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
