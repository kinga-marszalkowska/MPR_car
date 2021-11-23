package com.example.demo.service;

import com.example.demo.car.Car;
import com.example.demo.exceptions.CarNotFoundException;
import com.example.demo.exceptions.IdAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarService {
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;

        carRepository.save(new Car( "Citroen", 1999));
        carRepository.save(new Car("Skoda", 2010));
        carRepository.save(new Car("Opel", 2002));
    }

    public Car getCarFromRepository(String id) {
        Car car = carRepository.findById(Long.parseLong(id));
        if(car == null){
            throw new CarNotFoundException();
        }else return car;
    }

    public void putCarToRepository(Car car){
        Car car1 = carRepository.findById(Long.parseLong(String.valueOf(car.getId())));
        if(car1 == null)
            carRepository.save(car);
        else throw new IdAlreadyExistsException();
    }

    public void deleteCarFromRepository(String id){
        if(getCarFromRepository(id) == null) throw new CarNotFoundException();
        carRepository.delete(getCarFromRepository(id));
    }

    public void updateCarInRepository(Car car){
        Car carToUpdate = carRepository.findById(car.getId());
        if (carToUpdate != null){
            carToUpdate.setName(car.getName());
            carToUpdate.setYear(car.getYear());
            carRepository.save(carToUpdate);
        }else {
            throw new CarNotFoundException();
        }

    }


}
