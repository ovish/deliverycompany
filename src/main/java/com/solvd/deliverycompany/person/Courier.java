package com.solvd.deliverycompany.person;

import com.solvd.deliverycompany.entities.Address;
import com.solvd.deliverycompany.entities.Order;
import com.solvd.deliverycompany.entities.OrderStatus;
import com.solvd.deliverycompany.entities.Vehicle;
import com.solvd.deliverycompany.exceptions.DeliveryException;
import com.solvd.deliverycompany.interfaces.Deliverable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Courier extends Employee implements Deliverable {

    private Vehicle vehicle;
    private static final Logger logger = LogManager.getLogger(Courier.class);


    public Courier() {};

    public Courier(String firstName, String lastName, String phone, Long employeeId, String position, Vehicle vehicle) {
        super(firstName, lastName, phone, employeeId, position);
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void printRole() {
        logger.info("Courier: " + super.getFirstName() + " " + super.getLastName());
    }

    @Override
    public void deliverOrder(Order order, Address address) throws DeliveryException {
        address.validate();
        order.setOrderStatus(OrderStatus.IN_TRANSIT);
        logger.info(String.valueOf(order.getOrderStatus()));
    }

    @Override
    public void deliverOrder(Order order, Receiver receiver) throws DeliveryException {
        receiver.getAddress().validate();
        order.setOrderStatus(OrderStatus.DELIVERED);
        logger.info("Order delivered to receiver " + receiver.getFirstName() + " " + receiver.getLastName());
    }
}
