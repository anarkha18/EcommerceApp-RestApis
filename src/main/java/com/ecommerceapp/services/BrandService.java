package com.ecommerceapp.services;

import com.ecommerceapp.entitys.Brand;
import com.ecommerceapp.exceptionHandlers.EntityNotFoundException;
import com.ecommerceapp.repos.BrandRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    public static final Logger logger = LoggerFactory.getLogger(BrandService.class);

    @Autowired
    private BrandRepo brandRepo;

    public Brand addOrSaveBrand(Brand brand){
        return brandRepo.save(brand);
    }
    public Brand getBrandById(Long id) throws EntityNotFoundException {
        Optional<Brand> optionalBrand = brandRepo.findById(id);
        if (optionalBrand.isPresent()) {
            Brand myBrand = optionalBrand.get();
            return myBrand;
        } else {
            throw new EntityNotFoundException("Brand not found!!!");
        }
    }

    public List<Brand> getAllBrands(){
        return brandRepo.findAll();
    }

    public void deleteBrand(Long id) {
        brandRepo.deleteById(id);
    }
}
