package com.solvd.deliverycompany.delivery;

public abstract class DeliveryType {

    private String typeName;
    private final Integer DeliveryDays;
    private Double extraCost;

    public abstract String getDescription();

    public abstract double calculateCost (Double basePrice, Double weight, Boolean isFragile);

    public DeliveryType(Integer deliveryDays) {
        DeliveryDays = deliveryDays;
    };

    public DeliveryType(String typeName, Integer deliveryDays, Double extraCost) {
        this.typeName = typeName;
        DeliveryDays = deliveryDays;
        this.extraCost = extraCost;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getDeliveryDays() {
        return DeliveryDays;
    }

    public Double getExtraCost() {
        return extraCost;
    }

    public void setExtraCost(Double extraCost) {
        this.extraCost = extraCost;
    }

}
