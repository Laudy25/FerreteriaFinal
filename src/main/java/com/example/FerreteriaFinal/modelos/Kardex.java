package com.example.FerreteriaFinal.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "kardex")
public class Kardex {
    @Id
    private String id;
    private String productoNombre;
    private String producto;
    private int cantidad;
    private String tipoMovimiento; 
    private Date fecha;
    private int saldo; 
    
    public String getId() { 
        return id; 
    }
    public void setId(String id) { 
        this.id = id; 
    }

    public String getProductoNombre() { return productoNombre; }
    public void setProductoNombre(String productoNombre) { this.productoNombre = productoNombre; }

    public String getProducto() { 
        return producto; 
    }
    public void setProducto(String producto) { 
        this.producto = producto; 
    }

    public int getCantidad() { 
        return cantidad; 
    }
    public void setCantidad(int cantidad) { 
        this.cantidad = cantidad; 
    }

    public String getTipoMovimiento() { 
        return tipoMovimiento; 
    }
    public void setTipoMovimiento(String tipoMovimiento) { 
        this.tipoMovimiento = tipoMovimiento; 
    }

    public Date getFecha() { 
        return fecha; 
    }
    public void setFecha(Date fecha) { 
        this.fecha = fecha; 
    }

    public int getSaldo() { 
        return saldo; 
    }
    public void setSaldo(int saldo) { 
        this.saldo = saldo; 
    }
}