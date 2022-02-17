package com.example.demo.controllers;

import com.example.demo.models.ErrorResponse;
import com.example.demo.models.exceptions.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {

    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(Exception.class)
    public ErrorResponse exceptionHandler(Exception e) {
        logger.error(e.getMessage());
        ErrorResponse res = new ErrorResponse();
        res.setErrorDetails(e.getMessage());
        return res;
    }

    @ExceptionHandler(CustomException.class)
    public ErrorResponse customExceptionHandler(CustomException e) {
        logger.error(e.getMessage());
        ErrorResponse res = new ErrorResponse();
        res.setErrorDetails(e.getMessage());
        return res;
    }

}
