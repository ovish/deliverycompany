package com.solvd.deliverycompany.person;

import com.solvd.deliverycompany.entities.Address;
import com.solvd.deliverycompany.entities.Order;
import com.solvd.deliverycompany.exceptions.InsufficientFundsException;
import com.solvd.deliverycompany.interfaces.Payable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Objects;

public class Sender extends DeliveryParty implements Payable {

    private String email;
    private Double accountBalance;
    private static final Logger logger = LogManager.getLogger(Sender.class.getName());


    @Override
    public String getRole() {
        return "SENDER";
    }

    @Override
    public void printRole() {
        logger.info("Sender: " + super.getFirstName() + " " + super.getLastName());
    }

    @Override
    public void processOrder(Order order) {
        addOrder(order);
        logger.info("Sender processed order " + order.getOrderID());
    }

    public void addFunds(Double amount) {
        accountBalance += amount;
        logger.info("Added $" + amount + ". New Balance: $" + accountBalance);
    }

    public boolean withdrawFunds(Double amount) {
        if (amount > 0 && accountBalance >= amount) {
            accountBalance -= amount;
            logger.info("Withdrawn $" + amount + ". Remaining: $" + accountBalance);
            return true;
        } else {
            logger.warn("Insufficient funds or invalid amount");
            return false;
        }
    }

    @Override
    public boolean pay(Double amount) {
        try {
            if (accountBalance < amount) {
                throw new InsufficientFundsException("Not enough money!");
            }
            withdrawFunds(amount);
            logger.info("Payment $" + amount + " completed");
            return true;

        } catch (InsufficientFundsException e) {
            logger.error("Payment failed");
            return false;
        }
    }

    public Sender() {}

    public Sender(String firstName, String lastName, String phone, String email) {
        super(firstName, lastName, phone);
        this.email = email;
        this.accountBalance = 0.0;
    }

    public Sender(String firstName, String lastName, String phone, Address address, String email) {
        super(firstName, lastName, phone, address);
        this.email = email;
        this.accountBalance = 0.0;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return "model.Sender{" +
                "name='" + getFirstName() + " " + getLastName() + '\'' +
                "email='" + email + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Sender sender = (Sender) o;
        return Objects.equals(email, sender.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

}



