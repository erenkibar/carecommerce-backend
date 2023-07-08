package com.eren.carecommerce.controller;

import com.eren.carecommerce.dto.CarSearchDto;
import com.eren.carecommerce.model.Car;
import com.eren.carecommerce.model.CarSpecificationBuilder;
import com.eren.carecommerce.model.User;
import com.eren.carecommerce.request.CarRequest;
import com.eren.carecommerce.request.SearchCriteria;
import com.eren.carecommerce.response.CarResponse;
import com.eren.carecommerce.service.CarService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*")
public class CarController {
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }


    @GetMapping("/user/listing")
    public List<Car> getCarsByUserId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Car> carList = service.getAllCarsByUserEmail(userDetails.getUsername());
        return carList;
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

    @GetMapping("/latest")
    public List<Car> getLatestCars() {
        List<Car> carList = service.getLatestCars();
        return carList;
    }

    @PostMapping("/add")
    public ResponseEntity<Car> createCar (@RequestBody CarRequest car){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Car savedCar = service.saveCar(car, userDetails.getUsername());
        if(savedCar != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable String id){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        service.deleteCarById(id, userDetails.getUsername());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Car>> searchAndFilterCars(@RequestBody CarSearchDto carDto){
        CarSpecificationBuilder builder = new CarSpecificationBuilder();
        List<SearchCriteria> criteriaList = carDto.getSearchCriteriaList();

        if(criteriaList != null){
            criteriaList.forEach(x->
            {x.setDataOption(carDto.getDataOption());
                builder.with(x);
            });
        }

        List<Car> carList = service.findBySearchCriteria(builder.build());

        return new ResponseEntity<>(carList, HttpStatus.OK);



    }

    @GetMapping("/all")
    public ResponseEntity<List<Car>> getAllCars() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> INSIDE IF");
            List<Car> carList = service.getAllCars();
            return new ResponseEntity<>(carList, HttpStatus.OK);
        } else {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> INSIDE ELSE");
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/all/{id}")
    public ResponseEntity<Void> deleteCarById(@PathVariable String id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
            service.deleteCar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }



}
