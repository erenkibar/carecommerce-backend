package com.eren.carecommerce.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {

    private String year;
    private String mileage;
    private String price;
    private String title;
    private String description;
    private String fuelType;
    private String transmissionType;
    private String userID;
    private String modelID;
    private String brandID;
}
