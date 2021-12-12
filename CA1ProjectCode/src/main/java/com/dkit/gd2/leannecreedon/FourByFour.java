package com.dkit.gd2.leannecreedon;


public class FourByFour extends Vehicle {

    /* Attributes */
    private final int numberOfSeats;


    // Constructors
    public FourByFour(String type, String make, String model, double milesPerKWh, String registrationNumber,
               double costPerMile, int year, int month, int day, int mileage, double latitude,
               double longitude, int numberOfSeats)
    {
        super(type, make, model, milesPerKWh, registrationNumber, costPerMile, year, month,
                day, mileage, latitude, longitude);
        this.numberOfSeats = numberOfSeats;
    }

    public FourByFour(int id, String type, String make, String model, double milesPerKWh, String registrationNumber,
               double costPerMile, int year, int month, int day, int mileage, double latitude,
               double longitude, int numberOfSeats)
    {
        super(id, type, make, model, milesPerKWh, registrationNumber, costPerMile, year, month,
                day, mileage, latitude, longitude);
        this.numberOfSeats = numberOfSeats;
    }

    // Getter
    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    // ToString
    @Override
    public String toString() {
        return "FourByFour{" +
                "numberOfSeats=" + numberOfSeats +
                '}';
    }
}
