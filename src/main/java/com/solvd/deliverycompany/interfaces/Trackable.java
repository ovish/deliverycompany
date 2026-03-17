package com.solvd.deliverycompany.interfaces;

import com.solvd.deliverycompany.entities.OrderStatus;

public interface Trackable {

    void track(String trackingNumber);
    void updateStatus(OrderStatus newStatus);
}
