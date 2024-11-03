package com.example.FerreteriaFinal.controladores;

import com.example.FerreteriaFinal.modelos.Kardex;
import com.example.FerreteriaFinal.modelos.Producto;
import com.example.FerreteriaFinal.servicios.KardexServicio;
import com.example.FerreteriaFinal.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/kardex")
@CrossOrigin(origins = "http://localhost:3000")
public class KardexControlador {

    @Autowired
    private KardexServicio kardexServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Kardex> obtenerMovimientos() {
        return kardexServicio.obtenerMovimientos();
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarMovimiento(@RequestBody Kardex kardex) {
        if (kardex.getProductoNombre() == null || kardex.getProductoNombre().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El nombre del producto es obligatorio");
        }
    
        Optional<Producto> productoOpt = productoServicio.obtenerProductoPorNombre(kardex.getProductoNombre());
        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            
            int saldoActual = producto.getStock();
            if ("Entrada".equalsIgnoreCase(kardex.getTipoMovimiento())) {
                producto.setStock(saldoActual + kardex.getCantidad());
                kardex.setSaldo(saldoActual + kardex.getCantidad());
            } else if ("Salida".equalsIgnoreCase(kardex.getTipoMovimiento())) {
                producto.setStock(saldoActual - kardex.getCantidad());
                kardex.setSaldo(saldoActual - kardex.getCantidad());
            }
    
            productoServicio.guardarProducto(producto);
            Kardex nuevoKardex = kardexServicio.guardarKardex(kardex);
            return ResponseEntity.ok(nuevoKardex);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

}
    

