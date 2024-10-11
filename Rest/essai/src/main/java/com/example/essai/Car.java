package com.example.essai;

import java.util.ArrayList;
import java.util.List;

public class Car {
    private String plateNumber;
    private String brand;
    private double price;
    private boolean isRented;
    private List<Dates> rentalPeriods; // Liste des périodes de location

    public Car(String plateNumber, String brand, double price) {
        this.plateNumber = plateNumber;
        this.brand = brand;
        this.price = price;
        this.isRented = false;
        this.rentalPeriods = new ArrayList<>();
    }

    // Getters et setters
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public List<Dates> getRentalPeriods() {
        return rentalPeriods;
    }

    public void addRentalPeriod(Dates period) {
        this.rentalPeriods.add(period);
    }
}
