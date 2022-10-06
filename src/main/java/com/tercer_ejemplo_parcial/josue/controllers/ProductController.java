package com.tercer_ejemplo_parcial.josue.controllers;

import com.tercer_ejemplo_parcial.josue.models.Product;
import com.tercer_ejemplo_parcial.josue.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @PostMapping("/product")
    public ResponseEntity createProduct(@RequestBody Product product){
        try{
            productRepository.save(product);
            return new ResponseEntity(product, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping(value = "/product/{id}")
    public ResponseEntity getProduct(@PathVariable Long id){
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return new ResponseEntity(product, HttpStatus.OK);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/products")
    public ResponseEntity listProducts(){
        List<Product> products = productRepository.findAll();
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }else {
            return new ResponseEntity(products, HttpStatus.OK);
        }
    }
    @PutMapping(value = "/updateProduct/{id}")
    public ResponseEntity updateProducts(@PathVariable Long id, @RequestBody Product product){
    Optional <Product> productBD = productRepository.findById(id);
    if(productBD.isPresent()){
        try{
            productBD.get().setNameProduct(product.getNameProduct());
            productBD.get().setDescription(product.getDescription());
            productBD.get().setValue(product.getValue());
            productRepository.save(productBD.get());
            return new ResponseEntity(productBD, HttpStatus.OK);
        }catch(Exception e){
            return ResponseEntity.badRequest().build();
        }
    }else{
        return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping(value = "/deleteProduct/{id}")
    public ResponseEntity deleteProduct(@PathVariable Long id){
        Optional <Product> productBD = productRepository.findById(id);
        if(productBD.isPresent()){
            productRepository.delete(productBD.get());
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/product/name/{name_product}")
    public ResponseEntity listByName(@PathVariable String name_product){
        List<Product> products = productRepository.findAllByNameProduct(name_product);
        if(products.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            return new ResponseEntity(products, HttpStatus.OK);
        }
    }
}
