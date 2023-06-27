package com.eren.carecommerce.service;

import com.eren.carecommerce.model.*;
import com.eren.carecommerce.repository.BrandRepository;
import com.eren.carecommerce.repository.CarRepository;
import com.eren.carecommerce.repository.ModelRepository;
import com.eren.carecommerce.repository.UserRepository;
import com.eren.carecommerce.request.CarRequest;
import com.eren.carecommerce.response.CarResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;
    private final BrandRepository brandRepository;

    private final ModelRepository modelRepository;

    public CarService(CarRepository carRepository, UserRepository userRepository, BrandRepository brandRepository, ModelRepository modelRepository) {
        this.carRepository = carRepository;
        this.userRepository = userRepository;
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    //Add a car
    public Car saveCar(CarRequest carRequest){
        User user = userRepository.getReferenceById(Long.parseLong(carRequest.getUserID()));
        Brand brand = brandRepository.getReferenceById(Long.parseLong(carRequest.getBrandID()));
        Model model = modelRepository.getReferenceById(Long.parseLong(carRequest.getModelID()));

        var car = Car.builder()
                .year(carRequest.getYear())
                .mileage(carRequest.getMileage())
                .price(carRequest.getPrice())
                .title(carRequest.getTitle())
                .description(carRequest.getDescription())
                .fuelType(FuelType.valueOf(carRequest.getFuelType()))
                .transmissionType(TransmissionType.valueOf(carRequest.getTransmissionType()))
                .user(user)
                .model(model)
                .brand(brand)
                .build();
        return carRepository.save(car);
    }

    //Delete a car
    public void deleteCar(String id){
        Long carID = Long.parseLong(id);
        carRepository.deleteCarById(carID);
    }


    //Get a single car by ID
    public CarResponse getCarById(String carID){
        Long cID = Long.parseLong(carID);
        var car = carRepository.findCarById(cID);
        return CarResponse.builder()
                .year(car.getYear())
                .mileage(car.getMileage())
                .brand(String.valueOf(car.getBrand()))
                .model(String.valueOf(car.getModel()))
                .description(car.getDescription())
                .title(car.getTitle())
                .price(car.getPrice())
                .fuelType(String.valueOf(car.getFuelType()))
                .transmissionType(String.valueOf(car.getTransmissionType()))
                .firstName(car.getUser().getFirstName())
                .lastName(car.getUser().getLastName())
                .brand(car.getBrand().getName())
                .model(car.getModel().getName())
                .build();
    }

    //Get all cars of a given user
    public List<Car> getAllCarsByUserId(String userId){
        Long uID = Long.parseLong(userId);
        return carRepository.findCarsByUserId(uID);
    }
}
