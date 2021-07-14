package com.enima.enigmaboot.repos;

import com.enima.enigmaboot.entities.PurchaseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseDetailRepos extends JpaRepository<PurchaseDetail, String> {
}
