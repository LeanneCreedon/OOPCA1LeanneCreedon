package com.dkit.gd2.leannecreedon;

public class Vehicle {

    /* Attributes */

    private String make;
    private String model;
    private int milesPerKWh;
    private int numberOfSeats;
    private String registrationNumber;
    private double costPerMile;
    private String lastServicedDate;
    private int mileage;
    private double vehicleDepotLatitude;
    private double vehicleDepotLongitude;

    // Constructor

    public Vehicle(String make, String model, int milesPerKWh, int numberOfSeats,
                   String registrationNumber, double costPerMile,
                   String lastServicedDate, int mileage, double vehicleDepotLatitude,
                   double vehicleDepotLongitude)
    {
        this.make = make;
        this.model = model;
        this.milesPerKWh = milesPerKWh;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;
        this.costPerMile = costPerMile;
        this.lastServicedDate = lastServicedDate;
        this.mileage = mileage;
        this.vehicleDepotLatitude = 0.0;
        this.vehicleDepotLongitude = 0.0;
    }

    // Getters

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getMilesPerKWh() {
        return milesPerKWh;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public double getCostPerMile() {
        return costPerMile;
    }

    public String getLastServicedDate() {
        return lastServicedDate;
    }

    public int getMileage() {
        return mileage;
    }

    public double getVehicleDepotLatitude() {
        return vehicleDepotLatitude;
    }

    public double getVehicleDepotLongitude() {
        return vehicleDepotLongitude;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", milesPerKWh=" + milesPerKWh +
                ", numberOfSeats=" + numberOfSeats +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", costPerMile=" + costPerMile +
                ", lastServicedDate='" + lastServicedDate + '\'' +
                ", mileage=" + mileage +
                ", vehicleDepotLatitude=" + vehicleDepotLatitude +
                ", vehicleDepotLongitude=" + vehicleDepotLongitude +
                '}';
    }
}
