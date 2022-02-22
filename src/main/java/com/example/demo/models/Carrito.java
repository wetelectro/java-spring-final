package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Document
public class Carrito {

    @Id
    private String id;
    private String email;
    @CreatedDate
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date date;
    private String address;
    private List<Producto> productList;

    public static OrdenDeCompra createOrder(Carrito carrito) {
        return new OrdenDeCompra(carrito);
    }

}
