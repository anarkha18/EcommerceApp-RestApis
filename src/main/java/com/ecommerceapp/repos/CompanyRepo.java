package com.ecommerceapp.repos;

import com.ecommerceapp.entitys.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepo extends JpaRepository<Company, Long> {
}
