package com.solvd.deliverycompany.delivery;

public class StandardDelivery extends DeliveryType {

    public StandardDelivery() {
        super("Standard", 3, 5.0);
    }

    @Override
    public double calculateCost(Double basePrice, Double weight, Boolean isFragile) {
        Double cost = basePrice + getExtraCost() + (weight * 2.0);

        if (isFragile) {
            cost += 10.0;
        }
        return cost;
    }

    @Override
    public String getDescription() {
        return "Standard delivery within 3 business days";
    }
}
