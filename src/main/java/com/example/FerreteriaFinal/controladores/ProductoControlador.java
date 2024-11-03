package com.example.FerreteriaFinal.controladores;

import com.example.FerreteriaFinal.modelos.Producto;
import com.example.FerreteriaFinal.servicios.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> obtenerTodosLosProductos() {
        return productoServicio.obtenerTodosLosProductos();
    }

    // Obtener producto por nombre en lugar de ID
    @GetMapping("/{nombre}")
    public Optional<Producto> obtenerProductoPorNombre(@PathVariable String nombre) {
        return productoServicio.obtenerProductoPorNombre(nombre);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoServicio.guardarProducto(producto);
    }

    // Eliminar producto por nombre
    @DeleteMapping("/{nombre}")
    public ResponseEntity<?> eliminarProducto(@PathVariable String nombre) {
        Optional<Producto> producto = productoServicio.obtenerProductoPorNombre(nombre);
        if (producto.isPresent()) {
            productoServicio.eliminarProductoPorNombre(nombre);
            return ResponseEntity.ok("Producto eliminado con éxito");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }

    // Actualizar producto por nombre
    @PutMapping("/{nombre}")
    public ResponseEntity<?> actualizarProducto(@PathVariable String nombre, @RequestBody Producto producto) {
        Optional<Producto> productoExistente = productoServicio.obtenerProductoPorNombre(nombre);
        if (productoExistente.isPresent()) {
            Producto productoActualizado = productoServicio.actualizarProductoPorNombre(nombre, producto);
            return ResponseEntity.ok(productoActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }
}
