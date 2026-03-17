package com.solvd.deliverycompany.exceptions;

public class InvalidOrderStatusException extends Exception {

    public InvalidOrderStatusException(String message) {
        super(message);
    }
}
