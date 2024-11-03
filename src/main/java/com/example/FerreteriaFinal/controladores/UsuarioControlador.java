package com.example.FerreteriaFinal.controladores;

import com.example.FerreteriaFinal.dto.LoginRequest;
import com.example.FerreteriaFinal.modelos.Usuario;
import com.example.FerreteriaFinal.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioServicio.obtenerTodosLosUsuarios();
    }

    @GetMapping("/{id}")
    public Optional<Usuario> obtenerUsuarioPorId(@PathVariable String id) {
        return usuarioServicio.obtenerUsuarioPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable String id) {
        usuarioServicio.eliminarUsuario(id);
    }

    @PostMapping
    public Usuario agregarUsuario(@RequestBody Usuario usuario) {
        return usuarioServicio.agregarUsuario(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> autenticarUsuario(@RequestBody LoginRequest loginRequest) {
        Usuario usuario = usuarioServicio.obtenerUsuarioPorUsername(loginRequest.getUsername());

        if (usuario != null && usuario.getContrasena().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok().body(Map.of("role", usuario.getRol()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        }
    }
}
