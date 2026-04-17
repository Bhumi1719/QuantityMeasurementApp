package com.example.quantityapp.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(QuantityMeasurementException.class)
    public ResponseEntity<String> handleException(QuantityMeasurementException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception e) {
        return ResponseEntity.internalServerError().body("Internal error: " + e.getMessage());
    }
}
