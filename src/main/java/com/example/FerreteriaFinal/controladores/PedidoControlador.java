package com.example.FerreteriaFinal.controladores;

import com.example.FerreteriaFinal.modelos.Pedido;
import com.example.FerreteriaFinal.servicios.PedidoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@CrossOrigin(origins = "http://localhost:3000")
public class PedidoControlador {

    @Autowired
    private PedidoServicio pedidoServicio;

    @GetMapping
    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoServicio.obtenerTodosLosPedidos();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> obtenerPedidoPorId(@PathVariable String id) {
        return pedidoServicio.obtenerPedidoPorId(id);
    }

    @PostMapping
    public Pedido agregarPedido(@RequestBody Pedido pedido) {
        return pedidoServicio.agregarPedido(pedido);
    }

    @DeleteMapping("/{id}")
    public void eliminarPedido(@PathVariable String id) {
        pedidoServicio.eliminarPedido(id);
    }
}
