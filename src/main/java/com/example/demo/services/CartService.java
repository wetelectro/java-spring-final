package com.example.demo.services;

import com.example.demo.models.ProductoCarrito;
import com.example.demo.models.Response;

public interface CartService {

    Response showMyCart(String userEmail);
    Response deleteMyCart(String userEmail);

    Response addToCart(ProductoCarrito productoCarrito);

}
