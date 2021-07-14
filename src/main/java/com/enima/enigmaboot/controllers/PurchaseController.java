package com.enima.enigmaboot.controllers;

import com.enima.enigmaboot.constant.ResponseMessage;
import com.enima.enigmaboot.entities.Purchases;
import com.enima.enigmaboot.services.PurchaseService;
import com.enima.enigmaboot.utils.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchases")
public class PurchaseController {
    @Autowired
    PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<ResponseData<Purchases>> savePurchase(@RequestBody Purchases purchases){
        ResponseData responseData = new ResponseData();
        responseData.setMessage(String.format(ResponseMessage.DATA_STORED, "Purchase"));
        responseData.setPayLoad(purchaseService.transaction(purchases));
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(responseData);
    }

    @GetMapping("/{purchaseId}")
    public Purchases getTransactionById(@PathVariable("purchaseId") String id){
        return purchaseService.getTransactionById(id);
    }

}
