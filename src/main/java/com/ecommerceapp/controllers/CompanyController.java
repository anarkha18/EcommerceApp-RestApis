package com.ecommerceapp.controllers;

import com.ecommerceapp.entitys.Brand;
import com.ecommerceapp.entitys.Company;
import com.ecommerceapp.exceptionHandlers.EntityNotFoundException;
import com.ecommerceapp.services.BrandService;
import com.ecommerceapp.services.CompService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/companies")
public class CompanyController {
    public static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

    @Autowired
    private CompService compService;

    @PostMapping("/addCompany")
    public ResponseEntity<Company> addCompany(@RequestBody @Valid Company company) {
        compService.addOrSaveCompany(company);
        logger.info(company.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }

    @GetMapping("/getAllCompanies")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> allCompanies = compService.getAllCompanies();
        logger.info(allCompanies.toString());
        return ResponseEntity.ok(allCompanies);
    }


    @GetMapping("/getCompany/{id}")
    public ResponseEntity<?> getCompany(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(compService.getCompanyById(id));
    }


    @PutMapping("/updateCompany/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long id, @RequestBody @Valid Company company) throws EntityNotFoundException {
        Company existingCompany =compService.getCompanyById(id);
        existingCompany.setComp_name(company.getComp_name());
        existingCompany.setComp_location(company.getComp_location());
        compService.addOrSaveCompany(existingCompany);
        return ResponseEntity.ok("Company details updated successfully");
    }


    @DeleteMapping("/deleteCompany/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long id) throws EntityNotFoundException {
        Company existingCompany =compService.getCompanyById(id);
        compService.deleteCompany(id);
        return ResponseEntity.ok("Company deleted successfully");
    }
}