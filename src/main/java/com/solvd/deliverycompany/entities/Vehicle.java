package com.solvd.deliverycompany.entities;

public class Vehicle {

    private String type;
    private String plateNumber;
    private Double maxLoad;


    public Vehicle() {
    }

    public Vehicle(String type, String plateNumber, Double maxLoad) {
        this.type = type;
        this.plateNumber = plateNumber;
        this.maxLoad = maxLoad;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Double getMaxLoad() {
        return maxLoad;
    }

    public void setMaxLoad(Double maxLoad) {
        this.maxLoad = maxLoad;
    }


}

