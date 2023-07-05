package com.eren.carecommerce.repository;

import com.eren.carecommerce.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>, JpaSpecificationExecutor {

    Car findCarById(Long id);

    List<Car> findCarsByUserId(String userID);

    void deleteCarByIdAndUserId(Long carID, String userID);

    List<Car> findFirst10ByOrderByCreatedAtDesc();
}
