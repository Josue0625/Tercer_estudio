package com.tercer_ejemplo_parcial.josue.repository;

import com.tercer_ejemplo_parcial.josue.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameProduct(String name_product);
}
