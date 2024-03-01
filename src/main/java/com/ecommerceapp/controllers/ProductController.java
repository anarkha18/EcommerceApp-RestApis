package com.ecommerceapp.controllers;


import com.ecommerceapp.DTOs.ProductDTO;
import com.ecommerceapp.entitys.Product;
import com.ecommerceapp.exceptionHandlers.EntityNotFoundException;
import com.ecommerceapp.services.ProductService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductDTO productDTO) throws EntityNotFoundException {
        Product product = productService.toProduct(productDTO);
        logger.info(product.toString());
        productService.addOrSaveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }


    @GetMapping("/getAllProducts")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> allProducts = productService.getAllProducts();
        logger.info(allProducts.toString());
        return ResponseEntity.ok(allProducts);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws EntityNotFoundException {
        Product myProduct = productService.getProductById(id);
        logger.info(myProduct.toString());
        return ResponseEntity.status(HttpStatus.OK).body(myProduct);
    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody @Valid ProductDTO productDTO) throws EntityNotFoundException {
        Product exProduct = productService.getProductById(id);
        Product updatedProduct = productService.updateProduct(exProduct, productDTO);
        productService.addOrSaveProduct(updatedProduct);
        return ResponseEntity.ok("Product details updated successfully");
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) throws EntityNotFoundException {
        productService.deleteProduct(id);
        logger.info("deleted");
        return ResponseEntity.ok("Product deleted successfully");
    }
}
