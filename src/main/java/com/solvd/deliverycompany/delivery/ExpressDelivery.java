package com.solvd.deliverycompany.delivery;

public class ExpressDelivery extends DeliveryType {

    public ExpressDelivery() {
        super("Express", 1, 15.0);
    }

    @Override
    public double calculateCost(Double basePrice, Double weight, Boolean isFragile) {
        Double cost = basePrice + getExtraCost() + (weight * 4.0);

        if (isFragile) {
            cost += 20.0;
        }
        return cost;
    }

    @Override
    public String getDescription() {
        return "Express delivery within 1 business day";
    }
}
