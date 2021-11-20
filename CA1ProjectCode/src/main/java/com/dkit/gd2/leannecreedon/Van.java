package com.dkit.gd2.leannecreedon;

public class Van extends Vehicle {

    /* Attributes */

    private int loadSpace;

    public Van(String make, String model, int milesPerKWh, int numberOfSeats,
               String registrationNumber, double costPerMile, String lastServicedDate,
               int mileage, double vehicleDepotLatitude, double vehicleDepotLongitude,
               int loadSpace)
    {
        super(make, model, milesPerKWh, numberOfSeats, registrationNumber, costPerMile,
                lastServicedDate, mileage, vehicleDepotLatitude, vehicleDepotLongitude);
        this.loadSpace = loadSpace;
    }

    public int getLoadSpace()
    {
        return loadSpace;
    }

    @Override
    public String toString() {
        return "Van{" +
                "loadSpace=" + loadSpace +
                '}';
    }
}
