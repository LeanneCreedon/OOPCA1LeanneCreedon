package com.dkit.gd2.leannecreedon;

import java.util.ArrayList;

public class VehicleManager {

    // VehicleManager class to handle loading the vehicles from a text file.

    private final ArrayList<Vehicle> vehicleList;

    public VehicleManager(ArrayList<Vehicle> vehicleList)
    {
        this.vehicleList = vehicleList;
    }

    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void displayAllVehicles()
    {
        for(Vehicle vehicle : this.vehicleList)
        {
            System.out.println(vehicle.toString());
        }
    }

    public void loadVehiclesInFromFile()
    {

    }
}
