package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class User {

    @Id
    private String id;
    private String completeName;
    private String telephone;
    @Indexed(unique = true)
    private String email;
    private String password;
    private String token;
    private String address;
}
