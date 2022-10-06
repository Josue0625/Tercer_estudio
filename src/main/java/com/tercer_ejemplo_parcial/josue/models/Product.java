package com.tercer_ejemplo_parcial.josue.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String nameProduct;
    @Column(length = 500)
    private String description;
    @Column(length = 50, nullable = false)
    private double value;
}
