package com.ecommerceapp.DTOs;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private Long p_id;
    @NotNull(message = "Product name cannot be null")
    @Size(min = 3, message = "Product name should have at least 3 characters")
    private String p_name;
    @NotNull(message = "Product price cannot be null")
    private Long p_price;
    @NotNull(message = "Brand cannot be null")
    private Long p_brand;
    @NotNull(message = "Category cannot be null")
    private Long p_category;
    @NotNull(message = "Company cannot be null")
    private Long p_company;

}
