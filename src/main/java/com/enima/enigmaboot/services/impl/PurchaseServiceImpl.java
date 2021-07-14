package com.enima.enigmaboot.services.impl;

import com.enima.enigmaboot.constant.ResponseMessage;
import com.enima.enigmaboot.entities.Products;
import com.enima.enigmaboot.entities.PurchaseDetail;
import com.enima.enigmaboot.entities.Purchases;
import com.enima.enigmaboot.exception.DataNotFoundException;
import com.enima.enigmaboot.repos.PurchaseRepos;
import com.enima.enigmaboot.services.ProductService;
import com.enima.enigmaboot.services.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    PurchaseRepos purchaseRepos;

    @Autowired
    PurchaseDetailServiceImpl purchaseDetailService;

    @Autowired
    ProductService productService;

    @Override
    public Purchases transaction(Purchases purchase) {
        Purchases purchases = purchaseRepos.save(purchase);

        for (PurchaseDetail purchaseDetail : purchase.getPurchaseDetails()) {
            purchaseDetail.setPurchases(purchases);
            Products products = productService.getProductById(purchaseDetail.getProducts().getId());

            purchaseDetail.setPriceSell(Double.valueOf(products.getProductPrice()));
            Integer productStock = products.getStock() - purchaseDetail.getQuantity();
            products.setStock(productStock);
            purchaseDetailService.savePurchaseDetail(purchaseDetail);
        }
        return purchases;
    }

    @Override
    public Purchases getTransactionById(String id) {
        if(!(purchaseRepos.existsById(id))){
            String message = String.format(ResponseMessage.NOT_FOUND_MESSAGE, "purchase", id);
            throw new DataNotFoundException(message);
        }
        return purchaseRepos.findById(id).get();
    }
}
