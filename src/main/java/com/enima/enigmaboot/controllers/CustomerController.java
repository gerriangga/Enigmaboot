package com.enima.enigmaboot.controllers;

import com.enima.enigmaboot.constant.ApiUrlConstant;
import com.enima.enigmaboot.constant.ResponseMessage;
import com.enima.enigmaboot.dto.CustomerSearchDTO;
import com.enima.enigmaboot.entities.Customers;
import com.enima.enigmaboot.services.CustomerService;
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

@RestController
@RequestMapping(ApiUrlConstant.CUSTOMER)
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<ResponseData<Customers>> saveCustomer(@RequestBody Customers customers, Errors errors){
        ResponseData<Customers> responseData = new ResponseData();

        if(errors.hasErrors()){
            responseData.getMessage();
            responseData.setPayLoad(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }

        responseData.setMessage(String.format(ResponseMessage.DATA_STORED, "Customer"));
        responseData.setPayLoad(customerService.saveCustomer(customers));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(responseData);
    }

    @GetMapping("/{customerId}")
    public Customers getCustomersById(@PathVariable("customerId") String customerId){
        return customerService.getCustomerById(customerId);
    }

//    @GetMapping
//    public List<Customers> getAllCustomers(){
//        return customerService.getAllCustomers();
//    }

//    @GetMapping
//    public List<Customers> getAllCustomers(@RequestParam(name = "firstName", defaultValue = "")String firstName,
//                                           @RequestParam(name = "lastName", defaultValue = "")String lastName){
//        return customerService.getCustomerByName(firstName, lastName);
//    }

    @PutMapping
    public void updateCustomer(@RequestBody Customers customers){
        customerService.saveCustomer(customers);
    }

    @DeleteMapping("/{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") String customerId){
        customerService.deleteCustomer(customerId);
    }

    @GetMapping
    public PageResponseWrapper<Customers> searchCustomerPerPage(@RequestBody CustomerSearchDTO customerSearchDTO,
                                                 @RequestParam(name="page", defaultValue = "0") Integer page,
                                                 @RequestParam(name="size", defaultValue = "3") Integer sizePerPage,
                                                 @RequestParam(name="sortBy", defaultValue = "firstName") String sortBy,
                                                 @RequestParam(name="direction", defaultValue = "ASC") String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        Page<Customers> customersPage = customerService.getCustomerPerPage(pageable, customerSearchDTO);
        return new PageResponseWrapper<Customers>(customersPage);
    }
}
