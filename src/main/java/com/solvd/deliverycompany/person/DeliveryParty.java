package com.solvd.deliverycompany.person;

import com.solvd.deliverycompany.entities.Address;
import com.solvd.deliverycompany.entities.Order;
import com.solvd.deliverycompany.entities.OrderStatus;

import java.util.HashSet;
import java.util.Set;

public abstract class DeliveryParty extends Person {

    private Address address;
    private Set<Order> orderHistory = new HashSet<>();

    public abstract String getRole();
    public abstract void processOrder(Order order);

    public void addOrder(Order order) {
        orderHistory.add(order);
    }

    public long countDeliveredOrders() {
        return orderHistory.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.DELIVERED)
                .count();
    }

    public DeliveryParty() {
        super();
        this.orderHistory = new HashSet<>();
    }

    public DeliveryParty(String firstName, String lastName, String phone) {
        super(firstName, lastName, phone);
        this.orderHistory = new HashSet<>();
    }

    public DeliveryParty(String firstName, String lastName, String phone, Address address) {
        super(firstName, lastName, phone);
        this.address = address;
        this.orderHistory = new HashSet<>();
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Order> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(Set<Order> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
