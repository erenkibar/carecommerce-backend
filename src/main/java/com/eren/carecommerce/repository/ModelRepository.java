package com.eren.carecommerce.repository;

import com.eren.carecommerce.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findAllByBrandId(Long id);
    List<Model> findAllByName(String name);
}
