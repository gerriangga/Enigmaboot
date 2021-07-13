package com.enima.enigmaboot.controllers;

import com.enima.enigmaboot.dto.ProductSearchDTO;
import com.enima.enigmaboot.entities.Products;
import com.enima.enigmaboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/api/products")
    public void saveProducts(@RequestBody Products products){
        productService.saveProduct(products);
    }

//    @GetMapping("/api/products")
//    public List<Products> getAllProducts(){
//        return productService.getAllProducts();
//    }

    @GetMapping("/api/products/{productId}")
    public Products getProductById(@PathVariable("productId") String Id){
        return productService.getProductById(Id);
    }

    @DeleteMapping("/api/products/{productId}")
    public void deleteProductById(@PathVariable("productId") String Id){
        productService.deleteProduct(Id);
    }

    @GetMapping("/api/products")
    public Page<Products> searchProductPerPage( @RequestParam(name="page", defaultValue = "0") Integer page,
                                                @RequestParam(name="size", defaultValue = "3") Integer sizePerPage,
                                                @RequestParam(name="sortBy", defaultValue = "name") String sortBy,
                                                @RequestParam(name="searchProductName", defaultValue = "") ProductSearchDTO productSearchDTO,
                                                @RequestParam(name="direction", defaultValue = "ASC") String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        return productService.getProductPerPage(pageable, productSearchDTO);
    }
}
