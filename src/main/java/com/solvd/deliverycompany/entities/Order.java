package com.solvd.deliverycompany.entities;

import com.solvd.deliverycompany.delivery.StandardDelivery;
import com.solvd.deliverycompany.exceptions.InvalidOrderStatusException;
import com.solvd.deliverycompany.interfaces.Trackable;
import com.solvd.deliverycompany.person.Receiver;
import com.solvd.deliverycompany.person.Sender;
import com.solvd.deliverycompany.delivery.DeliveryType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Objects;

public class Order implements Trackable {

    private static long nextID = 1;
    private Long orderID;
    private Sender sender;
    private Receiver receiver;
    private Product product;
    private TrackingInfo trackingInfo;
    private OrderStatus orderStatus;
    private DeliveryType deliveryType;
    private static final Logger logger = LogManager.getLogger(Order.class);


    public static Long generateID() {
        return nextID++ ;
    }

    @Override
    public void track(String trackingNumber){
        logger.info("Order " + orderID + " orderStatus: " + orderStatus + ". Estimated Delivery Date: " +trackingInfo.getEstimatedDeliveryDate());
    }

    @Override
    public void updateStatus(OrderStatus newStatus) {
        this.orderStatus = newStatus;
        logger.info("Order " + orderID + " orderStatus: " + newStatus);
    }

    public void cancel() throws InvalidOrderStatusException {
        if (this.orderStatus == OrderStatus.DELIVERED) {
            throw new InvalidOrderStatusException("Cannot cancel order - already delivered");
        }
        if (this.orderStatus == OrderStatus.CANCELED) {
            throw new InvalidOrderStatusException("Order is already canceled");
        }
        this.orderStatus = OrderStatus.CANCELED;
        logger.info("Order " + orderID + " canceled");
    }


    public Order() {
        this.deliveryType = new StandardDelivery();
    }

    public Order(Long orderID, Sender sender, Receiver receiver, Product product) {
        this.orderID = generateID();
        this.sender = sender;
        this.receiver = receiver;
        this.product = product;
        this.orderStatus = OrderStatus.CREATED;
        this.deliveryType = new StandardDelivery() {
        };
    }

    private Order(Long orderID, Sender sender, Receiver receiver) {
        this.orderID = generateID();
        this.sender = sender;
        this.receiver = receiver;
        this.product = product;
        this.orderStatus = OrderStatus.CREATED;
        this.deliveryType = new StandardDelivery() {
        };
    }

    public Long getOrderID() {
        return orderID;
    }

    public void setOrderID(Long orderID) {
        this.orderID = orderID;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public TrackingInfo getTrackingInfo() {
        return trackingInfo;
    }

    public void setTrackingInfo(TrackingInfo trackingInfo) {
        this.trackingInfo = trackingInfo;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public Long getProductID() {
        return product.getProductID();
    }

    @Override
    public String toString() {
        return "com.solvd.entities.Order{" +
                "orderID=" + orderID +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", product=" + product +
                ", trackingInfo=" + trackingInfo +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (this.hashCode() != o.hashCode()) return false;
        Order order = (Order) o;
        return Objects.equals(orderID, order.orderID) && Objects.equals(sender, order.sender) && Objects.equals(receiver, order.receiver) && Objects.equals(product, order.product) && Objects.equals(trackingInfo, order.trackingInfo) && Objects.equals(orderStatus, order.orderStatus);
    }

    @Override
    public int hashCode() {
        return orderID.hashCode();
    }
}
