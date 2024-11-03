package com.example.FerreteriaFinal.servicios;

import com.example.FerreteriaFinal.modelos.Producto;
import com.example.FerreteriaFinal.repositorios.ProductoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepositorio productoRepositorio;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepositorio.findAll();
    }

    public Optional<Producto> obtenerProductoPorNombre(String nombre) {
        return productoRepositorio.findByNombre(nombre);
    }
    
    public Producto guardarProducto(Producto producto) {
        return productoRepositorio.save(producto);
    }

    public void eliminarProductoPorNombre(String nombre) {
        Optional<Producto> producto = productoRepositorio.findByNombre(nombre);
        producto.ifPresent(productoRepositorio::delete);
    }
    public Producto actualizarProductoPorNombre(String nombre, Producto productoActualizado) {
        Optional<Producto> productoExistente = productoRepositorio.findByNombre(nombre);
        if (productoExistente.isPresent()) {
            Producto producto = productoExistente.get();
            producto.setNombre(productoActualizado.getNombre());
            producto.setDescripcion(productoActualizado.getDescripcion());
            producto.setPrecio(productoActualizado.getPrecio());
            producto.setEstado(productoActualizado.isEstado());
            producto.setStock(productoActualizado.getStock()); 
            return productoRepositorio.save(producto);
        } else {
            throw new RuntimeException("Producto no encontrado");
        }
    }
}

