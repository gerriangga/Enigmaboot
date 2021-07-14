package com.enima.enigmaboot.services;

import com.enima.enigmaboot.entities.PurchaseDetail;
import com.enima.enigmaboot.entities.Purchases;

public interface PurchaseService{
    public Purchases transaction(Purchases purchase);
    public Purchases getTransactionById(String id);
}
