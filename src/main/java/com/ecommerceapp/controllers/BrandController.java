package com.ecommerceapp.controllers;


import com.ecommerceapp.entitys.Brand;
import com.ecommerceapp.exceptionHandlers.EntityNotFoundException;
import com.ecommerceapp.services.BrandService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandController {

    public static final Logger logger = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    @PostMapping("/addBrand")
    public ResponseEntity<Brand> addBrand(@RequestBody @Valid Brand brand) {
        brandService.addOrSaveBrand(brand);
        logger.info(brand.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(brand);
    }


    @GetMapping("/getAllBrands")
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> allBrands = brandService.getAllBrands();
        logger.info(allBrands.toString());
        return ResponseEntity.ok(allBrands);
    }


    @GetMapping("/getBrand/{id}")
    public ResponseEntity<?> getBrand(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }


    @PutMapping("/updateBrand/{id}")
    public ResponseEntity<?> updateBrand(@PathVariable Long id, @RequestBody @Valid Brand brand) throws EntityNotFoundException {
        Brand existingBrand =brandService.getBrandById(id);
        existingBrand.setB_name(brand.getB_name());
        brandService.addOrSaveBrand(existingBrand);
        return ResponseEntity.ok("Brand details updated successfully");
    }


    @DeleteMapping("/deleteBrand/{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id) throws EntityNotFoundException {
        Brand existingBrand =brandService.getBrandById(id);
        brandService.deleteBrand(id);
        return ResponseEntity.ok("Brand deleted successfully");
    }
}
