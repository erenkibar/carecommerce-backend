package com.eren.carecommerce.service;

import com.eren.carecommerce.model.*;
import com.eren.carecommerce.repository.BrandRepository;
import com.eren.carecommerce.repository.CarRepository;
import com.eren.carecommerce.repository.ModelRepository;
import com.eren.carecommerce.repository.UserRepository;
import com.eren.carecommerce.request.CarRequest;
import com.eren.carecommerce.response.CarResponse;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Car saveCar(CarRequest carRequest, String email){
        String partSeparator = ",";

        Optional<User> user = userRepository.findUserByEmail(email);
        Brand brand = brandRepository.getReferenceById(Long.parseLong(carRequest.getBrandID()));
        Model model = modelRepository.getReferenceById(Long.parseLong(carRequest.getModelID()));

        List<byte[]> imageBytes = carRequest.getImages().stream()
                .map(Base64.getDecoder()::decode)
                .collect(Collectors.toList());

        var car = Car.builder()
                .year(carRequest.getYear())
                .images(imageBytes)
                .mileage(carRequest.getMileage())
                .price(carRequest.getPrice())
                .title(carRequest.getTitle())
                .description(carRequest.getDescription())
                .fuelType(FuelType.valueOf(carRequest.getFuelType()))
                .transmissionType(TransmissionType.valueOf(carRequest.getTransmissionType()))
                .color(Color.valueOf(carRequest.getColor()))
                .user(user.get())
                .model(model)
                .brand(brand)
                .build();
        return carRepository.save(car);
    }

    //Delete a car
    public void deleteCarById(String carID){
        Long cID = Long.parseLong(carID);
        carRepository.deleteCarById(cID);
    }


    //Get a single car by ID
    public CarResponse getCarById(String carID){
        Long cID = Long.parseLong(carID);
        var car = carRepository.findCarById(cID);
        List<String> base64 = car.getImages().stream()
                .map(Base64.getEncoder()::encodeToString)
                .collect(Collectors.toList());
        return CarResponse.builder()
                .year(car.getYear())
                .images(base64)
                .mileage(car.getMileage())
                .description(car.getDescription())
                .title(car.getTitle())
                .price(car.getPrice())
                .fuelType(String.valueOf(car.getFuelType()))
                .transmissionType(String.valueOf(car.getTransmissionType()))
                .color(String.valueOf(car.getColor()))
                .numberOfDoors(String.valueOf(car.getNumberOfDoors()))
                .engineSize(car.getEngineSize())
                .firstName(car.getUser().getFirstName())
                .lastName(car.getUser().getLastName())
                .brand(car.getBrand().getName())
                .model(car.getModel().getName())
                .build();
    }

    //Get all cars of a given user
    public List<Car> getAllCarsByUserEmail(String username){
        Optional<User> user = userRepository.findUserByEmail(username);
        return carRepository.findCarsByUserId(user.get().getId());
    }
}
