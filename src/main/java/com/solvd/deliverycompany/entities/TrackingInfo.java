package com.solvd.deliverycompany.entities;

import java.time.LocalDate;

public record TrackingInfo (String trackingNumber, LocalDate estimatedDeliveryDate) {


    public TrackingInfo(String trackingNumber, LocalDate estimatedDeliveryDate) {
        this.trackingNumber =  trackingNumber;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }


}

