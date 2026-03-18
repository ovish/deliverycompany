package com.solvd.deliverycompany.person;

import com.solvd.deliverycompany.entities.Address;
import com.solvd.deliverycompany.entities.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Receiver extends DeliveryParty {

    private static final Logger logger = LogManager.getLogger(Receiver.class);


    public Receiver() {}

    public Receiver(String firstName, String lastName, String phone, Address address) {
        super(firstName, lastName, phone, address);
    }

    @Override
    public String getRole() {
        return "RECEIVER";
    }

    @Override
    public void printRole() {
        System.out.println("Receiver: " + super.getFirstName() + " " + super.getLastName());
    }

    @Override
    public void processOrder(Order order) {
        addOrder(order);
        logger.info("Receiver ready for order " + order.getOrderID());
    }

    public void confirmDelivery(Order order) {
        logger.info("Order " + order.getOrderID() + " confirmed by receiver");
    }

    @Override
    public boolean equals(Object o) {
        if (this.hashCode() != o.hashCode()) return false;
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receiver receiver = (Receiver) o;
        return Objects.equals(getAddress(), receiver.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getAddress());
    }

    @Override
    public String toString() {
        return "model.Receiver{" +
                "address=" + getAddress() +
                '}';
    }
}
