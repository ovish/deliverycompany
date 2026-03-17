package com.solvd.deliverycompany.entities;

public final class Product {

    private Long productID;
    private Double weight;
    private String size;
    private Boolean fragile;

    public Product() {}

    public Product(Long productID, Double weight, String size, Boolean fragile) {
        this.productID = productID;
        this.weight = weight;
        this.size = size;
        this.fragile = fragile;
    }

    public Product(Double weight, String size) {
        this.weight = weight;
        this.size = size;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Boolean isFragile() {
        return fragile;
    }

    public void setFragile(Boolean fragile) {
        this.fragile = fragile;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Boolean getFragile() {
        return fragile;
    }
}
