package com.example.FerreteriaFinal.repositorios;

import java.util.Optional;
import com.example.FerreteriaFinal.modelos.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductoRepositorio extends MongoRepository<Producto, String> {
    Optional<Producto> findByNombre(String nombre); 
}
