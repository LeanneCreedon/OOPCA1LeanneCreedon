package com.dkit.gd2.leannecreedon;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Passengers {

    // PassengerInfo class to handle loading passengers from a text file.
    private ArrayList<Passenger> passengersList = new ArrayList<Passenger>();

    public Passengers()
    {
        this.passengersList = passengersList;
    }

    public ArrayList<Passenger> getPassengersList() {
        return passengersList;
    }

//    private void loadPassengerFromFile(String filename)
//    {
//        try
//        {
//            Scanner keyboard = new Scanner(new File(filename));
//            keyboard.useDelimiter("[,\r\n]+");
//
//            while (keyboard.hasNext()) {
//                int id = keyboard.nextInt();
//                String name = keyboard.next();
//                String email = keyboard.next();
//                String phone = keyboard.next();
//                double latitude = keyboard.nextDouble();
//                double longitude = keyboard.nextDouble();
//
//                passengersList.add(new Passenger(id, name, email, phone, latitude, longitude));
//            }
//            keyboard.close();
//
//        }
//        catch (IOException e)
//        {
//            System.out.println("Exception thrown. " + e);
//        }
//    }

    public boolean removePassenger(Passenger passenger)
    {
        int foundPosition = findPassenger(passenger);
        if(foundPosition < 0)
        {
            System.out.println(passenger.getName() + ", was not found");
            return false;
        }
        this.passengersList.remove(foundPosition);
        System.out.println(passenger.getName() + ", was deleted");
        return true;
    }

    public boolean updatePassenger(Passenger oldPassenger, Passenger newPassenger)
    {
        int foundPosition = findPassenger(oldPassenger);
        if(foundPosition < 0)
        {
            System.out.println(oldPassenger.getName() + ", was not found");
            return false;
        }
        this.passengersList.set(foundPosition, newPassenger);
        return true;
    }

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

    // Passenger Menu Methods Other Versions

    public void addNewPassenger(Passenger newPassenger)
    {
        passengersList.add(newPassenger);
    }

    public void printPassengerDetails()
    {
        System.out.println("Printing Passenger Details: -\n");
        System.out.printf("%-25s%-15s%-35s%s%n", "Name", "ID", "Email", "Home Position");
        for(int i=0; i<111; i++)
        {
            System.out.print('-');
        }
        for (Passenger passenger : passengersList)
        {
            System.out.printf("%n%-25s%-15s%-35s%s%n", passenger.getName(),passenger.getID(),passenger.getEmail(),passenger.getHomePosition());
        }

    }

    public static void currentPassengerBookings()
    {
        System.out.println("CURRENT PASSENGER BOOKINGS");
    }

    public static void passengerBookingsDateTimeOrder()
    {
        System.out.println("PASSENGER BOOKINGS IN DATE/TIME ORDER");
    }

}

// Got help with formatting the table of passengers from this stack overflow page:
// https://stackoverflow.com/questions/18672643/how-to-print-a-table-of-information-in-java
