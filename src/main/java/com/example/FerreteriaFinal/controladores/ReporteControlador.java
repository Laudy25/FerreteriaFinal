package com.example.FerreteriaFinal.controladores;

import com.example.FerreteriaFinal.servicios.ReporteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin(origins = "http://localhost:3000")
public class ReporteControlador {

    @Autowired
    private ReporteServicio reporteServicio;

    @GetMapping("/{tipo}")
    public ResponseEntity<byte[]> generarReporte(@PathVariable String tipo) {
        byte[] reportePdf = reporteServicio.generarReporte(tipo);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", tipo + "_reporte.pdf");
        return new ResponseEntity<>(reportePdf, headers, HttpStatus.OK);
    }
}
