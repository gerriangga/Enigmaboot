package com.enima.enigmaboot.repos;

import com.enima.enigmaboot.entities.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepos extends JpaRepository<Products, String> {
    Page<Products> findAll(Specification<Products> productsSpecification, Pageable pageable);
}
