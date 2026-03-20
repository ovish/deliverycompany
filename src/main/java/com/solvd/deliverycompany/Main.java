package com.solvd.deliverycompany;

import com.solvd.deliverycompany.utilities.ConnectionPool;
import com.solvd.deliverycompany.utilities.DBConnection;
import com.solvd.deliverycompany.delivery.ExpressDelivery;
import com.solvd.deliverycompany.delivery.Notification;
import com.solvd.deliverycompany.entities.*;
import com.solvd.deliverycompany.exceptions.DeliveryException;
import com.solvd.deliverycompany.person.Courier;
import com.solvd.deliverycompany.person.Receiver;
import com.solvd.deliverycompany.person.Sender;
import com.solvd.deliverycompany.storage.SortingCenter;
import com.solvd.deliverycompany.storage.Storage;
import com.solvd.deliverycompany.storage.Warehouse;
import com.solvd.deliverycompany.utilities.MyLinkedList;
import com.solvd.deliverycompany.utilities.WordCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




public class Main {

       private static final Logger logger = LogManager.getLogger(Main.class);



        public static void main(String[] args) {

                Address warehouseAddress = new Address("Radom", "Nowa 1", "23-234");
                Address sortingCenterAddress = new Address("Warsaw", "Konwaliowa 21", "00-001");
                Address receiverAddress = new Address("Krakow", "Kasztanowa 12", "00-002");

                Sender sender = new Sender("Anna", "Smith", "123456789", "anna@gmail.com") {
                };
                sender.setAddress(warehouseAddress);

                Receiver receiver = new Receiver("Marta", "Nowak", "987654321", receiverAddress);

                Product product = new Product(1234L, 2.5, "Medium", true);

                Order order = new Order(123L, sender, receiver, product);

                TrackingInfo trackingInfo = new TrackingInfo("QA2326363PL", LocalDate.of(2026, 2, 1));
                order.setTrackingInfo(trackingInfo);
                order.setDeliveryType(new ExpressDelivery());

                sender.addFunds(150.0);
                Double cost = order.getDeliveryType().calculateCost(11.0, 1.5, product.isFragile());
                sender.pay(cost);
                sender.processOrder(order);

                Storage warehouse = new Warehouse("com.solvd.storage.Warehouse", warehouseAddress);
                warehouse.addOrder(order);
                warehouse.receiveOrder(order);
                warehouse.processOrder(order);
                warehouse.releaseOrder(order);

                Vehicle van = new Vehicle("Van", "WA1234", 600.0);
                Vehicle truck = new Vehicle("Truck", "LN5678", 1500.0);
                Courier courier1 = new Courier("Piotr", "Kowalski", "100093322", 187878L, "model.Courier", van);
                Courier courier2 = new Courier("Michal", "Szulak", "546392929", 321212L, "model.Courier", truck);

                order.updateStatus(OrderStatus.IN_TRANSIT);

                try {
                        courier1.deliverOrder(order, sortingCenterAddress);
                } catch (DeliveryException e) {
                        e.printStackTrace();
                }

                Storage sortingCenter = new SortingCenter("Central Sorting Center", sortingCenterAddress);
                sortingCenter.receiveOrder(order);
                sortingCenter.processOrder(order);
                sortingCenter.releaseOrder(order);

                order.track("QA2326363PL");

                try {
                        courier2.deliverOrder(order, receiver);
                } catch (DeliveryException e) {
                        e.printStackTrace();
                }

                receiver.confirmDelivery(order);
                order.updateStatus(OrderStatus.DELIVERED);

                Notification notification1 = new Notification("SMS", "Your order has been delivered");
                Notification notification2 = new Notification("Email", "Your order has been delivered");

                notification1.sendSMS(receiver);
                notification2.sendEmail(sender);

                logger.info("Status: " + order.getOrderStatus());
                logger.info("Delivery Date: " + trackingInfo.getEstimatedDeliveryDate());

                order.track("QA2326363PL");

                //LinkedList

                MyLinkedList<String> list = new MyLinkedList<>();

                list.add("Order 1");
                list.add("Order 2");
                list.add("Order 3");

                logger.info("Linked List. Size: " + list.size());

                list.print();

                list.remove(1);
                logger.info("New List:");
                list.print();
                logger.info("Index 1: " + list.get(1));


                //Stream API
                List<Order> allOrders = new ArrayList<>();
                logger.info(order.getOrderStatus());
                allOrders.add(order);

                Order order2 = new Order(456L, sender, receiver,
                        new Product(456L, 1.0, "Small", false));
                order2.setOrderStatus(OrderStatus.DELIVERED);

                Order order3 = new Order(789L, sender, receiver,
                        new Product(789L, 5.0, "Large", true));
                order3.setOrderStatus(OrderStatus.DELIVERED);

                allOrders.add(order2);
                allOrders.add(order3);

                long countDeliveredOrders = allOrders.stream()
                        .filter(o -> o.getOrderStatus() == OrderStatus.DELIVERED)
                        .count();
                logger.info("Delivered orders: " + countDeliveredOrders);


                //reflection

                Class<Order> clazz = Order.class;
                logger.info("Class name: " + clazz.getName());
                logger.info("Simple name: " + clazz.getSimpleName());
                logger.info("Package: " + clazz.getPackageName());
                logger.info("Modifiers: " + Modifier.toString(clazz.getModifiers()));
                logger.info("Fields:");
                for (Field field : clazz.getDeclaredFields()) {
                        logger.info(Modifier.toString(field.getModifiers()) + " " +
                                field.getType().getSimpleName() + " " + field.getName());
                }
                logger.info("Methods:");
                for (Method method : clazz.getDeclaredMethods()) {
                        logger.info(
                                Modifier.toString(method.getModifiers()) + " " +
                                        method.getReturnType().getSimpleName() + " " + method.getName()
                        );
                }

                try {
                    clazz = Order.class;
                    Constructor<Order> constructor = clazz.getDeclaredConstructor(Long.class, Sender.class, Receiver.class);
                    constructor.setAccessible(true);
                    Order order4 = constructor.newInstance(1234L, sender, receiver);
                    logger.info("Created " + order4);

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }



                //Connection Pool
                ConnectionPool pool = new ConnectionPool(3);
                Runnable task = () -> {
                        try {
                                DBConnection connection = pool.getConnection();
                                logger.info(Thread.currentThread().getName() + " got connection ");
                                connection.execute("UPDATE delivery SET status='DELIVERED'");
                                Thread.sleep(2000);
                                pool.releaseConnection(connection);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                };
                for (int i = 0; i < 6; i++) {
                        new Thread(task, "Order " + i).start();
                }


                WordCounter.countWords("input.txt", "output.txt");




        }

}