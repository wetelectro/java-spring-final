package com.example.demo.services;

import com.example.demo.models.*;
import com.example.demo.repositories.CarritoRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImplementation implements CartService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private EmailServiceImplementation emailService;

    @Override
    public Response createCart(String userEmail) {

        List<User> usuario = usuarioRepository.findByEmail(userEmail);

        Carrito carrito = new Carrito();
        carrito.setEmail(userEmail);
        carrito.setDate(new Date());
        carrito.setAddress(usuario.get(0).getAddress());

        carritoRepository.save(carrito);

        Response response = new Response();
        response.setMessage("Carrito creado para el usuario " + userEmail);
        response.setPayload(carrito);

        return response;
    }

    @Override
    public Response showMyCart(String userEmail) {

        List<Carrito> carrito = carritoRepository.findByEmail(userEmail);

        Response response = new Response();

        if(carrito == null){
            response.setMessage("El usuario " + userEmail + " no tiene carritos");
        }else{
            response.setMessage("Carrito del usuario " + userEmail);
            response.setPayload(carrito);
        }

        return response;
    }

    @Override
    public Response deleteMyCart(String userEmail) {

        Carrito carritoBorrado = carritoRepository.deleteByEmail(userEmail);

        Response response = new Response();

        if (carritoBorrado == null) {
            response.setMessage("No existen carritos del usuario " + userEmail);
        } else {
            response.setMessage("Carrito del usuario " + userEmail + " eliminado");
            response.setPayload(carritoBorrado);
        }

        return response;
    }

    public Response addToCart(String itemId, String email) {

        Carrito carritoViejo = carritoRepository.findByEmail(email).get(0);
        Optional<Producto> producto = productRepository.findById(itemId);

        List<Producto> newList;
        if(carritoViejo.getProductList() == null){
             newList = new ArrayList<Producto>();
        }else{
            newList = carritoViejo.getProductList();
        }

        Response response = new Response();

        if(producto.isPresent()){
            newList.add(producto.get());
            carritoViejo.setProductList(newList);

            Query query = new Query(Criteria.where("email").is(email));
            mongoTemplate.findAndReplace(query, carritoViejo);

            response.setMessage("Item agregado a carrito de " + email);
            response.setPayload(carritoViejo);
        }else{
            response.setMessage("El item no existe y no se pudo agregar");
            response.setPayload(carritoViejo);
        }

        return response;
    }

    public Response deleteFromCart(String itemId, String email) {

        Carrito carritoViejo = carritoRepository.findByEmail(email).get(0);

        List<Producto> newList = carritoViejo.getProductList();

        for (Producto producto : newList) {
            if(producto.getId().equals(itemId)){
                newList.remove(producto);
                break;
            }
        }
        carritoViejo.setProductList(newList);

        Query query = new Query(Criteria.where("email").is(email));
        mongoTemplate.findAndReplace(query, carritoViejo);

        Response response = new Response();
        response.setMessage("Item borrado del carrito de " + email);
        response.setPayload(carritoViejo);

        return response;
    }

    public Response makeOrder(String email) {

        Carrito carrito = carritoRepository.findByEmail(email).get(0);

        OrdenDeCompra orden =  Carrito.createOrder(carrito);

        emailService.sendOrderEmail(orden);

        Response response = new Response();
        response.setMessage("Orden de compra creada");
        response.setPayload(orden);
        return response;
    }

}
