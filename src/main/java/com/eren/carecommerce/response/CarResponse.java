package com.eren.carecommerce.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private String year;
    private String mileage;
    private String price;
    private String title;
    private String description;
    private String fuelType;
    private String transmissionType;
    private String firstName;
    private String lastName;
    private String brand;
    private String model;
}
