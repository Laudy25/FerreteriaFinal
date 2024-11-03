package com.example.FerreteriaFinal.servicios;

import com.example.FerreteriaFinal.modelos.Pedido;
import com.example.FerreteriaFinal.repositorios.PedidoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoServicio {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    public List<Pedido> obtenerTodosLosPedidos() {
        return pedidoRepositorio.findAll();
    }

    public Optional<Pedido> obtenerPedidoPorId(String id) {
        return pedidoRepositorio.findById(id);
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }

    public void eliminarPedido(String id) {
        pedidoRepositorio.deleteById(id);
    }

    public Pedido agregarPedido(Pedido pedido) {
        return pedidoRepositorio.save(pedido);
    }
}
