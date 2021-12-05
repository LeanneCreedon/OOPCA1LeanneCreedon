package com.dkit.gd2.leannecreedon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Vehicles {

    // VehicleManager class to handle loading the vehicles from a text file.

    private final ArrayList<Vehicle> vehicleList;


    public Vehicles(String nameOfFile)
    {
        this.vehicleList = new ArrayList<>();
        loadVehiclesFromFile(nameOfFile);
    }

    public void displayAllVehicles()
    {
        for(Vehicle vehicle : vehicleList)
        {
            System.out.println(vehicle.toString());
        }
    }

    public void loadVehiclesFromFile(String fileName)
    {
        try
        {
            Scanner keyboard = new Scanner(new File(fileName));
            keyboard.useDelimiter("[,\r\n]+");

            while (keyboard.hasNext())
            {
                int id = keyboard.nextInt();
                String type = keyboard.next();
                String make = keyboard.next();
                String model = keyboard.next();
                double milesPerKWh = keyboard.nextDouble();
                String registrationNumber = keyboard.next();
                double costPerMile = keyboard.nextDouble();
                int year = keyboard.nextInt();
                int month = keyboard.nextInt();
                int day = keyboard.nextInt();
                int mileage = keyboard.nextInt();
                double latitude = keyboard.nextDouble();
                double longitude = keyboard.nextDouble();

                if (type.equalsIgnoreCase("Van") || type.equalsIgnoreCase("Truck"))
                {
                    double loadSpace = keyboard.nextDouble();
                    if(type.equalsIgnoreCase("Van"))
                    {
                        vehicleList.add(new Van(id, type, make, model, milesPerKWh, registrationNumber, costPerMile,
                                year, month, day, mileage, latitude, longitude, loadSpace));
                    }
                    else
                    {
                        vehicleList.add(new Truck(id, type, make, model, milesPerKWh, registrationNumber, costPerMile,
                                year, month, day, mileage, latitude, longitude, loadSpace));
                    }
                }

                if (type.equalsIgnoreCase("Car") || type.equalsIgnoreCase("FourByFour"))
                {
                    int numberOfSeats = keyboard.nextInt();
                    if(type.equalsIgnoreCase("Car"))
                    {
                        vehicleList.add(new Car(id, type, make, model, milesPerKWh, registrationNumber, costPerMile,
                                year, month, day, mileage, latitude, longitude, numberOfSeats));
                    }
                    else
                    {
                        vehicleList.add(new FourByFour(id, type, make, model, milesPerKWh, registrationNumber, costPerMile,
                                year, month, day, mileage, latitude, longitude, numberOfSeats));
                    }
                }
            }
            keyboard.close();
        }
        catch (IOException e)
        {
            System.out.println("Exception thrown. " + e);
        }
    }


    // Menu Methods Other versions

    // Search for depot pos
    public PositionTracker searchVehicleDepot(Vehicle vehicle)
    {
        if(findVehicle(vehicle) >=0)
        {
            return vehicle.getVehicleDepotPosition();
        }
        return null;
    }

    // Search by type
    public String searchVehicle(Vehicle vehicle)
    {
        if(findVehicle(vehicle) >= 0)
        {
            return vehicle.getType();
        }
        return null;
    }

    public Vehicle searchVehicle(String vehicleType)
    {
        int position = findVehicle(vehicleType);
        if(position >= 0)
        {
            return this.vehicleList.get(position);
        }
        return null;
    }

    private int findVehicle(Vehicle vehicle)
    {
        return this.vehicleList.indexOf(vehicle);
    }

    private int findVehicle(String vehicleType)
    {
        for (int i=0; i<this.vehicleList.size(); i++)
        {
            Vehicle vehicle = this.vehicleList.get(i);
            if(vehicle.getType().equals(vehicleType))
            {
                return i;
            }
        }
        return -1;
    }

}
