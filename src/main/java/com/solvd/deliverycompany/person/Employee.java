package com.solvd.deliverycompany.person;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Employee extends Person {

    private Long employeeId;
    private String position;
    private static final Logger logger = LogManager.getLogger(Employee.class);


    public Employee () {}

    public Employee(String firstName, String lastName, String phone, Long employeeId, String position) {
        super(firstName, lastName, phone);
        this.employeeId = employeeId;
        this.position = position;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public void printRole(){
        logger.info("model.Employee: " + super.getFirstName() + super.getLastName());
    }

}
