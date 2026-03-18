package com.solvd.deliverycompany.storage;

import com.solvd.deliverycompany.exceptions.OrderNotFoundException;
import com.solvd.deliverycompany.entities.Address;
import com.solvd.deliverycompany.entities.Order;
import com.solvd.deliverycompany.entities.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SortingCenter extends Storage {

    private Queue<Order> orderQueue;
    private List<Order> ordersInProcessing;
    private static final Logger logger = LogManager.getLogger(SortingCenter.class);

    @Override
    public String getStorageType() {
        return "Sorting Center";
    }

    public void receiveOrder(Order order) {
        orderQueue.add(order);
        order.setOrderStatus(OrderStatus.AT_SORTING_CENTER);
        logger.info(order.getOrderStatus() + ": Order arrived at sorting center");
    }

    @Override
    public void processOrder(Order order) {
        order = orderQueue.poll();
        ordersInProcessing.add(order);
        addOrder(order);
        logger.info("Order " + order.getOrderID() + "is being sorted at " + getName());
    }

    @Override
    public void releaseOrder(Order order) {
        if (!hasOrder(order.getOrderID())) {
            try {
                throw new OrderNotFoundException("Order not found");
            } catch (OrderNotFoundException e) {
                e.printStackTrace();
            }
        }
        ordersInProcessing.remove(order);
        removeOrder(order.getOrderID());
        order.setOrderStatus(OrderStatus.OUT_FOR_DELIVERY);
        logger.info(order.getOrderStatus() + ": Order left sorting center");
    }

    public SortingCenter() {}

    public SortingCenter(String name, Address address) {
       super(name, address);
       this.ordersInProcessing = new ArrayList<>();
       this.orderQueue = new LinkedList<>();
    }

    public List<Order> getOrdersInProcessing() {
        return ordersInProcessing;
    }

    public void setOrdersInProcessing(List<Order> ordersInProcessing) {
        this.ordersInProcessing = ordersInProcessing;
    }

    public Queue<Order> getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(Queue<Order> orderQueue) {
        this.orderQueue = orderQueue;
    }
}
