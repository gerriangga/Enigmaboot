package com.enima.enigmaboot.services;

import com.enima.enigmaboot.dto.ProductSearchDTO;
import com.enima.enigmaboot.entities.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    public Products saveProduct(Products products);
    public Products getProductById(String id);
    public List<Products> getAllProducts();
    public void deleteProduct(String id);
    public Page<Products> getProductPerPage(Pageable pageable, ProductSearchDTO productSearchDTO);
    public List<Products> getProductByName(String name);
}
