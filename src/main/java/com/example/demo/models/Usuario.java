package com.example.demo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Usuario {

    @Id
    private String id;
    private String completeName;
    private String telephone;
    @Indexed(unique = true)
    private String email;
    private String password;

    @Override
    public String toString() {
        return "User :: [ email: " + this.getEmail() + " ; password: " + this.getPassword() + " ]";
    }

}
