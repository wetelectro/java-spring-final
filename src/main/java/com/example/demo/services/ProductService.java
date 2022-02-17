package com.example.demo.services;

import com.example.demo.models.Producto;
import com.example.demo.models.Response;

public interface ProductService {

    Response getAllProducts();
    Response createProduct(Producto producto);
    Response readProductById(String id);
    Response updateProductById(String id, Producto producto);
    Response deleteProductById(String id);
    Response deleteAllProducts();
}
