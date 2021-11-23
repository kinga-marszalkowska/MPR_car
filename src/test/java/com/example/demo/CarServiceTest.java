package com.example.demo;
import com.example.demo.car.Car;
import com.example.demo.controller.CarController;
import com.example.demo.exceptions.CarNotFoundException;
import com.example.demo.service.CarRepository;
import com.example.demo.service.CarService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CarController.class)
public class CarServiceTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarService carService;

    @Test
    public void getCarFromRepositoryThatDoesNotExistAndThrowException() throws Exception, CarNotFoundException{
       // when(carService.getCarFromRepository("100")).thenReturn(null);

        mvc.perform(MockMvcRequestBuilders.get("/car/100")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getCarFromRepositoryReturnsCar() throws Exception, CarNotFoundException {
        Car testCar = new Car("tesla", 2021);
        when(carService.getCarFromRepository("2")).thenReturn(testCar);

        mvc.perform(MockMvcRequestBuilders.get("/car/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 2,'name': tesla, 'year': 2021}"));
    }
    @Test
    public void addCarToRepository(){

    }
    @Test
    public void addCarToRepositoryWhereIdAlreadyExists(){

    }


}
