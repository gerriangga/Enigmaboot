package com.enima.enigmaboot.services.impl;

import com.enima.enigmaboot.dto.CustomerSearchDTO;
import com.enima.enigmaboot.entities.Customers;
import com.enima.enigmaboot.repos.CustomerRepos;
import com.enima.enigmaboot.services.CustomerService;
import com.enima.enigmaboot.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepos customerRepos;

    @Override
    public Customers saveCustomer(Customers customers) {
        return customerRepos.save(customers);
    }

    @Override
    public Customers getCustomerById(String id) {
        Optional <Customers> temp = customerRepos.findById(id);
        if(!temp.isPresent())
            return null;
        return customerRepos.findById(id).get();
    }

    @Override
    public List<Customers> getAllCustomers() {
        return customerRepos.findAll();
    }

    @Override
    public void deleteCustomer(String id) {
        customerRepos.deleteById(id);
    }

    @Override
    public Page<Customers> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO) {
        Specification<Customers> customersSpecification = CustomerSpecification.getSpecification(customerSearchDTO);
        return customerRepos.findAll(customersSpecification ,pageable);
    }

    @Override
    public List<Customers> getCustomerByName(String firstName, String lastName) {
        return customerRepos.findCustomerByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName , lastName);
    }

}
