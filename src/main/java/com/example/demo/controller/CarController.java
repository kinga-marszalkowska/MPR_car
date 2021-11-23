package com.example.demo.controller;

import com.example.demo.car.Car;
import com.example.demo.exceptions.CarNotFoundException;
import com.example.demo.exceptions.IdAlreadyExistsException;
import com.example.demo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car/{id}")
    public ResponseEntity<Car> getCar(@PathVariable String id){
        Car car = carService.getCarFromRepository(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("/car/add")
    public ResponseEntity<Car> addCar(@RequestBody Car car){
        carService.putCarToRepository(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/car/del/{id}")
    public ResponseEntity<Car> deleteCar(@PathVariable String id){
        carService.deleteCarFromRepository(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/car/update")
    public ResponseEntity<Car> updateCar(@RequestBody Car car){
        carService.updateCarInRepository(car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }


}
