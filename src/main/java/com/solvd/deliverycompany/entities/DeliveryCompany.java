package com.solvd.deliverycompany.entities;

import com.solvd.deliverycompany.person.Employee;
import com.solvd.deliverycompany.storage.SortingCenter;

import java.util.List;

public class DeliveryCompany {

private String name;
private Address address;
private List<Employee> employees;
private List<SortingCenter> sortingCenters;
private List<Order> orders;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<SortingCenter> getSortingCenters() {
        return sortingCenters;
    }

    public void setSortingCenters(List<SortingCenter> sortingCenters) {
        this.sortingCenters = sortingCenters;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
