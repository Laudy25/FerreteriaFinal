package com.example.FerreteriaFinal.servicios;

import com.example.FerreteriaFinal.modelos.Kardex;
import com.example.FerreteriaFinal.modelos.Producto;
import com.example.FerreteriaFinal.repositorios.KardexRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KardexServicio {
    @Autowired
    private KardexRepositorio kardexRepositorio;

    @Autowired
    private ProductoServicio productoServicio;

    public List<Kardex> obtenerMovimientos() {
        return kardexRepositorio.findAll();
    }

    public Kardex registrarMovimiento(Kardex movimiento) {
        Optional<Producto> productoOpt = productoServicio.obtenerProductoPorNombre(movimiento.getProductoNombre());

        if (productoOpt.isPresent()) {
            Producto producto = productoOpt.get();
            int saldoActual = producto.getStock();

            if ("Entrada".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
                movimiento.setSaldo(saldoActual + movimiento.getCantidad());
                producto.setStock(saldoActual + movimiento.getCantidad());
            } else if ("Salida".equalsIgnoreCase(movimiento.getTipoMovimiento())) {
                movimiento.setSaldo(saldoActual - movimiento.getCantidad());
                producto.setStock(saldoActual - movimiento.getCantidad());
            }

            productoServicio.guardarProducto(producto);
            return kardexRepositorio.save(movimiento);
        } else {
            throw new RuntimeException("Producto no encontrado para el movimiento en el kardex");
        }
    }

    public Kardex guardarKardex(Kardex movimiento) {
        return kardexRepositorio.save(movimiento);
    }
}



