package com.example.demo.services;

import com.example.demo.models.LoginRequest;
import com.example.demo.models.Response;
import com.example.demo.models.User;

public interface UserService {

    Response getAllUsers();
    Response registerUser(User user);
    Response loginUser(LoginRequest userData);
    Response getUserById(String id);
    Response deleteUserById(String id);
    Response getUserByEmail(String email);
}
