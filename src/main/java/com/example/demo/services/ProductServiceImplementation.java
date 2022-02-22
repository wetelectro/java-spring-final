package com.example.demo.services;

import com.example.demo.models.Producto;
import com.example.demo.models.Response;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductServiceImplementation implements ProductService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ProductRepository productRepository;

    public Response getAllProducts() {

        List<Producto> productos =  productRepository.findAll();

        Response res = new Response();
        res.setMessage("Lista de todos los productos");
        res.setPayload(productos);
        return res;
    }

    public Response createProduct(Producto producto) {

        productRepository.save(producto);

        Response res = new Response();
        res.setMessage("Nuevo producto creado");
        res.setPayload(producto);
        return res;
    }

    public Response readProductById(String id) {

        Optional<Producto> productFromDB = productRepository.findById(id);

        Response res = new Response();
        if(productFromDB == null){
            res.setMessage("El producto de ID:" + id + " no existe");
        }else{
            res.setMessage("Producto de ID:" + id);
            res.setPayload(productFromDB);
        }
        return res;
    }

    public Response updateProductById(String id, Producto producto) {

        Optional<Producto> productFromDB = productRepository.findById(id);

        Response res = new Response();

        if(productFromDB.isPresent()){
            if(producto.getCategory() == null){ producto.setCategory(productFromDB.get().getCategory()); }
            if(producto.getDescription() == null){ producto.setDescription(productFromDB.get().getDescription()); }
            if(producto.getPrice() == null){ producto.setPrice(productFromDB.get().getPrice()); }
            if(producto.getImageURL() == null){ producto.setImageURL(productFromDB.get().getImageURL()); }

            // Replacement before getting the old values
            Query query = new Query(Criteria.where("_id").is(id));
            mongoTemplate.findAndReplace(query, producto);

            res.setMessage("El producto con id:" + id + " fue actualizado");
            res.setPayload(producto);
        }

        res.setMessage("El producto con id:" + id + " no existe");
        return res;
    }

    public Response deleteProductById(String id) {

        Optional<Producto> productFromDB = productRepository.findById(id);
        productRepository.deleteById(id);

        Response res = new Response();
        res.setMessage("El producto con id:" + id + " fue eliminado");
        res.setPayload(productFromDB);
        return res;
    }

    public Response deleteAllProducts() {
        productRepository.deleteAll();

        Response res = new Response();
        res.setMessage("Todos los productos fueron eliminados");
        return res;
    }

}
