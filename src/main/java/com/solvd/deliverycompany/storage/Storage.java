package com.solvd.deliverycompany.storage;

import com.solvd.deliverycompany.exceptions.OrderNotFoundException;
import com.solvd.deliverycompany.entities.Address;
import com.solvd.deliverycompany.entities.Order;

import java.util.HashMap;
import java.util.Map;

public abstract class Storage {

    private String name;
    private Address address;
    private Map<Long, Order> currentOrders;


    public abstract String getStorageType();
    public abstract void receiveOrder(Order order);
    public abstract void processOrder(Order order);
    public abstract void releaseOrder(Order order);

    public boolean hasOrder(Long OrderID) {
        return currentOrders.containsKey(OrderID);
    }

    public void addOrder(Order order) {
        currentOrders.put(order.getOrderID(), order);
    }


    public Order removeOrder(Long orderID) {
        Order removed = currentOrders.remove(orderID);
        if (removed == null) {
            try {
                throw new OrderNotFoundException("Order not found");
            } catch (OrderNotFoundException e) {
                e.printStackTrace();
            }
        }
        return removed;
    }

    public Storage() {
    }

    public Storage(String name, Address address) {
        this.name = name;
        this.address = address;
        this.currentOrders = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Map<Long, Order> getCurrentOrders() {
        return currentOrders;
    }

    public void setCurrentOrders(Map<Long, Order> currentOrders) {
        this.currentOrders = currentOrders;
    }
}
