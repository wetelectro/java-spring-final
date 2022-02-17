package com.example.demo.services;

import com.example.demo.models.Response;
import com.example.demo.models.Usuario;

public interface UserService {

    Response getAllUsers();
    Response registerUser(Usuario usuario);
    Response getUserById(String id);
    Response deleteUserById(String id);
    Response getUserByEmail(String email);
}
