package com.example.FerreteriaFinal.repositorios;

import com.example.FerreteriaFinal.modelos.Kardex;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KardexRepositorio extends MongoRepository<Kardex, String> {
}
