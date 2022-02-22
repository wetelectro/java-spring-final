package com.example.demo.services;

import com.example.demo.models.OrdenDeCompra;
import com.example.demo.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImplementation implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendTestEmail() {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setText("Hola desde Java Spring Boot");
        simpleMailMessage.setTo("wet.4gustin@gmail.com");
        simpleMailMessage.setSubject("Java Mailer Test");

        javaMailSender.send(simpleMailMessage);
    }

    public void sendRegistrationEmail(User user) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(user.getEmail());
        simpleMailMessage.setSubject("Creacion de Cuenta");
        simpleMailMessage.setText(
                "Alta de user " + user.getCompleteName() + ", le dejamos sus credenciales:\n" +
                "email: " + user.getEmail() + "\n" +
                "password: " + user.getPassword() + "\n" +
                "por favor, no comparta esta informacion."
        );

        javaMailSender.send(simpleMailMessage);
    }

    public void sendOrderEmail(OrdenDeCompra ordenDeCompra) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(ordenDeCompra.getEmail());
        simpleMailMessage.setSubject("Nueva Orden de compra");
        simpleMailMessage.setText(
                "Orden de compra :\n" +
                ordenDeCompra.toString()
        );

        javaMailSender.send(simpleMailMessage);
    }
}
