package com.example.FerreteriaFinal.repositorios;

import com.example.FerreteriaFinal.modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {
    Usuario findByUsername(String username);
}
