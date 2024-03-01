package com.ecommerceapp.services;

import com.ecommerceapp.entitys.Brand;
import com.ecommerceapp.entitys.Company;
import com.ecommerceapp.exceptionHandlers.EntityNotFoundException;
import com.ecommerceapp.repos.BrandRepo;
import com.ecommerceapp.repos.CompanyRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompService {

    public static final Logger logger = LoggerFactory.getLogger(CompService.class);

    @Autowired
    private CompanyRepo companyRepo;

    public Company addOrSaveCompany(Company company){
        return companyRepo.save(company);
    }
    public Company getCompanyById(Long id) throws EntityNotFoundException {
        Optional<Company> optionalComp = companyRepo.findById(id);
        if (optionalComp.isPresent()) {
            Company myComp = optionalComp.get();
            return myComp;
        } else {
            throw new EntityNotFoundException("Company not found!!!");
        }
    }

    public List<Company> getAllCompanies(){
        return companyRepo.findAll();
    }

    public void deleteCompany(Long id) {
        companyRepo.deleteById(id);
    }
}
