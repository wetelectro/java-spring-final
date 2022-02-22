package com.example.demo.services;

import com.example.demo.models.LoginRequest;
import com.example.demo.models.Response;
import com.example.demo.models.User;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public Response registerUser(User user) {

        emailService.sendRegistrationEmail(user);

//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        user.setPassword( encoder.encode(user.getPassword()) );

        userRepo.save(user);

        Response res = new Response();
        res.setMessage("User registrado");
        res.setPayload(user);
        return res;
    }

    public Response loginUser(LoginRequest userData) {

//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        List<User> userList = userRepo.findByEmail(userData.getEmail());
        Response res = new Response();

        if (userList.isEmpty()){
            res.setMessage("User no registrado");
            res.setPayload("Email ingresado : " + userData.getEmail());
        }else{
            User myUser =  userList.get(0);
            //if ( encoder.matches(userData.getPassword(), myUser.getPassword()) ){
            if (userData.getPassword().equals(myUser.getPassword())){
                res.setMessage("Has ingresado a tu cuenta");
                res.setPayload("Email ingresado : " + userData.getEmail());

            }else{
                res.setMessage("Credenciales incorrectas");
                res.setPayload("Email ingresado : " + userData.getEmail());
            }
        }

        return res;
    }

    public Response getAllUsers() {

        List<User> users = userRepo.findAll();

        Response res = new Response();
        res.setMessage("Lista de todos los users");
        res.setPayload(users);
        return res;
    }


    public Response getUserById(String id) {

        Optional<User> usuario = userRepo.findById(id);

        Response res = new Response();
        res.setMessage("User con id : " + id);
        res.setPayload(usuario);
        return res;
    }

    public Response deleteUserById(String id) {

        Optional<User> usuario = userRepo.findById(id);
        userRepo.deleteById(id);

        Response res = new Response();
        res.setMessage("User con id : " + id + " ha sido eliminado");
        res.setPayload(usuario);
        return res;
    }


    public Response getUserByEmail(String email) {
        List<User> users = userRepo.findByEmail(email);

        Response res = new Response();
        res.setMessage("User con email : " + email);
        res.setPayload(users);
        return res;
    }

}
