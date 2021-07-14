package com.enima.enigmaboot.repos;

import com.enima.enigmaboot.entities.Purchases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepos extends JpaRepository<Purchases, String> {

}
