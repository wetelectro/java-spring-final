package com.example.demo.controllers;

import com.example.demo.models.Response;
import com.example.demo.services.CartServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartController {

    @Autowired
    CartServiceImplementation cartService;

    @GetMapping("/cart/{email}")
    public Response getCartById(@PathVariable String email) {
        return cartService.showMyCart(email);
    }

    @DeleteMapping("/cart/{email}")
    public Response deleteCartById(@PathVariable String email) {
        return cartService.deleteMyCart(email);
    }

    @PostMapping("/cart/{email}")
    public Response createCart(@PathVariable String email) {
        return cartService.createCart(email);
    }

    @GetMapping("/cart/{email}/add/{itemId}")
    public Response addItemToCart(@PathVariable String email, @PathVariable String itemId) {
        return cartService.addToCart(itemId, email);
    }

    @GetMapping("/cart/{email}/delete/{itemId}")
    public Response deleteItemToCart(@PathVariable String email, @PathVariable String itemId) {
        return cartService.deleteFromCart(itemId, email);
    }

    @GetMapping("/cart/{email}/makeorder")
    public Response makeOrder(@PathVariable String email) {
        return cartService.makeOrder(email);
    }

}
