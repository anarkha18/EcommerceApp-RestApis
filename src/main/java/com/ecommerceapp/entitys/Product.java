package com.ecommerceapp.entitys;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long p_id;
    private String p_name;
    private Long p_price;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_brand", referencedColumnName = "b_id")
    private Brand p_brand;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_category", referencedColumnName = "cat_id")
    private Category p_category;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "p_company", referencedColumnName = "comp_id")
    private Company p_company;

}
