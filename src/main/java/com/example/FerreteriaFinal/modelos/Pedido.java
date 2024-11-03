package com.example.FerreteriaFinal.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "pedidos")
public class Pedido {
    @Id
    private String id;
    private String cliente;
    private List<String> productos; 
    private Date fecha;
    private String estado;
    private double total;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public List<String> getProductos() { return productos; }
    public void setProductos(List<String> productos) { this.productos = productos; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}

