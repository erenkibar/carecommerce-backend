package com.eren.carecommerce.controller;

import com.eren.carecommerce.model.Brand;
import com.eren.carecommerce.service.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin(origins = "*")
public class BrandController {

    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping("/get-all-brands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        return ResponseEntity.ok(service.getAllBrands());
    }
}
