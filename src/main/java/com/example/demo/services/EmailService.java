package com.example.demo.services;

import com.example.demo.models.OrdenDeCompra;
import com.example.demo.models.User;

public interface EmailService {
    void sendTestEmail();
    void sendOrderEmail(OrdenDeCompra ordenDeCompra);
    void sendRegistrationEmail(User user);
}
