package com.dkit.gd2.leannecreedon;


import java.time.LocalDate;

public class Vehicle {

    /* Vehicle class to create a vehicle object. */

    private final IDSystem idSystem = IDSystem.getInstance("idSystem.txt");

    /* Attributes */
    private final int id;
    private final String type;
    private final String make;
    private final String model;
    private final double milesPerKWh;
    private final String registrationNumber;
    private final double costPerMile;
    private final LocalDate lastServicedDate;
    private final int mileage;
    private final PositionTracker vehicleDepotPosition;

    // Constructors

    public Vehicle(String type, String make, String model, double milesPerKWh, String registrationNumber,
                   double costPerMile, int year, int month, int day, int mileage, double latitude, double longitude)
    {
        this.id = idSystem.getNextId();
        this.type = type;
        this.make = make;
        this.model = model;
        this.milesPerKWh = milesPerKWh;
        this.registrationNumber = registrationNumber;
        this.costPerMile = costPerMile;
        this.lastServicedDate = LocalDate.of(year, month,day);
        this.mileage = mileage;
        this.vehicleDepotPosition = new PositionTracker(latitude, longitude);
    }

    public Vehicle(int id, String type, String make, String model, double milesPerKWh, String registrationNumber,
                   double costPerMile, int year, int month, int day, int mileage, double latitude, double longitude)
    {
        this.id = id;
        this.type = type;
        this.make = make;
        this.model = model;
        this.milesPerKWh = milesPerKWh;
        this.registrationNumber = registrationNumber;
        this.costPerMile = costPerMile;
        this.lastServicedDate = LocalDate.of(year, month,day);
        this.mileage = mileage;
        this.vehicleDepotPosition = new PositionTracker(latitude, longitude);
    }

    // Getters

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public double getMilesPerKWh() {
        return milesPerKWh;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public double getCostPerMile() {
        return costPerMile;
    }

    public LocalDate getLastServicedDate() {
        return lastServicedDate;
    }

    public int getMileage() {
        return mileage;
    }

    public PositionTracker getVehicleDepotPosition() {
        return vehicleDepotPosition;
    }

    // ToString

    @Override
    public String toString() {
        return "Vehicle{" +
                "idSystem=" + idSystem +
                ", id=" + id +
                ", type='" + type + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", milesPerKWh=" + milesPerKWh +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", costPerMile=" + costPerMile +
                ", lastServicedDate=" + lastServicedDate +
                ", mileage=" + mileage +
                ", vehicleDepotPosition=" + vehicleDepotPosition +
                '}';
    }
}
