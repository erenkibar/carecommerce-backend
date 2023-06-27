package com.eren.carecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String year;

    private String mileage;

    private String price;

    private String title;

    private String description;

    private FuelType fuelType;

    private TransmissionType transmissionType;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER)
    private Model model;


}
