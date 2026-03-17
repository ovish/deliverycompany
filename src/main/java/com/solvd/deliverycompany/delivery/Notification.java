package com.solvd.deliverycompany.delivery;

import com.solvd.deliverycompany.exceptions.NotificationException;
import com.solvd.deliverycompany.interfaces.Notifiable;
import com.solvd.deliverycompany.person.Receiver;
import com.solvd.deliverycompany.person.Sender;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Notification implements Notifiable {

    private String type;
    private String message;
    private static final Logger logger = LogManager.getLogger(Notification.class.getName());


    public Notification() {}

    public Notification(String type, String message) {
        this.type = type;
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void sendSMS(Receiver receiver) throws NotificationException {
        if (receiver.getPhone() == null)
            throw new NotificationException("Phone number missing");
        System.out.println(type + " to " + receiver.getPhone() + " : " + message);

    }

    @Override
    public void sendSMS(Sender sender) throws NotificationException {
        if (sender.getPhone() == null)
            throw new NotificationException("Phone number missing");
        logger.info(type + " to " + sender.getPhone() + " : " + message);
    }

    @Override
    public void sendEmail(Sender sender) throws NotificationException {
        if (sender.getEmail() == null)
            throw new NotificationException("Email missing");
        logger.info(type + " to " + sender.getEmail() + " : " + message);
    }
}
