package com.solvd.deliverycompany.interfaces;

import com.solvd.deliverycompany.exceptions.NotificationException;
import com.solvd.deliverycompany.person.Receiver;
import com.solvd.deliverycompany.person.Sender;

public interface Notifiable {

    public void sendSMS(Receiver receiver) throws NotificationException;
    public void sendSMS(Sender sender) throws NotificationException;
    public void sendEmail(Sender sender) throws NotificationException;
}
