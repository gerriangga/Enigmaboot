package com.enima.enigmaboot.repos;

import com.enima.enigmaboot.entities.Customers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface CustomerRepos extends JpaRepository<Customers, String> {
    List<Customers> findCustomerByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(String firstName, String lastName);
    Page<Customers> findAll(Specification<Customers> customersSpecification, Pageable pageable);

    @Query(value = "SELECT * FROM mst_customer c WHERE c.status = 1", nativeQuery = true)
    List<Customers> findActiveCustomers();

    @Query("SELECT c FROM Customers c WHERE c.status = 0 AND c.firstName = ?1 AND c.lastName = ?2")
    List<Customers> nonActiveCustomer(String firstName, String lastName);

    @Modifying
    @Query("UPDATE Customers c SET c.status= 1 WHERE c.id = :id")
    void updateCustomerStatus(@Param("id") String id);
}
