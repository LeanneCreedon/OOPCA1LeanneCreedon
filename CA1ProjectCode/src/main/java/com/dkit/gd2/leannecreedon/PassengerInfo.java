package com.dkit.gd2.leannecreedon;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class PassengerInfo {

    // PassengerInfo class to handle writing passengers to a text file and loading passengers from a text file.
    private final ArrayList<Passenger> passengersList;


    public PassengerInfo(ArrayList<Passenger> passengersList)
    {
        this.passengersList = passengersList;
    }


    public ArrayList<Passenger> getPassengersList() {
        return passengersList;
    }

    public boolean addNewPassenger(Passenger newPassenger)
    {
//        if(findPassenger(newPassenger.getName()) >= 0)
//        {
//            System.out.println("Passenger already stored");
//            return false;
//        }
        passengersList.add(newPassenger);
        return true;
    }

    public void displayAllPassengers()
    {
        for(Passenger passenger : this.passengersList)
        {
            System.out.println(passenger.toString());
        }
    }

    // Writing user input passengers to passengers.txt file
    public void addNewPassengerToFile(String name, IDSystem id, String email, String telephone, PositionTracker homePosition)
    {
        try (BufferedWriter passengerFile = new BufferedWriter(new FileWriter("passengers.txt")))
        {
            for (Passenger passenger : passengersList)
            {
                passengerFile.write(passenger.getName() + "," + passenger.getID() + "," +
                        passenger.getEmail() + "," + passenger.getTelephone() + "," + passenger.getHomePosition());

            }
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    // Load values from passengers.txt file
    public void loadPassengersFromFile(String filename)
    {
        try
        {
            Scanner keyboard = new Scanner(new File(filename));
            keyboard.useDelimiter("[,\r\n]+");
            while (keyboard.hasNext())
            {
                String name = keyboard.next();
                int id = keyboard.nextInt();
                String email = keyboard.next();
                String phone = keyboard.next();
                double latitude = keyboard.nextDouble();
                double longitude = keyboard.nextDouble();

                this.passengersList.add(new Passenger(name, id, email, phone, latitude, longitude));
            }
            keyboard.close();

        }
        catch (IOException e)
        {
            System.out.println("Exception thrown. " + e);
        }
    }


    public void loadPassenger()
    {
        try(BufferedReader passengersFile = new BufferedReader(new FileReader("passengers.txt")))
        {
            String input;
            while((input = passengersFile.readLine()) != null)
            {

            }
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
    }

}
