package com.enima.enigmaboot.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "trx_purchase_detail")
public class PurchaseDetail {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    private Double priceSell;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "purchase_id")
    private Purchases purchases;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Products products;

    public PurchaseDetail() {
    }

    public PurchaseDetail(String id, Double priceSell, Integer quantity, Purchases purchases, Products products) {
        this.id = id;
        this.priceSell = priceSell;
        this.quantity = quantity;
        this.purchases = purchases;
        this.products = products;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(Double priceSell) {
        this.priceSell = priceSell;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Purchases getPurchases() {
        return purchases;
    }

    public void setPurchases(Purchases purchases) {
        this.purchases = purchases;
    }

    public Products getProducts() {
        return products;
    }

    public void setProducts(Products products) {
        this.products = products;
    }
}
