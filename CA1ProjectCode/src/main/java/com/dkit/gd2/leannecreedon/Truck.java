package com.dkit.gd2.leannecreedon;


public class Truck extends Vehicle {

    /* Attributes */
    private final double loadSpace;

    // Constructors
    public Truck(String type, String make, String model, double milesPerKWh, String registrationNumber,
               double costPerMile, int year, int month, int day, int mileage, double latitude,
               double longitude, double loadSpace)
    {
        super(type, make, model, milesPerKWh, registrationNumber, costPerMile, year, month,
                day, mileage, latitude, longitude);
        this.loadSpace = loadSpace;
    }

    public Truck(int id, String type, String make, String model, double milesPerKWh, String registrationNumber,
               double costPerMile, int year, int month, int day, int mileage, double latitude,
               double longitude, double loadSpace)
    {
        super(id, type, make, model, milesPerKWh, registrationNumber, costPerMile, year, month,
                day, mileage, latitude, longitude);
        this.loadSpace = loadSpace;
    }

    // Getter
    public double getLoadSpace()
    {
        return loadSpace;
    }

    // ToString
    @Override
    public String toString() {
        return "Truck{" +
                "loadSpace=" + loadSpace +
                '}';
    }
}
