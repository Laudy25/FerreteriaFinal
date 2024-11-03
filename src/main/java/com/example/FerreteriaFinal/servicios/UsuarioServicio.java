package com.example.FerreteriaFinal.servicios;

import com.example.FerreteriaFinal.modelos.Usuario;
import com.example.FerreteriaFinal.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public Usuario agregarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepositorio.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(String id) {
        return usuarioRepositorio.findById(id);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    public void eliminarUsuario(String id) {
        usuarioRepositorio.deleteById(id);
    }

    public Usuario obtenerUsuarioPorUsername(String username) {
        return usuarioRepositorio.findByUsername(username);
    }
}

