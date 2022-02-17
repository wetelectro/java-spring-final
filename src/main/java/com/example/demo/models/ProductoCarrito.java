package com.example.demo.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Data
@Document
public class ProductoCarrito {

    private String itemId;
    private String idProducto;  // Solo guardar id del producto ya que los datos se obtienen al crear la orden de compra
    private int cantidad;

    private static int productCounter = 0;

    public ProductoCarrito() {
        Date date = new Date();
        this.itemId = String.valueOf(date.getTime() + productCounter);
        productCounter = productCounter + ((int)Math.floor(Math.random() * 100));
    }

}
