package com.example.demo.exceptions;

import com.example.demo.car.Car;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<Car> handleNotFound(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IdAlreadyExistsException.class)
    public ResponseEntity<Car> handleIdNotFound(){
        return ResponseEntity.badRequest().build();
    }
}
