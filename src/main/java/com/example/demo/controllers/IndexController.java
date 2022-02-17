package com.example.demo.controllers;

import com.example.demo.models.Response;
import com.example.demo.models.exceptions.CustomException;
import com.example.demo.services.EmailService;
import com.example.demo.services.EmailServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class IndexController {

    @Autowired
    private EmailServiceImplementation emailService;

    @GetMapping("/")
    public Response home() {
        Response res = new Response();
        res.setPayload("Ecommerce API by Agustin Wet");
        return res;
    }

    @GetMapping("/simerror")
    public Response error500() throws Exception {
        Response res = new Response();
        int result = 1000000/0;
        res.setPayload(result);
        return res;
    }

    @GetMapping("/simcustomerror")
    public Response errorCustom() throws CustomException {
        Response res = new Response();
        throw new CustomException();
    }

    @GetMapping("/email")
    public Response sendEmail() {

        emailService.sendTestEmail();

        Response res = new Response();
        res.setMessage("Email sended");
        return res;
    }
}
