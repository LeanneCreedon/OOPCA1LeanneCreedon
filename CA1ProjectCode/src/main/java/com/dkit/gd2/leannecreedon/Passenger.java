package com.dkit.gd2.leannecreedon;


public class Passenger implements Comparable<Passenger>
{
    // Passenger class to create a passenger

    private final IDSystem idSystem = IDSystem.getInstance("idSystem.txt");

    /* Attributes */

    private final String name;
    private final int id;
    private final String email;
    private final String telephone;
    private final PositionTracker homePosition;


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

    // Creating new Passenger
    public static Passenger createNewPassenger(String name, IDSystem id, String email, String telephone, PositionTracker homePosition)
    {
        return new Passenger(name, id, email, telephone, homePosition);
    }

    // Updating Passenger
    public static Passenger updatePassenger(String name, int id, String email, String telephone, PositionTracker homePosition)
    {
        return new Passenger(name, id, email, telephone, homePosition);
    }


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
