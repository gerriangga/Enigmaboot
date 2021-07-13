package com.enima.enigmaboot.controllers;

import com.enima.enigmaboot.dto.CustomerSearchDTO;
import com.enima.enigmaboot.entities.Customers;
import com.enima.enigmaboot.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/api/customers")
    public void saveCustomer(@RequestBody Customers customers){
        customerService.saveCustomer(customers);
    }

    @GetMapping("/api/customers/{customerId}")
    public Customers getCustomersById(@PathVariable("customerId") String customerId){
        return customerService.getCustomerById(customerId);
    }

//    @GetMapping("/api/customers")
//    public List<Customers> getAllCustomers(){
//        return customerService.getAllCustomers();
//    }

//    @GetMapping("/api/customers")
//    public List<Customers> getAllCustomers(@RequestParam(name = "firstName", defaultValue = "")String firstName,
//                                           @RequestParam(name = "lastName", defaultValue = "")String lastName){
//        return customerService.getCustomerByName(firstName, lastName);
//    }

    @PutMapping("/api/customers")
    public void updateCustomer(@RequestBody Customers customers){
        customerService.saveCustomer(customers);
    }

    @DeleteMapping("/api/customers/{customerId}")
    public void deleteCustomerById(@PathVariable("customerId") String customerId){
        customerService.deleteCustomer(customerId);
    }

    @GetMapping("/api/customers")
    public Page<Customers> searchCustomerPerPage(@RequestBody CustomerSearchDTO customerSearchDTO,
                                                 @RequestParam(name="page", defaultValue = "0") Integer page,
                                                 @RequestParam(name="size", defaultValue = "3") Integer sizePerPage,
                                                 @RequestParam(name="sortBy", defaultValue = "firstName") String sortBy,
                                                 @RequestParam(name="direction", defaultValue = "ASC") String direction){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, sizePerPage, sort);
        return customerService.getCustomerPerPage(pageable, customerSearchDTO);
    }
}
