package com.enima.enigmaboot.repos;

import com.enima.enigmaboot.entities.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepos extends JpaRepository<Customers, String> {
    public List<Customers> findCustomerByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
    Page<Customers> findAll(Specification<Customers> customersSpecification, Pageable pageable);
}
