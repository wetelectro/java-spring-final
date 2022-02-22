package com.example.demo.repositories;

import com.example.demo.models.Carrito;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CarritoRepository extends MongoRepository<Carrito,String> {

    List<Carrito> findByEmail(String email);
    Carrito deleteByEmail(String email);

}
