package com.example.demo.models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrdenDeCompra {

    private int numeroDeOrden;
    private Date horaYFecha;
    private EstadosDeOrden estado;
    private String email;
    private List<Producto> productos;

    static int currentOrderNumber;

    public OrdenDeCompra(Carrito carrito) {
        this.numeroDeOrden = currentOrderNumber;
        OrdenDeCompra.currentOrderNumber++;
        this.email = carrito.getEmail();
        this.estado = EstadosDeOrden.GENERADA;
        this.horaYFecha = new Date();
        this.productos = carrito.getProductList();
    }

}
