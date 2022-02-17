package com.example.demo.repositories;

import com.example.demo.models.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Producto, String> {

}
