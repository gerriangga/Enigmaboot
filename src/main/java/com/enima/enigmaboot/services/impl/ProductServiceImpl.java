package com.enima.enigmaboot.services.impl;

import com.enima.enigmaboot.dto.ProductSearchDTO;
import com.enima.enigmaboot.entities.Products;
import com.enima.enigmaboot.repos.ProductRepos;
import com.enima.enigmaboot.services.CustomerService;
import com.enima.enigmaboot.services.ProductService;
import com.enima.enigmaboot.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepos productRepos;

    @Override
    public Products saveProduct(Products products) {
        return productRepos.save(products);
    }

    @Override
    public Products getProductById(String id) {
        return productRepos.findById(id).get();
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepos.findAll();
    }

    @Override
    public void deleteProduct(String id) {
        productRepos.deleteById(id);
    }

    @Override
    public Page<Products> getProductPerPage(Pageable pageable, ProductSearchDTO productSearchDTO) {
        Specification<Products> productsSpecification = ProductSpecification.getSpecification(productSearchDTO);
        return productRepos.findAll(productsSpecification, pageable);
    }

    @Override
    public List<Products> getProductByName(String name) {
        return null;
    }
}
