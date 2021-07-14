package com.enima.enigmaboot.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trx_purchase")
public class Purchases {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "purchase_id")
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date purchaseDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnoreProperties("purchases")
    private Customers customers;

    @OneToMany(mappedBy = "purchases")
    @JsonIgnoreProperties("purchases")
    private List<PurchaseDetail> purchaseDetails = new ArrayList<>();

    public Purchases() {
    }

    public Purchases(String id, Date purchaseDate, Customers customers) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.customers = customers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public List<PurchaseDetail> getPurchaseDetails() {
        return purchaseDetails;
    }

    public void setPurchaseDetails(List<PurchaseDetail> purchaseDetails) {
        this.purchaseDetails = purchaseDetails;
    }
}
