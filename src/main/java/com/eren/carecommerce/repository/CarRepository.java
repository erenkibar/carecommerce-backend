package com.eren.carecommerce.repository;

import com.eren.carecommerce.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {

    Car findCarById(Long id);

    List<Car> findCarsByUserId(String userID);

    void deleteCarByIdAndUserId(Long carID, String userID);

    List<Car> findFirst10ByOrderByCreatedAtDesc();
}
