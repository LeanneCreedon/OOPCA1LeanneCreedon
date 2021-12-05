package com.dkit.gd2.leannecreedon;


public class Passenger implements Comparable<Passenger>
{

    /* Attributes */

    private String name;
    private int id;
    private String email;
    private String telephone;
    private PositionTracker homePosition;

    private IDSystem idSystem = IDSystem.getInstance("idSystem.txt");

    // Constructor

    public Passenger(String name, IDSystem idSystem, String email, String telephone, PositionTracker homePosition)
    {
        this.name = name;
        this.id = idSystem.getNextId();
        this.email = email;
        this.telephone = telephone;
        this.homePosition = homePosition;
    }

    public Passenger(String name, int id, String email, String telephone, PositionTracker homePosition)
    {
        this.name = name;
        this.id = id;
        this.email = email;
        this.telephone = telephone;
        this.homePosition = homePosition;
    }

    // Getters

    public String getName() {
        return name;
    }

    public int getID() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }

    public PositionTracker getHomePosition() {
        return homePosition;
    }

    public static Passenger createNewPassenger(String name, IDSystem id, String email, String telephone, PositionTracker homePosition)
    {
        return new Passenger(name, id, email, telephone, homePosition);
    }

    // Writing user input passengers to passengers.txt file
//    public static Passengers writeNewPassenger(Passenger passengersList)
//    {
//        try (BufferedWriter passengerFile = new BufferedWriter(new FileWriter("passengers.txt")))
//        {
//            for (Passenger passenger : this.passengersList)
//            {
//                passengerFile.write(passenger.getName() + "," + passenger.getID() + "," +
//                        passenger.getEmail() + "," + passenger.getTelephone() + "," + passenger.getHomePosition());
//
//            }
//        }
//        catch(IOException ioe)
//        {
//            ioe.printStackTrace();
//        }
//        return null;
//    }

    // Implements Comparable Method

    @Override
    public int compareTo(Passenger otherPassenger)
    {
        return this.name.compareToIgnoreCase(otherPassenger.name);
    }

    // ToString Method

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                ", ID='" + id + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", homePosition=" + homePosition +
                '}';
    }

}
