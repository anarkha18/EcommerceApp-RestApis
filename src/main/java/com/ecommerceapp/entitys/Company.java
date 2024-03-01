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
@Table(name = "company_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comp_id;
    @NotNull(message = "Company cannot be null")
    @Size(min = 3, message = "Company name should have at least 3 characters")
    private String comp_name;

    @NotNull(message = "Company location cannot be null")
    @Size(min = 3, message = "Company location should have at least 3 characters")
    private String comp_location;

    @OneToMany(mappedBy = "p_company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonBackReference
    private List<Product> products;

    @Override
    public String toString() {
        return "Company{" +
                "comp_id=" + comp_id +
                ", comp_name='" + comp_name + '\'' +
                ", comp_location='" + comp_location + '\'' +
                '}';
    }
}
