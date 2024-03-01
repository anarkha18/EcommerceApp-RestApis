package com.ecommerceapp.entitys;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "category_table")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cat_id;
    @NotNull(message = "Category cannot be null")
    @Size(min = 3, message = "Category name should have at least 3 characters")
    private String cat_name;
    @OneToMany(mappedBy = "p_category", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Product> products;

    @Override
    public String toString() {
        return "Category{" +
                "cat_id=" + cat_id +
                ", cat_name='" + cat_name + '\'' +
                '}';
    }

}
