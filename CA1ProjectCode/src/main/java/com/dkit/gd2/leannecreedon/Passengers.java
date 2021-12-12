package com.dkit.gd2.leannecreedon;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Passengers {

    // Passengers manager class to handle loading, writing and other useful methods in my system.
    private final ArrayList<Passenger> passengersList;

    public Passengers(String nameOfFile)
    {
        this.passengersList = new ArrayList<>();
        loadPassengersFromFile(nameOfFile);
    }

    public ArrayList<Passenger> getPassengersList() {
        return passengersList;
    }

    // Loading Passengers from passengers.txt file
    public void loadPassengersFromFile(String fileName)
    {
        try
        {
            Scanner keyboard = new Scanner(new File(fileName));
            keyboard.useDelimiter("[,\r\n]+");

            while (keyboard.hasNext())
            {
                String name = keyboard.next();
                int id = keyboard.nextInt();
                String email = keyboard.next();
                String phone = keyboard.next();
                double latitude = keyboard.nextDouble();
                double longitude = keyboard.nextDouble();

                PositionTracker homePos = new PositionTracker(latitude, longitude);
                passengersList.add(new Passenger(name, id, email, phone, homePos));
            }
            keyboard.close();
        }
        catch (IOException e)
        {
            System.out.println("Exception thrown. " + e);
        }
    }

    // Writing user input passengers to passengers.txt file
    public void writePassengers()
    {
        try (BufferedWriter passengerFile = new BufferedWriter(new FileWriter("passengers.txt")))
        {
            for (Passenger passenger : passengersList)
            {
                passengerFile.write(passenger.getName() + "," + passenger.getID() + "," +
                        passenger.getEmail() + "," + passenger.getTelephone() + "," + passenger.getHomePosition().getLatitude() +
                        "," + passenger.getHomePosition().getLongitude() + "\n");
            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    // UPDATING AND REMOVE PASSENGER METHODS
    public void removePassenger(Passenger passenger)
    {
        int foundPosition = findPassenger(passenger);
        if(foundPosition < 0)
        {
            System.out.println(Colours.RED + passenger.getName() + ", was not found" + Colours.RESET);
            return;
        }
        this.passengersList.remove(foundPosition);
        System.out.println(Colours.GREEN + passenger.getName() + ", was deleted" + Colours.RESET);
        writePassengers();
    }

    public boolean updatePassenger(Passenger oldPassenger, Passenger newPassenger)
    {
        int foundPosition = findPassenger(oldPassenger);
        if(foundPosition < 0)
        {
            System.out.println(Colours.RED + oldPassenger.getName() + ", was not found" + Colours.RESET);
            return false;
        }
        this.passengersList.set(foundPosition, newPassenger);
        writePassengers();
        return true;
    }

    // SEARCHING FOR PASSENGER BY NAME
    public String searchPassenger(Passenger passenger)
    {
        if(findPassenger(passenger) >= 0)
        {
            return passenger.getName();
        }
        return null;
    }

    public Passenger searchPassenger(String passengerName)
    {
        int position = findPassenger(passengerName);
        if(position >= 0)
        {
            return this.passengersList.get(position);
        }
        return null;
    }

    private int findPassenger(Passenger passenger)
    {
        return this.passengersList.indexOf(passenger);
    }

    private int findPassenger(String passengerName)
    {
        for (int i=0; i<this.passengersList.size(); i++)
        {
            Passenger passenger = this.passengersList.get(i);
            if(passenger.getName().equals(passengerName))
            {
                return i;
            }
        }
        return -1;
    }

    // ADDING NEW PASSENGER TO ARRAY LIST AND TO FILE
    public void addNewPassenger(Passenger newPassenger)
    {
        if(findPassenger(newPassenger.getName()) >= 0)
        {
            return;
        }
        passengersList.add(newPassenger);
        writePassengers();
    }

    // Printing out passenger details
    public void printPassengerDetails()
    {
        if(!(passengersList.isEmpty()))
        {
            System.out.println("Printing Passenger Details: -\n");
            System.out.printf("%-20s%-15s%-35s%-25s%s%n", "Name", "ID", "Email", "Telephone", "Home Position");
            for (int i = 0; i < 130; i++) {
                System.out.print('-');
            }
            for (Passenger passenger : passengersList) {
                System.out.printf("%n%-20s%-15s%-35s%-25s%s%n", passenger.getName(), passenger.getID(), passenger.getEmail(),
                        passenger.getTelephone(), passenger.getHomePosition());
            }
        }
        else
        {
            System.out.println(Colours.RED + "No Passengers on the system." + Colours.RESET);
        }
    }
}