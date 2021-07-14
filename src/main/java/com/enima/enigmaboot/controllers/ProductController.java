package com.enima.enigmaboot.controllers;

import com.enima.enigmaboot.constant.ApiUrlConstant;
import com.enima.enigmaboot.constant.ResponseMessage;
import com.enima.enigmaboot.dto.ProductSearchDTO;
import com.enima.enigmaboot.entities.Products;
import com.enima.enigmaboot.services.ProductService;
import com.enima.enigmaboot.utils.PageResponseWrapper;
import com.enima.enigmaboot.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiUrlConstant.PRODUCT)
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity<ResponseData<Products>> saveProducts(@RequestBody Products products, Errors errors){
        ResponseData<Products> responseData = new ResponseData();

        if(errors.hasErrors()){
            responseData.getMessage();
            responseData.setPayLoad(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setMessage(String.format(ResponseMessage.DATA_STORED, "Product"));
        responseData.setPayLoad(productService.saveProduct(products));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(responseData);
    }

//    @GetMapping("/api/products")
//    public List<Products> getAllProducts(){
//        return productService.getAllProducts();
//    }

    @GetMapping("/{productId}")
    public Products getProductById(@PathVariable("productId") String Id){
        return productService.getProductById(Id);
    }

    @DeleteMapping("/{productId}")
    public void deleteProductById(@PathVariable("productId") String Id){
        productService.deleteProduct(Id);
    }

    @GetMapping
    public PageResponseWrapper<Products> searchProductPerPage(@RequestParam(name="name", defaultValue = "") String name,
                                                              @RequestParam(name="price", required = false) Integer productPrice,
                                                              @RequestParam(name="stock", required = false) Integer stock,
                                                              @RequestParam(name="page", defaultValue = "0") Integer page,
                                                              @RequestParam(name="size", defaultValue = "3") Integer sizePerPage,
                                                              @RequestParam(name="sortBy", defaultValue = "name") String sortBy,
                                                              @RequestParam(name="direction", defaultValue = "ASC") String direction){
        ProductSearchDTO productSearchDTO = new ProductSearchDTO(name, productPrice, stock);
        productSearchDTO.setSearchProductName(name);
        productSearchDTO.setSearchProductPrice(productPrice);
        productSearchDTO.setSearchProductStock(stock);
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        Page<Products> productsPage = productService.getProductPerPage(pageable, productSearchDTO);
        return new PageResponseWrapper<Products>(productsPage);
    }
}
