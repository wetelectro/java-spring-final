package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Producto {

    @Id
    private String id;
    private String description;
    private String imageURL;
    private Double price;
    private String category;

}
