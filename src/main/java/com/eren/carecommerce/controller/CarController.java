package com.eren.carecommerce.controller;

import com.eren.carecommerce.model.Car;
import com.eren.carecommerce.request.CarRequest;
import com.eren.carecommerce.response.CarResponse;
import com.eren.carecommerce.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class CarController {
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable String id){
        CarResponse car = service.getCarById(id);
        if(car != null) {
            return new ResponseEntity<>(car, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Car> createCar (@RequestBody CarRequest car){
        System.out.println(car.toString());
        Car savedCar = service.saveCar(car);
        if(savedCar != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String id){
        service.deleteCar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
