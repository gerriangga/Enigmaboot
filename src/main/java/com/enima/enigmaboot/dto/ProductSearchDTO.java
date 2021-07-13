package com.enima.enigmaboot.dto;

public class ProductSearchDTO {
    private String searchProductName;
    private Integer searchProductPrice;
    private Integer searchProductStock;

    public ProductSearchDTO(String searchProductName, Integer searchProductPrice, Integer searchProductStock) {
        this.searchProductName = searchProductName;
        this.searchProductPrice = searchProductPrice;
        this.searchProductStock = searchProductStock;
    }

    public String getSearchProductName() {
        return searchProductName;
    }

    public void setSearchProductName(String searchProductName) {
        this.searchProductName = searchProductName;
    }

    public Integer getSearchProductPrice() {
        return searchProductPrice;
    }

    public void setSearchProductPrice(Integer searchProductPrice) {
        this.searchProductPrice = searchProductPrice;
    }

    public Integer getSearchProductStock() {
        return searchProductStock;
    }

    public void setSearchProductStock(Integer searchProductStock) {
        this.searchProductStock = searchProductStock;
    }
}
