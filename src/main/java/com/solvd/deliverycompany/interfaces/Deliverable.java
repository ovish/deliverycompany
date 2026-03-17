package com.solvd.deliverycompany.interfaces;

import com.solvd.deliverycompany.entities.Address;
import com.solvd.deliverycompany.entities.Order;
import com.solvd.deliverycompany.exceptions.DeliveryException;
import com.solvd.deliverycompany.person.Receiver;

public interface Deliverable {

    void deliverOrder(Order order, Address address) throws DeliveryException;
    void deliverOrder(Order order, Receiver receiver) throws DeliveryException;

    }
