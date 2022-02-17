package com.example.demo.models;

import lombok.Data;

@Data
public class ErrorResponse {
    private String errorId;
    private String errorDetails;

    static int idCounter = 0;

    public ErrorResponse() {
        ErrorResponse.idCounter++;
        this.errorId = String.valueOf(ErrorResponse.idCounter);
    }
}
