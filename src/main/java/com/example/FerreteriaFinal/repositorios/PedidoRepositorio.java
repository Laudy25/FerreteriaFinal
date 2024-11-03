package com.example.FerreteriaFinal.repositorios;

import com.example.FerreteriaFinal.modelos.Pedido;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PedidoRepositorio extends MongoRepository<Pedido, String> {
}
