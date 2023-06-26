package com.eren.carecommerce.controller;

import com.eren.carecommerce.model.Model;
import com.eren.carecommerce.service.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/model")
@CrossOrigin(origins = "*")
public class ModelController {
    private final ModelService service;

    public ModelController(ModelService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Model>> getModelsById(@PathVariable String id) {
        return ResponseEntity.ok(service.getAllModelsById(id));
    }
}
