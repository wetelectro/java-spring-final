package com.example.demo.services;

import com.example.demo.models.Response;

public interface CartService {

    Response createCart(String userEmail);
    Response showMyCart(String userEmail);
    Response deleteMyCart(String userEmail);
    Response addToCart(String itemId, String email);
    Response deleteFromCart(String itemId, String email);
    Response makeOrder(String email);
}
