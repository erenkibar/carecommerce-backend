package com.eren.carecommerce.repository;

import com.eren.carecommerce.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {


    List<Brand> findAll();


}
