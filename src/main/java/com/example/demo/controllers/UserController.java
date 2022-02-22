package com.example.demo.controllers;

import com.example.demo.models.LoginRequest;
import com.example.demo.models.Response;
import com.example.demo.models.User;
import com.example.demo.services.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserServiceImplementation userService;

    @GetMapping("/user/all")
    public Response getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{id}")
    public Response getUserById(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user/email/{address}")
    public Response getUserByEmail(@PathVariable String address) {
        return userService.getUserByEmail(address);
    }

    @PostMapping("/user")
    public Response registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @DeleteMapping("/user/{id}")
    public Response deleteUserById(@PathVariable String id) {
        return userService.deleteUserById(id);
    }

    @PostMapping("/user/login")
    public Response loginUser(@RequestBody LoginRequest userData){
        return  userService.loginUser(userData);
    }

}
