package com.ecommerceapp.repos;

import com.ecommerceapp.entitys.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p WHERE p.p_id = :productId")
    void deleteProductById(Long productId);

}
