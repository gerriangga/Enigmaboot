package com.enima.enigmaboot.services;

import com.enima.enigmaboot.dto.CustomerSearchDTO;
import com.enima.enigmaboot.entities.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {
    public Customers saveCustomer(Customers customers);
    public Customers getCustomerById(String id);
    public List<Customers> getAllCustomers();
    public void deleteCustomer(String id);
    public Page<Customers> getCustomerPerPage(Pageable pageable, CustomerSearchDTO customerSearchDTO);
    public List<Customers> getCustomerByName(String firstName, String lastName);
    public List<Customers> getActiveCustomer();
    public List<Customers> getNonActiveCustomer(String firstName, String lastName);
    public void updateStatusCustomer(String id);
}
