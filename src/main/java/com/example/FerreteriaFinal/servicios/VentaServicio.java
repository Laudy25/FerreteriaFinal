package com.example.FerreteriaFinal.servicios;

import com.example.FerreteriaFinal.modelos.Venta;
import com.example.FerreteriaFinal.repositorios.VentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaServicio {

    @Autowired
    private VentaRepositorio ventaRepositorio;

    public List<Venta> obtenerTodasLasVentas() {
        return ventaRepositorio.findAll();
    }

    public Optional<Venta> obtenerVentaPorId(String id) {
        return ventaRepositorio.findById(id);
    }

    public Venta guardarVenta(Venta venta) {
        return ventaRepositorio.save(venta);
    }

    public void eliminarVenta(String id) {
        ventaRepositorio.deleteById(id);
    }

    public Venta agregarVenta(Venta venta) {
        return ventaRepositorio.save(venta);
    }
}
