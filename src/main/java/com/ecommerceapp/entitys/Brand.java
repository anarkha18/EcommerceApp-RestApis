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
@Table(name = "brand_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long b_id;

    @NotNull(message = "Brand name cannot be null")
    @Size(min = 3, message = "Brand name should have at least 3 characters")
    private String b_name;

    @OneToMany(mappedBy = "p_brand", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Product> products;

    @Override
    public String toString() {
        return "Brand{" +
                "b_id=" + b_id +
                ", b_name='" + b_name + '\'' +
                '}';
    }
}