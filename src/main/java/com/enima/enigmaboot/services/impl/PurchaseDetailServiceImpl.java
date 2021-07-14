package com.enima.enigmaboot.services.impl;

import com.enima.enigmaboot.entities.PurchaseDetail;
import com.enima.enigmaboot.repos.PurchaseDetailRepos;
import com.enima.enigmaboot.services.PurchaseDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseDetailServiceImpl implements PurchaseDetailService {
    @Autowired
    PurchaseDetailRepos purchaseDetailRepos;

    @Override
    public PurchaseDetail savePurchaseDetail(PurchaseDetail purchaseDetail) {


        return purchaseDetailRepos.save(purchaseDetail);
    }
}
