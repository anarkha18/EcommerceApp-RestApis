package com.ecommerceapp.services;


import com.ecommerceapp.DTOs.ProductDTO;
import com.ecommerceapp.entitys.Product;
import com.ecommerceapp.exceptionHandlers.EntityNotFoundException;
import com.ecommerceapp.repos.ProductRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductService {
    public static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CompService compService;

    public Product addOrSaveProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) throws EntityNotFoundException {
        Optional<Product> myProduct = productRepo.findById(id);
        if(myProduct.isPresent()){
            return myProduct.get();
        }else{
            throw new EntityNotFoundException("Product not found");
        }
    }

    public Product setProductFields(Product product, ProductDTO productDTO) throws EntityNotFoundException {
        product.setP_name(productDTO.getP_name());
        product.setP_price(productDTO.getP_price());
        product.setP_category(categoryService.getCategoryById(productDTO.getP_category()));
        product.setP_brand(brandService.getBrandById(productDTO.getP_brand()));
        product.setP_company(compService.getCompanyById(productDTO.getP_company()));
        return product;
    }
    public Product toProduct(ProductDTO productDTO) throws EntityNotFoundException {
        Product newProduct = new Product();
        return setProductFields(newProduct, productDTO);
    }

    public Product updateProduct(Product existingProduct, ProductDTO productDTO) throws EntityNotFoundException {
        return setProductFields(existingProduct, productDTO);
    }


    public void deleteProduct(Long productId) throws EntityNotFoundException {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product with ID " + productId + " not found!"));
        productRepo.deleteProductById(product.getP_id());
    }

}
