package com.solvd.deliverycompany.entities;

import com.solvd.deliverycompany.exceptions.DeliveryException;

public class Address {

    private String city;
    private String street;
    private String zipCode;

    public void validate() throws DeliveryException {
        if (city == null || city.isEmpty()) {
            throw new DeliveryException("City is missing in address");
        }
        if (street == null || street.isEmpty()) {
            throw new DeliveryException("Street is missing in address");
        }
        if (zipCode == null || zipCode.length() < 5) {
            throw new DeliveryException("Invalid zipcode");
        }
    }

    public Address() {}

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + zipCode;
    }
}
