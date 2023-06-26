package com.eren.carecommerce.service;

import com.eren.carecommerce.model.Model;
import com.eren.carecommerce.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelService {
    private final ModelRepository repository;

    public List<Model> getAllModelsById(String id){
        Long longId = Long.parseLong(id);
        return repository.findAllByBrandId(longId);

    }

    public List<Model> getAllModelsByName(String name){
        return repository.findAllByName(name);

    }


}
