package com.example.demo.controllers;

import com.example.demo.models.Producto;
import com.example.demo.models.Response;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public Response readAll() {
        return productService.getAllProducts();
    }

    @PostMapping("/product")
    public Response create(@RequestBody Producto producto) {
        return productService.createProduct(producto);
    }

    @GetMapping("/product/{id}")
    public Response readById(@PathVariable String id) {
        return productService.readProductById(id);
    }

    @PutMapping("/product/{id}")
    public Response updateById(@PathVariable String id, @RequestBody Producto producto) {
        return productService.updateProductById(id, producto);
    }

    @DeleteMapping("/product/{id}")
    public Response deleteById(@PathVariable String id) {
        return productService.deleteProductById(id);
    }

    @DeleteMapping("/products")
    public Response deleteAll() {
        return productService.deleteAllProducts();
    }

}
