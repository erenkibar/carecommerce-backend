package com.eren.carecommerce.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    private String year;
    private List<String> images;
    private String mileage;
    private String price;
    private String title;
    private String description;
    private String fuelType;
    private String transmissionType;
    private String numberOfDoors;
    private String color;
    private String engineSize;
    private String userID;
    private String modelID;
    private String brandID;
}
