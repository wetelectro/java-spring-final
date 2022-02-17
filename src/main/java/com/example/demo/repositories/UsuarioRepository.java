package com.example.demo.repositories;

import com.example.demo.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    public List<Usuario> findByEmail(String email);

}
