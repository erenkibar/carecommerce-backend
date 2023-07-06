package com.eren.carecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @Column(columnDefinition = "bytea")
    private List<byte[]> images;

    private String year;

    private String mileage;

    private String price;

    private String title;

    private String description;

    private FuelType fuelType;

    private TransmissionType transmissionType;

    private NumberOfDoors numberOfDoors;

    private Color color;

    private String engineSize;

    @CreationTimestamp
    private Date createdAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Brand brand;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Model model;


}
