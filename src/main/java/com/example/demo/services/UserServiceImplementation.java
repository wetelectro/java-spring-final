package com.example.demo.services;

import com.example.demo.models.Response;
import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UsuarioRepository userRepo;

    @Autowired
    private EmailServiceImplementation emailService;

    @Override
    public Response registerUser(Usuario usuario) {

        emailService.sendRegistrationEmail(usuario);

        userRepo.save(usuario);

        Response res = new Response();
        res.setMessage("Usuario registrado");
        res.setPayload(usuario);
        return res;
    }

    public Response getAllUsers() {

        List<Usuario> usuarios = userRepo.findAll();

        Response res = new Response();
        res.setMessage("Lista de todos los usuarios");
        res.setPayload(usuarios);
        return res;
    }


    public Response getUserById(String id) {

        Optional<Usuario> usuario = userRepo.findById(id);

        Response res = new Response();
        res.setMessage("Usuario con id : " + id);
        res.setPayload(usuario);
        return res;
    }

    public Response deleteUserById(String id) {

        Optional<Usuario> usuario = userRepo.findById(id);
        userRepo.deleteById(id);

        Response res = new Response();
        res.setMessage("Usuario con id : " + id + " ha sido eliminado");
        res.setPayload(usuario);
        return res;
    }


    public Response getUserByEmail(String email) {
        List<Usuario> usuarios = userRepo.findByEmail(email);

        Response res = new Response();
        res.setMessage("Usuario con email : " + email);
        res.setPayload(usuarios);
        return res;
    }

}
