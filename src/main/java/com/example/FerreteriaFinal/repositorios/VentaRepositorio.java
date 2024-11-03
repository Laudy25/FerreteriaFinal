package com.example.FerreteriaFinal.repositorios;

import com.example.FerreteriaFinal.modelos.Venta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VentaRepositorio extends MongoRepository<Venta, String> {
   
}
