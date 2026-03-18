package com.solvd.deliverycompany.storage;

import com.solvd.deliverycompany.entities.Order;
import com.solvd.deliverycompany.entities.OrderStatus;
import com.solvd.deliverycompany.entities.Product;
import com.solvd.deliverycompany.exceptions.OrderNotFoundException;
import com.solvd.deliverycompany.exceptions.ProductNotFoundException;
import com.solvd.deliverycompany.entities.Address;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Warehouse extends Storage {

    private List<Order> storedOrders;
    private Map<Long, Product> products;
    private static final Logger logger = LogManager.getLogger(Warehouse.class);

   @Override
   public String getStorageType() {
       return "Warehouse";
   }

   public void addProduct(Product product) {
       products.put(product.getProductID(), product);
   }

   public Product getProductByID(Long productID) throws ProductNotFoundException {
       Product product = products.get(productID);
       if (product == null) {
           throw new ProductNotFoundException("Product ID " + productID + " not found in warehouse");
       }
       return product;
   }

   @Override
   public void receiveOrder(Order order) {
       Long productID = order.getProductID();
       if (!products.containsKey(productID)) {
           logger.warn("Product for order " + order.getOrderID() + " not found in warehouse");
           return;
       }
       storedOrders.add(order);
       addOrder(order);
       logger.info("Order " + order.getOrderID() + " created and received at warehouse " + super.getName());
   }

   @Override
   public void processOrder(Order order) {
       order.setOrderStatus(OrderStatus.IN_PROGRESS);
       logger.info("Warehouse preparing order " + order.getOrderID());
   }

   public void releaseOrder(Order order) {
       if (!hasOrder(order.getOrderID())) {
           try {
               throw new OrderNotFoundException("Order not found in warehouse");
           } catch (OrderNotFoundException e) {
               e.printStackTrace();
           }
       }
       storedOrders.remove(order);
       Long productID = order.getProductID();
       Product product = products.remove(productID);
       order.setOrderStatus(OrderStatus.RELEASED_FROM_WAREHOUSE);
       logger.info("Order " + order.getOrderID() + " released from warehouse " + super.getName());
   }

   public Order findOrder(Long orderID) {
       return storedOrders.stream()
               .filter(order -> order.getOrderID().equals(orderID))
               .findFirst()
               .orElse(null);
   }

   public List<Long> getAvailableProductsID() {
       return products.keySet().stream()
               .sorted()
               .collect(Collectors.toList());
   }

   public long countFragileProducts() {
       return products.values().stream()
               .filter(Product::isFragile)
               .count();
   }

   public Warehouse() {}

    public Warehouse(String name, Address address) {
        super(name, address);
        this.storedOrders = new ArrayList<>();
        this.products = new HashMap<>();
    }


    public List<Order> getStoredOrders() {
        return storedOrders;
    }

    public void setStoredOrders(List<Order> storedOrders) {
        this.storedOrders = storedOrders;
    }

    public Map<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Product> products) {
        this.products = products;
    }
}
