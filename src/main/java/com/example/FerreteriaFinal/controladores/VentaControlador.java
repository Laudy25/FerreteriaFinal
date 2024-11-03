package com.example.FerreteriaFinal.controladores;

import com.example.FerreteriaFinal.modelos.Venta;
import com.example.FerreteriaFinal.servicios.VentaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin(origins = "http://localhost:3000")
public class VentaControlador {

    @Autowired
    private VentaServicio ventaServicio;

    @GetMapping
    public List<Venta> obtenerTodasLasVentas() {
        return ventaServicio.obtenerTodasLasVentas();
    }

    @GetMapping("/{id}")
    public Optional<Venta> obtenerVentaPorId(@PathVariable String id) {
        return ventaServicio.obtenerVentaPorId(id);
    }

    @PostMapping
    public Venta agregarVenta(@RequestBody Venta venta) {
        return ventaServicio.agregarVenta(venta);
    }

    @DeleteMapping("/{id}")
    public void eliminarVenta(@PathVariable String id) {
        ventaServicio.eliminarVenta(id);
    }
}
