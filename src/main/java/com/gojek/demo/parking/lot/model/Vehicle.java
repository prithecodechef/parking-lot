package com.gojek.demo.parking.lot.model;

import com.sun.istack.internal.NotNull;

public class Vehicle {
    @NotNull
    private String registrationNumber;

    @NotNull
    private String color; //can be a enum

    @NotNull
    private ParkingSpot parkingSpot;

    public Vehicle(String regitrationNumber, String color) {
        this.registrationNumber = regitrationNumber;
        this.color = color;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public void setParkingSpot(ParkingSpot parkingSpot) {
        this.parkingSpot = parkingSpot;
    }
}
