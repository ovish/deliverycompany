package com.solvd.deliverycompany.interfaces;

import com.solvd.deliverycompany.exceptions.InsufficientFundsException;

public interface Payable {

    boolean pay(Double amount) throws InsufficientFundsException;

}
