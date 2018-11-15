/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.entity.Modalidad;
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
    public void getRpt1(@PathVariable("id") long id, HttpServletResponse response) throws JRException, IOException, SQLException, ClassNotFoundException {
        System.out.println("proceso+id" + id);
        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/ejemplo.jasper");
        Map<String, Object> params = new HashMap<>();
        params.put("STRSQL", "SELECT p.id, "
                + " p.numero, "
                + " p.objeto, "
                + " p.palabraclave,  "
                + " f.nombre formadepago,"
                + " g.nombre garantia"
                + " FROM PROCESO p,"
                + " formadepago f,"
                + " garantia g"
                + " where p.id = " + id + ""
                        + " and p.formadepago_id = f.id"
                        + " and p.garantia_id = g.id");
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
