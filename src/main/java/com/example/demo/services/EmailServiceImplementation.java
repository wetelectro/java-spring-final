package com.example.demo.services;

import com.example.demo.models.Usuario;
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

    public void sendRegistrationEmail(Usuario usuario) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(usuario.getEmail());
        simpleMailMessage.setSubject("Creacion de Cuenta");
        simpleMailMessage.setText(
                "Alta de usuario " + usuario.getCompleteName() + ", le dejamos sus credenciales:\n" +
                "email: " + usuario.getEmail() + "\n" +
                "password: " + usuario.getPassword() + "\n" +
                "por favor, no comparta esta informacion."
        );

        javaMailSender.send(simpleMailMessage);
    }
}
